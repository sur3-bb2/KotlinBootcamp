package hof.FA

fun main(args: Array<String>) {
    println("before X: $x, after calling callSomething() ${callSomething()}, X: $x ")
}


fun callSomething() = incX()

var x = 0

fun incX() {
    x += 1
}

fun decX() {
    x -= 1
}
