package corou.forkjoin.scs

import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

open class Result(val output: String?)

class Success(output: String) : Result(output)

class Failure : Result(null)

fun onComplete(result: Result?) {
    when (result) {
        is Success -> println("onSuccess : ${result.output}")
        is Failure -> println("onError ")
    }
}

class Callback {
    var listener: ((Result?) -> Unit)? = null

    internal fun onSuccess(result: Result?) {
        onComplete(result)
    }

    internal fun onError(result: Result?) {
        onComplete(result)
    }

    internal fun addListener(listener: ((Result?) -> Unit)?) {
        this.listener = listener
    }

    fun onComplete(result: Result?) {
        listener?.let { it(result) }
    }
}

fun fakeAsyncCallback(): Callback {
    val callback = Callback()

    thread(isDaemon = false) {
        try {
            val threadid = Thread.currentThread().id
            println("starting fakeAsyncCallback : $threadid")

            Thread.sleep(4000)

            println("done fakeAsyncCallback : $threadid")

            callback.onSuccess(Success("fakeAsyncCallback completed"))
        } catch (throwable: Throwable) {
            callback.onError(Failure())
        }
    }

    return callback
}

suspend fun convertToSuspend(callback: Callback): String {
    return suspendCoroutine { continuation ->
        callback.addListener { result ->
            when (result) {
                is Success -> continuation.resume("onSuccess : ${result.output}")
                is Failure -> continuation.resume("onError ")
            }
        }
    }
}

fun main(args: Array<String>)  {
    GlobalScope.launch {
        val threadid = Thread.currentThread().id

        val callback = fakeAsyncCallback()
        convertToSuspend(callback)
//    val job = async {
//        convertToSuspend(callback)
//    }


        println("called fakeAsyncCallback : $threadid")

        println("main done : $threadid")

        //delay(1000)

        //println("delay done : $threadid")

        //println("result  : ${job.await()}")
        //println("result  : $result")
    }

    Thread.sleep(5000)
}

fun mainUsingTraditional(args: Array<String>) {
    val callback = fakeAsyncCallback()

    callback.listener = ::onComplete

    println("called fakeAsyncCallback")

    println("main done")
}