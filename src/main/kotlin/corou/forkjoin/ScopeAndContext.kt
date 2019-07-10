package corou.forkjoin.ScopeAndContext

import kotlinx.coroutines.*
import java.math.BigInteger
import java.util.*
import kotlin.coroutines.coroutineContext


fun main() = runBlocking<Unit> {
    val job = coroutineContext[Job]
    println(coroutineContext) //parent context and it's job

    val childJob = launch {
        println(coroutineContext) // this is child context, with its own child job

        scopeCheck(this)
    }

    println("jobs equality ${job == childJob}")

    println("------------")

    println("waiting for find to return big prime number")

    find()
}

/**
 * fun CoroutineScope.launch(
// ...
block: suspend CoroutineScope.() -> Unit
): Job
By convention which is followed by all coroutine builders, this scope’s coroutineContext property
is the same as the context of the coroutine that is running inside this block
 */
suspend fun scopeCheck(scope: CoroutineScope) {
    println(scope.coroutineContext === coroutineContext)
}


suspend fun find() {
    val i = findBigPrime()

    println(i)
}

suspend fun findBigPrime(): BigInteger =
        withContext(Dispatchers.Default) {
            BigInteger.probablePrime(4096, Random())
        }
