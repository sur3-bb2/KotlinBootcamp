package corou.forkjoin.MA

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis


fun main() = runBlocking {
    val jobs = 1000
    val runs = 1000

    var counter = Counter()
    counter.run(Dispatchers.Default, jobs, runs) {
        counter.increment()
    }

    counter.value = 0

    counter = Counter()
    var time = counter.run(Dispatchers.Default, jobs, runs) {
        counter.increment()
    }

    logResult("Base counter", jobs, runs, time, counter)

    counter.value = 0

    counter = Counter()
    var context1 = newSingleThreadContext("ST1")
    time = counter.run(context1, jobs, runs) {
        withContext(context1) {
            counter.increment()
        }
    }

    logResult("Single Thread Context for increment only", jobs, runs, time, counter)

    counter.value = 0

    counter = Counter()
    var context = newSingleThreadContext("ST")
    time = counter.run(context, jobs, runs) {
        counter.increment()
    }

    logResult("Single Thread Context", jobs, runs, time, counter)

    counter = AtomicCounter()
    time = counter.run(Dispatchers.Default, jobs, runs) {
        counter.increment()
    }

    logResult("Atomic integer", jobs, runs, time, counter)

    counter = MutexCounter()
    time = counter.run(Dispatchers.Default, jobs, runs) {
        counter.increment()
    }

    logResult("Mutex integer", jobs, runs, time, counter)
}

fun logResult(counterType: String, n: Int, k: Int, time: Long, c: Counter) {
    println("${counterType} completed ${n*k} actions in $time")
    println("Counter : ${c.value}")
}

open class Counter() {
    private var counter = 0

    open suspend fun increment(){
        counter++
    }

    open var value: Int
        get() = counter
        set(value) {
            counter = value
        }

    suspend fun run(coroutineContext: CoroutineContext, jobs: Int, count: Int,
                         action: suspend () -> Unit): Long {

        return measureTimeMillis {
            val launchJobs = List(jobs) {
                GlobalScope.launch(coroutineContext) {
                    repeat(count) {
                        action()
                    }
                }
            }

            launchJobs.forEach { it.join() }
        }
    }
}

class AtomicCounter : Counter() {
    var counter : AtomicInteger = AtomicInteger(0)

    override suspend fun increment() {
        counter.getAndIncrement()
    }

    override var value: Int
        get() = counter.get()
        set(value) {
            counter.set(value)
        }
}

class MutexCounter : Counter() {
    var counter : Int = 0
    var mutex = Mutex()

    override suspend fun increment() {
        mutex.withLock {
            counter.inc()
        }
    }

    override var value: Int
        get() = counter
        set(value) {
            counter = value
        }
}