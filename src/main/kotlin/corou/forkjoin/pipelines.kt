package corou.forkjoin.pipes

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun produceNumbers() = GlobalScope.produce {
    var x = 0

    while(true) {
        send(x++)
    }
}


fun squareNumber(channel: ReceiveChannel<Int>) = GlobalScope.produce {
    for(item in channel) {
        send(item * item)
    }
}


fun main() {
    var pn = produceNumbers()
    var sn = squareNumber(pn)

    runBlocking {
        repeat(10) {
            println(sn.receive())
        }
    }

    println("main done")

    pn.cancel()
    sn.cancel()
}