package corou.forkjoin.GSIO

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun work(i: Int) {
    Thread.sleep(1000)
    println("Work $i done")
}

/**
 *To get concurrent execution in background threads and complete our
 * work in a second we can launch coroutines with Dispatchers.Default
 *
 * The launch(Dispatchers.Default) creates children coroutines in runBlocking scope, so runBlocking waits
 * for their completion automatically.
 *
 * if we just used 'launch', it will inherit parent scope's dispatcher which is 'single thread'
 */
fun main() {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                launch(Dispatchers.Default) {
                    work(i)
                }

                println("launched $i")
            }
        }
    }
    println("Done in $time ms")
}

/**
 * It prints Work 1 done and Work 2 done, but it takes two seconds to complete.
 * Where’s concurrency? There is none — here, launch had inherited coroutine dispatcher from the
 * scope introduced by runBlocking coroutine builder,
 * which confines execution to the single thread, so both tasks execute sequentially in the main thread.
 */
fun mainSequential() {
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                launch {
                    work(i)
                }
            }
        }
    }
    println("Done in $time ms")
}

/**
 * never prints anything
 *
 * GlobalScope.launch creates global coroutines. It is now developer’s responsibility to keep track of their lifetime.
 * We can “fix” an approach with GlobalScope by manually keeping track of the launched coroutines and
 * waiting for their completion using join
 */
fun mainGlobalScope() {
    val jobs = mutableListOf<Job>()
    val time = measureTimeMillis {
        runBlocking {
            for (i in 1..2) {
                jobs += GlobalScope.launch {
                    work(i)
                }
            }

            jobs.forEach{ it.join() }
        }
    }

    println("Done in $time ms")
}