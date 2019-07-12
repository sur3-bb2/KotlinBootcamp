package hof

fun <A, B, C> compose(a: (B) -> C, b: (A) -> B) : (A) -> C {
    return { x -> a(b(x)) }
}

fun <A, B, C> compose1(a: (B) -> C, b: (A) -> B) = { x: A -> a(b(x)) }

fun main() {
    val oddLength = compose(::isOdd, ::length)
    val oddLength1 = compose1(::isOdd, ::length)

    val strings = listOf("a", "ab", "abc")

    println(strings.filter(oddLength))
    println(strings.filter(oddLength1))
}

fun isOdd(x: Int) = x % 2 != 0
fun length(s: String) = s.length
