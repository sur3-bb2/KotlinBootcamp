package corou.forkjoin.actors

import corou.forkjoin.MA.Counter
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis


fun main() = runBlocking {
    val jobs = 1000
    val runs = 1000

    var counter = actor()

    counter.send(InitCount)

    run(Dispatchers.Default, jobs, runs) {
        counter.send(IncCount)
    }

    val result = CompletableDeferred<Int>()

    counter.send(GetCount(result))

    println(result.await())
}


sealed class CounterMsg

object InitCount: CounterMsg()
object IncCount: CounterMsg()
class GetCount(val response : CompletableDeferred<Int>) : CounterMsg()

fun actor() = GlobalScope.actor<CounterMsg> {
    var counter = 0;

    for(message in channel) {
        when(message) {
            is InitCount -> counter = 0
            is IncCount -> counter ++
            is GetCount -> message.response.complete(counter)
        }
    }
}

suspend fun run(coroutineContext: CoroutineContext, jobs: Int, count: Int,
                action: suspend () -> Unit): Long {

    return measureTimeMillis {
        val launchJobs = List(jobs) {
            GlobalScope.launch(coroutineContext) {
                repeat(count) {
                    action()
                }
            }
        }

        launchJobs.forEach { it.join() }
    }
}

