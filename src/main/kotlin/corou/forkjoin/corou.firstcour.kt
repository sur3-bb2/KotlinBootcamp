package corou

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread

//fun main(args: Array<String>) = runBlocking {
//    println("hello world co routines")
//}


fun main(args: Array<String>) = runBlocking {
    val job = launch {
        launch {
            for (i in 1..100) {
                delay(10)
                print(i)
            }
        }
    }

    delay(300)
    job.cancelAndJoin()

    //delay(500)

    //println("after delay")

    //demotask1plustask2()
    //demoNSTC()

    //println("executing demoWithTimeout ")
    //demoWithTimeout()

    println("executing demoRunBlocking ")
    demoRunBlocking()
}

//
//fun main(args: Array<String>) {
//    demoWithTimeout()
//
//    println("exit")
//}

fun demoNSTC() = runBlocking {
    newSingleThreadContext("MySingleThread").use { ctx ->
        val job = launch(ctx) {
            println("demoNSTC inside launch ${Thread.currentThread().id}")
        }

        println("demoNSTC outside launch ${Thread.currentThread().id}")
        //job.join()
    }

    println("demoNSTC done ${Thread.currentThread().id}")
}

fun demoWithTimeout() = runBlocking {
    val job = withTimeoutOrNull(3000) {
        for(i in 1..100) {
            delay(1000)
            print(i)
            coroutineContext[Job]!!.isActive
        }
    }

    println("demoWithTimeout timed out")

    println(job)

    //delay(500)

    //println("demoWithTimeout after delay")
}

/**
 * runBlocking is single threaded....
 * launch is inheriting runBlocking scope...
 * delay is co-operative, means it can be suspended.
 *
 * now launch 1 executes first, and then launch 2 starts
 *
 * executing demoRunBlocking
    demoRunBlocking done launch
    demoRunBlocking after delay
    demoRunBlocking begin launch1
    12345678910demoRunBlocking end launch1
    demoRunBlocking begin launch2
    11121314151617181920demoRunBlocking end launch2

 * now if we switch Thread.sleep to delay, then it's coperative...both launches could run concurrently....
 * delay gives up/suspended,
 */
fun demoRunBlocking() {
    runBlocking {
        launch {
            println("demoRunBlocking begin launch1")

            for (i in 1..10) {
                //Thread.sleep(50)
                delay(50)
                print(i)
            }

            println("demoRunBlocking end launch1")
        }

        launch {
            println("demoRunBlocking begin launch2")

            for (i in 11..20) {
                //Thread.sleep(50)
                delay(50)
                print(i)
            }

            println("demoRunBlocking end launch2")
        }

        println("demoRunBlocking done launch")

        delay(500)

        println("demoRunBlocking after delay")
    }
}

fun demoC() {
    GlobalScope.launch {
        delay(1000)

        println(" World ${coroutineContext[Job]!!.isActive}")
    }

    print("Hello, ")

    sleep(1500)
}


fun demoC1() {
    println("Start")

// Start a coroutine
    GlobalScope.launch {
        delay(1000)
        println("Hello")
    }

    Thread.sleep(2000) // wait for 2 seconds
    println("Stop")
}


fun demoC2() = runBlocking {
    GlobalScope.launch {
        delay(1000)

        println(" World")
    }

    print("Hello, ")

    doSomeWork()
}

fun demoC3() = runBlocking {
    val job = GlobalScope.launch {
        delay(1000)

        println(" World")
    }

    print("Hello, ")

    job.join()
}


suspend fun doSomeWork() {
    delay(1500)
}


fun demoCancel() = runBlocking {
    val job = launch {
        for(i in 1..1000) {
            delay(100)
            print(".")
        }
    }

    delay(2500)

    job.cancelAndJoin()

    println("done")
}

fun demoCancelUsingYield() = runBlocking {
    val job = launch {
        for(i in 1..1000) {
            yield() // this checks the flag
            print(".")

            Thread.sleep(10) //this will not chekc for cancel state, so use with yield or other built in functions
        }
    }

    delay(2500)

    job.cancelAndJoin()

    println("done")
}

fun demoCancelUsingIsActive() = runBlocking {
    val job = launch {
        for(i in 1..1000) {
            if(!isActive) throw CancellationException()

            print(".")

            Thread.sleep(10) //this will not chekc for cancel state, so use with yield or other built in functions
        }
    }

    delay(2500)

    job.cancelAndJoin()

    println("done")
}

fun demoCancelUsingIsActiveReturn() = runBlocking {
    val job = launch {
        repeat(1000) {
            if(!isActive) return@launch

            print(".")

            Thread.sleep(10) //this will not chekc for cancel state, so use with yield or other built in functions
        }
    }

    delay(2500)

    job.cancelAndJoin()

    println("done")
}

fun demoTryCatch() = runBlocking {
    val job = launch {
        try {
            repeat(1000) {
                if (!isActive) return@launch

                print(".")

                Thread.sleep(10) //this will not chekc for cancel state, so use with yield or other built in functions
            }
        } catch (ex: CancellationException) {
            println()
            println(ex.toString())
        } finally {
           withContext(NonCancellable) {
               println()
           }
        }
    }

    delay(2500)

    job.cancelAndJoin()

    println("done")
}

fun demoT() {
    thread {
        sleep(1000)

        println(" World")
    }

    print("Hello, ")

    sleep(1500)
}


suspend fun demotask1plustask2() {
    val result1 = task1()
    val result2 = task2()

    task1plustask2(result1, result2)
}

fun task1plustask2(result1: Int, result2: Int) {
    println(result1 + result2)
}

suspend fun task1() : Int {
    println("executing task1")
    delay(100)

    return 100
}

suspend fun task2() : Int {
    println("executing task2")
    delay(2000)

    return 150
}