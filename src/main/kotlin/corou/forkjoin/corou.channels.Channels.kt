package corou.channels

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        demoSimpleChannel()
    }
}

suspend fun demoSimpleChannel() {
    var channel: Channel<Int> = Channel()

    GlobalScope.launch {
        for(i in 1..5) {
            channel.send(i)
            println("Send $i")
        }

        channel.close()
    }

    for(y in channel) {
        println("received $y")
    }
}
