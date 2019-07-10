package corou.forkjoin.issues

import kotlinx.coroutines.*


/**
 * difference is with out runBlocking, the created thread is abandoned....
 *
 * GlobalScope Job needs to be maintained....
 */
fun main(args: Array<String>)   {
    var job = GlobalScope.launch {
        val value = async {
            doWork()
        }

        value.await()
    }

    println("launched")
}

/**
 * difference is with runBlocking
 * the block completes to execution
 *
 * issue is when we access value of 'value', could be null when evaluating....
 * launch/async is parent child... so join on 'launch' will wait for async to complete as well....
 */
//fun main() = runBlocking  {
//    var value: Deferred<Int>? = null
//
//    var job = launch {
//        value = async {
//            doWork()
//        }
//    }
//
//    //job.join()   //uncomment to use value
//    println("launched")
//    //println("value is ${value!!.await()}")
//}

suspend fun doWork() : Int {
    println("lazy")
    delay(500)
    println("lazy done")

    return 42
}

