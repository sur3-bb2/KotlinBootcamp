package corou.forkjoin

import kotlinx.coroutines.*

fun main(args: Array<String>) {
    val promise = doWorkAsync("best work")

    runBlocking {
        println(promise.await())
    }
}

fun doWorkAsync(msg: String) : Deferred<Int> = GlobalScope.async {
    println("doing work $msg")

    delay(100)

    println("done work $msg")

    return@async 41
}