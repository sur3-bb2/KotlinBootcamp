package corou.async

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) {
    val syncallTime = measureTimeMillis { demoSyncCall() }
    println("done syncallTime")

    val concurrentLaunchesTime = measureTimeMillis { demoASyncCall() }
    println("done concurrentLaunchesTime")

    val asyncTime = measureTimeMillis {
        runBlocking {
            println("return from demoUsingAsync ${demoUsingAsync()}")
        }
    }
    println("done asyncTime")

    println("syncallTime: $syncallTime")
    println("concurrentLaunchesTime: $concurrentLaunchesTime")
    println("asyncTime: $asyncTime")
}

fun demoSyncCall() = runBlocking {
    val job = launch {
        doWork1()
        doWork2()
    }
}

fun demoASyncCall() = runBlocking {
    val job1 = launch {
        doWork1()
    }

    val job2 = launch {
        doWork2()
    }
}

// now we're not waiting for launch, so can't get sum/process might be exited before it could calculate
// correction === added suspend, so we can join, and call this from runBlocking....
suspend fun demoUsingAsync(): Int {
    var sum = 0

    val job = GlobalScope.launch {
        val v1 = async { doWork1() }

        val v2 = async { doWork2() }

        sum = v1.await() + v2.await()

        println("Calculated sum $sum")
    }

    job.join()

    return sum
}

suspend fun doWork1() : Int {
    delay(100)

    println("done work 1")

    return 42
}

suspend fun doWork2() : Int {
    delay(200)

    println("done work 2")

    return 42
}