package sequences

/**
 * The first important thing to know is that all intermediate operations (the functions that return a new sequence)
 * are not executed until a terminal operation has been called. Sequences are evaluated lazily to avoid examining
 * all of the input data when it's not necessary.
 */
fun main() {
    demoSimpleSequences()

    println("------------")

    fibonacci.take(10).forEach(::print)

    println("------------")

    println(generateSimpleSequence.toList())

    println("------------")

    fibonaccii()

    println("------------")

    twoSequence()
    singleSequence()
}

fun demoSimpleSequences() {
    sequence {
        val start = 0

        yield(start)

        yieldAll(1..10 step 2)

        //this will generate infinite sequence
        yieldAll(generateSequence(8) { it * 3 })
    }.take(10)
            .filter {
                println("filter $it")
                true
            }.map {
                "$it, "
            }.forEach(::print)
}

val fibonacci = sequence {
    var pair = Pair(0, 1)

    while (true) {
        yield(pair.first)

        pair = Pair(pair.second, pair.first + pair.second)
    }
}

var count = 10
val generateSimpleSequence = generateSequence {
    (count--).takeIf { it > 0 }
}

fun fibonaccii() = generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }.map { it.first }.take(10).forEach(::print)

class LinkedValue<T>(val value: T, val next: LinkedValue<T>? = null)

fun <T : Any> LinkedValue<T>.asSequence() = generateSequence(
        { this },
        { it.next }
)

fun <T : Any> LinkedValue<T>.valueSequence() = this.asSequence().map { it.value }

val single = LinkedValue(1)
val two = LinkedValue(2, single)

fun twoSequence() = println(two.valueSequence().toList())
fun singleSequence() = println(single.valueSequence().toList())


fun sequencePlay() {
    /**
     * The following code will print nothing because filter is an intermediate function and no terminal function is ever called:
     */
    sequenceOf("A", "B", "C")
            .filter {
                println("filter: $it")
                true
            }

    /**
     * Now, if we extend the example by calling the terminal function forEach after
     * filter, we can actually examine the order of processing the elements:
     */
    sequenceOf("A", "B", "C")
            .filter {
                println("filter: $it")
                true
            }
            .forEach {
                println("forEach: $it")
            }

    // filter:  A
    // forEach: A
    // filter:  B
    // forEach: B
    // filter:  C
    // forEach: C

    val result = sequenceOf("a", "b", "c")
            .map {
                println("map: $it")
                it.toUpperCase()
            }
            .any {
                println("any: $it")
                it.startsWith("B")
            }

    println(result)

    // map: A
    // any: A
    // map: B
    // any: B
    // true
}

/**
 * Because of that, also ordering of operation processing is different. In sequence processing we generally
 * make whole processing for a single element, then for another etc. In Iterable processing, we process
 * the whole collection on every step.
 */
fun sequenceOrder() {
    sequenceOf(1, 2, 3)
            .filter { println("Filter $it, "); it % 2 == 1 }
            .map { println("Map $it, "); it * 2 }
            .toList()
// Prints: Filter 1, Map 1, Filter 2, Filter 3, Map 3,

    listOf(1, 2, 3)
            .filter { println("Filter $it, "); it % 2 == 1 }
            .map { println("Map $it, "); it * 2 }
// Prints: Filter 1, Filter 2, Filter 3, Map 1, Map 3,
}

/**
 * Two-step processing is already visibly faster when we use Sequences. In this case improvement is around 30%.
 */
class Product(val bought: Boolean, val price: Double)

val productsList = arrayListOf<Product>()
fun twoStepListProcessing(): List<Double> {
    return productsList
            .filter { it.bought }
            .map { it.price }
}

fun twoStepSequenceProcessing(): List<Double> {
    return productsList.asSequence()
            .filter { it.bought }
            .map { it.price }
            .toList()
}

fun threeStepListProcessing(): Double {
    return productsList
            .filter { it.bought }
            .map { it.price }
            .average()
}

fun threeStepSequenceProcessing(): Double {
    return productsList.asSequence()
            .filter { it.bought }
            .map { it.price }
            .average()
}


class Client(val adult: Boolean, val products: List<Product>)

val clientsList = arrayListOf<Client>()

fun productsListProcessing(): Double {

    return clientsList

            .filter { it.adult }

            .flatMap { it.products }

            .filter { it.bought }

            .map { it.price }

            .average()
}

fun productsSequenceProcessing(): Double {

    return clientsList.asSequence()
            .filter { it.adult }

            .flatMap { it.products.asSequence() }

            .filter { it.bought }

            .map { it.price }

            .average()
}

/**
It is controversial if Sequence should have methods like sorted because it is only palatially lazy
(evaluated when we need to get the first element) and don’t work on infinitive sequences.
It was added because it is a popular function and it is much easier to use it this way.
Although Kotlin developer should remember that is cannot be used on infinitive sequences.

generateSequence(0) { it + 1 }.sorted().take(10).toList()
// Infinitive calculation time

generateSequence(0) { it + 1 }.take(10).sorted().toList()
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 **/

// Took around 150 482 ns
fun productsSortAndProcessingList(): Double {
    return productsList
            .sortedBy { it.price }
            .filter { it.bought }
            .map { it.price }
            .average()
}

// Took around 96 811 ns
fun productsSortAndProcessingSequence(): Double {
    return productsList.asSequence()
            .sortedBy { it.price }
            .filter { it.bought }
            .map { it.price }
            .average()
}