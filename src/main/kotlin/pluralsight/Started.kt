@file:JvmName("PluralSightCourse")
package pluralsight

import JavaRunClass
import java.util.*

fun main(args: Array<String>) {
    //demo()

    //illegal()

    //forloops()

    fordeco()
}


fun fordeco() {
    val i = listOf(1, 2, 3, 4, 5)

    for((index, value) in i.withIndex()){
        println("$value is at $index")
    }

    val ii = TreeMap<String, Int>()

    ii.set("Suresh", 37)
    ii.set("Priya", 34)
    ii.set("Kushi", 2)

    for((key, value) in ii) {
        println("$key is $value")
    }
}

private fun illegal() {
    try {
        val s: JavaRunClass = JavaRun.get()

        println(s.display())
    }catch(e: IllegalStateException) {
        println(e.toString())
    }
}

private fun forloops() {
    for (i in 1..20) {
        print(i)
    }
    print("##")

    for (i in 1..20 step 5) {
        print(i)
    }
    print("##")

    for (i in 20 downTo 0 step 5) {
        print(i)
    }
    print("##")

    for (i in 10 until 20) {
        print(i)
    }
    print("##")
}


fun demo() {
    val p = Person("Suresh")

    p.displayName(::display)

    p.displayName { s-> println(s) }
}

fun display(s: String) {
    println(s)
}

class Person(val name: String) {
    fun displayName(func : (String) -> Unit) {
        func(name)
    }
}