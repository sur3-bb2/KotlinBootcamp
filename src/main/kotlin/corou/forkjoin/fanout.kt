package corou.forkjoin.fanout

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce


suspend fun produceNumbers(channel: Channel<String>, value: String) {
    while(true) {
        channel.send(value)
        delay(100)
    }
}

suspend fun consumeNumbers(channel: Channel<String>)  {
    channel.consumeEach {
        println("received: $it")
    }
}

fun main() = runBlocking {
    val channel = Channel<String>()

    run {
        launch { produceNumbers(channel, "foo") }
        launch { produceNumbers(channel, "bar") }
    }

    launch {  consumeNumbers(channel) }


    delay(2000)

    coroutineContext.cancelChildren()
}