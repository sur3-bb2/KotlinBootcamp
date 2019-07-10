package stdlib

import java.util.*

fun main(args: Array<String>) {
    puppyPlay()
    //makePairs()

    makeSets()

    val a: Aqua? = null
    a.pull()

    forEa()
}

fun makeSets() {
    val allBooks = setOf("Hamlet", "WRomeo and Juliet", "Henry VI", "Henry VI")

    val library = mapOf("William Shakespeare" to allBooks)

    println(library["William Shakespeare"])

    println(library.any { entry -> entry.value.any { it == "Hamlet" } })

    val moreBooks = mutableMapOf("William Shakespeare" to allBooks)

    moreBooks.getOrPut("Robert Frost") {
        setOf("A Way Out", "A Masque of Mercy")
    }

    println(moreBooks["Robert Frost"])
}

fun makePairs() {
    val returns = returnPair()
    val(name, age) = returnPair()

    println("name is $name, and age is $age")

    println("name is ${returns.first}, and age is ${returns.second}")

    val nightmare = returnPairNightmare()

    println("name is ${nightmare.first.first.first.first} and age is ${nightmare.first.first.first.second} " +
            "married to ${nightmare.first.second}, wonderful daughter ${nightmare.second}")
}

fun returnPair() : Pair<String, Int> {
    return "suresh" to 37
}

fun returnTriple() : Triple<String, String, Int> {
    return Triple("suresh",  "priya", 37)
}

fun returnPairNightmare(): Pair<Pair<Pair<Pair<String, Int>, String>, String>, String> {
    return "suresh" to 37 to "bengaluru" to "priya" to "kushi"
}


//val is run time - can be value returned by function, const is compile time.....
//only in object class, or use companion object(static constructor) accessed only once

const val country = "IN"

object CountryCode {
    const val INDIA = "IN"
    const val US = "US"
}

class CC {
    companion object {
        const val india = "in"
    }

}
// extension functions --- Receiver.
fun String.hasSpaces() : Boolean = this.find { it == ' ' } != null

class Aqua(val color: String, private val size: Int) {}

fun Aqua.isGreenColor() = color == "Green"

fun Aqua?.pull() {
    this?.apply {
        println("removing")
    }
}

val Aqua.isGreen : Boolean
    get() = color == "Green"



class Book(var pages: Int)

fun Book.weight() = pages * 1.5

fun Book.tornPages(total: Int) {
    //println("torn pages $total prev $pages")
    if(pages > total) pages -= total else pages = 0
    //println("torn pages $total prev $pages")
}

object Puppy {
    fun playWithBook(book : Book) {
        book.tornPages(Random().nextInt(12))
        //println("remaining pages ${book.pages}")
    }
}

fun puppyPlay() {
    val book = Book(200)

    while(book.pages > 0) {
        Puppy.playWithBook(book)
        println("remaining pages ${book.pages}")
    }

    println(book.weight())
}


fun forEa() {
    val v = listOf(1, 2, 3 ,4 ,5)

    v.forEach { println(it) }
}