package corou.forkjoin

import java.math.BigInteger
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask
import kotlin.system.measureTimeMillis

const val threshold = 5000

fun main(args: Array<String>) {
    val list = mutableListOf<Int>()

    var limit = 20_000_000

    while(limit > 0) {
        list.add(limit--)
    }

    var result = 0L

    run {
        var time = measureTimeMillis {
            result = Sum.sum(list.toIntArray())
        }

        time = measureTimeMillis {
            result = Sum.sum(list.toIntArray())
        }

        println("sum: $result in $time")
    }

    run {
        var time = measureTimeMillis {
            result = list.compute(0, list.size)
        }

        time = measureTimeMillis {
            result = list.compute(0, list.size)
        }

        println("sum: $result in $time")
    }
}


fun compute(array: IntArray, low: Int, high: Int): Long {
    return if(high - low <= threshold) {
        (low until high).map { array[it].toLong() }.sum()
    } else {
        var mid = low + (high - low) / 2
        val left = compute(array, low, mid)
        val right = compute(array, mid, high)

        return left + right
    }
}


fun List<Int>.compute(low: Int, high: Int) : Long {
    //println("cal low:$low high:$high size${size}")

    if(size <= threshold) {
        return this.map { it.toLong() }.sum()
    } else {
        //println("calculating left")

        val mid = low + (high - low) / 2
        val leftList = this.subList(low, mid)
        val left = leftList.compute(0, leftList.size)
        //println("calculted left")

        //println("calculting right")
        val rightList = this.subList(mid, high)
        val right = rightList.compute(0, rightList.size)
        //println("calculated right")


        //println(left)
        //println(right)

        return left + right
    }
}

class Sum(var array: IntArray, var low: Int, var high: Int) : RecursiveTask<Long>() {
    override fun compute(): Long {
        return if(high - low <= threshold) {
            (low until high).map { array[it].toLong() }.sum()
        } else {
            var mid = low + (high - low) / 2
            val left = Sum(array, low, mid)
            val right = Sum(array, mid, high)

            left.fork()

            val rightAns = right.compute()
            val leftAns = left.join()

            return leftAns + rightAns
        }
    }

    companion object {
        const val threshold = 5000

        fun sum(array: IntArray) : Long {
            return ForkJoinPool.commonPool().invoke(Sum(array, 0, array.size))
        }
    }
}