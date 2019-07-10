package corou.forkjoin.fanin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun produceNumbers() = GlobalScope.produce {
    var x = 0

    while(true) {
        send(x++)
    }
}

suspend fun consumeNumbers(id: String, channel: ReceiveChannel<Int>) = GlobalScope.launch {
    channel.consumeEach {
        println("Consumer : $id, received: $it")
    }
}

fun main() = runBlocking {
    val producer = produceNumbers()

    for (i in 1..5) {
        consumeNumbers(i.toString(), producer)
    }


    delay(100)

    producer.cancel()
}