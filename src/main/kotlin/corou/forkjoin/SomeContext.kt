package corou.forkjoin

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main1() {
    runBlocking {
        val scope0 = this
        // scope0 is the top-level coroutine scope.
        println("main $scope0")
        scope0.launch {
            println("${scope0.coroutineContext == this.coroutineContext}")
            val scope1 = this
            // scope1 inherits its context from scope0. It replaces the Job field
            // with its own job, which is a child of the job in scope0.
            // It retains the Dispatcher field so the launched coroutine uses
            // the dispatcher created by runBlocking.
            println("scope0")
            scope1.launch {
                val scope2 = this
                // scope2 inherits from scope1
                println("scope1")
            }
            println("after launch scope1")
        }
        println("after launch scope0")
    }
}

fun main() {
    SomeContext1.main()
}


object SomeContext1 {
    fun work(i: Int) {
        println("Working on $i")
        Thread.sleep(1000)
        println("Work $i done")
    }

    fun main() {
        val time = measureTimeMillis {
            runBlocking {
                for (i in 1..2) {
                    println("launching $i")
                    launch {
                        work(i)
                    }
                }
            }
        }
        println("Done in $time ms")
    }
}