package corou.forkjoin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun produce(sideChannel : SendChannel<Int>):ReceiveChannel<Int> = GlobalScope.produce {
    for(i in 1..10) {
        select<Unit> {
            onSend(i) {}
            sideChannel.onSend(i) {}
        }
    }
}


fun main() = runBlocking {
    val channel = Channel<Int>()
    val p = produce(channel)

    launch {
        channel.consumeEach {
            println("side receive ${it}")
        }
    }

    p.consumeEach {
        delay(100)

        println("receive $it")
    }
}