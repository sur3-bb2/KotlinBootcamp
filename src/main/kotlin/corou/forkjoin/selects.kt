package corou.forkjoin.selects

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select


fun producer1() = GlobalScope.produce {
    while (true) {
        //delay(10)

        send("from producer 1")
    }
}


fun producer2() = GlobalScope.produce {
    while (true) {
        //delay(13)

        send("from producer 2")
    }
}

suspend fun selector(p1: ReceiveChannel<String>, p2: ReceiveChannel<String>) {
    select<Unit> {
        p1.onReceiveOrNull { println(it) }
        p2.onReceive { println(it) }
    }
}

fun main() = runBlocking {
    val p1 = producer1()
    val p2 = producer2()

    repeat(15) {
        selector(p1, p2)
    }
}

