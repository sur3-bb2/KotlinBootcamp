package corou.forkjoin.pj

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() {
    var channel = produceNumber()

//    runBlocking {
//        channel.consumeEach {
//            println(it)
//        }
//    }

    GlobalScope.launch {
        channel.consumeEach {
            println(it)
        }
    }.join()

    println("main done")
}


fun produceNumber() = GlobalScope.produce {
    for(x in 1..5) {
        println("produce $x")

        send(x)
    }

    println("done producing")
}

