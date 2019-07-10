package hof

import java.io.File

fun main(args: Array<String>) {
    val letDemo = MyClass()

    letDemo.testLet()

    val i = EventBarcodeError("something")
}

class EventBarcodeError(val type: String, val header: String? = null, val errorMessage: String? = null)

class MyClass {
    fun testLet() {
        val str : String = "some string"

        val value = str.let {
            println(this)
            println(it)
            42
        }
        println("--------let end-------------")

        val value1 = str.also {
            println(this)
            println(it)
            42
        }
        println("----------also end-----------")


        val value2 = with(this) {
            println(this)
            //println(it)
            42
        }
        println("--------with end-------------")

        str.run {
            println(this)
        }
        println("-----run end----------------")


        this.run {
            println(this)
        }
        println("-----run extension end----------------")

        println(value)
        println(value1)
        println(value2)
    }
}

fun test() {
    var mood = "I am sad"

    run {
        val mood = "I am happy"
        println(mood) // I am happy
    }
    println(mood)  // I am sad
}

class LetvsApply {
    val original = "abc"

    fun demo() {
// Evolve the value and send to the next chain
        original.let {
            println("The original String is $it") // "abc"
            it.reversed() // evolve it as parameter to send to next let
        }.let {
            println("The reverse String is $it") // "cba"
            it.length  // can be evolve to other type
        }.let {
            println("The length of the String is $it") // 3
        }
// Wrong
// Same value is sent in the chain (printed answer is wrong)
        original.also {
            println("The original String is $it") // "abc"
            it.reversed() // even if we evolve it, it is useless
        }.also {
            println("The reverse String is ${it}") // "abc"
            it.length  // even if we evolve it, it is useless
        }.also {
            println("The length of the String is ${it}") // "abc"
        }
// Corrected for also (i.e. manipulate as original string
// Same value is sent in the chain
        original.also {
            println("The original String is $it") // "abc"
        }.also {
            println("The reverse String is ${it.reversed()}") // "cba"
        }.also {
            println("The length of the String is ${it.length}") // 3
        }
    }

    // Normal approach
    fun makeDir(path: String): File {
        val result = File(path)
        result.mkdirs()
        return result
    }

    // Improved approach
    fun makeDir1(path: String?) = path?.let{ File(it) }.also{ it?.mkdirs() }
}
class Intent() {
    var action: String? = null
    var data : Any? = null
    lateinit var soapMessage : String
    var simple: String? = null

    constructor(simple: String): this() {
        this.simple = simple
    }
}

object Uri {
    fun parse(data: String) {

    }
}

class Apply {
    // Normal approach
    fun createIntent1(intentData: String, intentAction: String): Intent {
        val intent = Intent()
        intent.action = intentAction
        intent.data= Uri.parse(intentData)
        return intent
    }



    // Improved approach, chaining
    fun createIntent(intentData: String, intentAction: String) =
            Intent().apply { action = intentAction }
                    .apply { data = Uri.parse(intentData) }
}