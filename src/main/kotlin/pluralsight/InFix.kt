package pluralsight

import java.math.BigInteger

fun main(args: Array<String>) {
    myinfix()

    println(fib(10000, BigInteger("1"), BigInteger("0")))
}

//fib(5, 1, 0)
//  fib(4, 1, 1)
//    fib(3, 2, 1)
//      fib(2, 3, 2)
//        fib(1, 5, 3)
//          fib(0, 8, 5)

tailrec fun fib(n: Int, a: BigInteger, b: BigInteger) : BigInteger {
    //println("n: $n a: $a b: $b")
    return if(n == 0) b else fib(n -1, a + b, a)
}



private fun myinfix() {
    val h1 = Header("h1")
    val h2 = Header("h2")

    val h3 = h1 plus h2

    println(h3.name)

    val h4 = h1 * h2

    println(h4.name)
}

infix operator fun Header.plus(other: Header) : Header {
    return Header("${name} : ${other.name}")
}

infix operator fun Header.times(other: Header) : Header {
    return Header("${name} : ${other.name}")
}

data class Header(val name: String)

class Persons(name: String)