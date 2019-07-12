package delegates

import kotlin.reflect.KProperty

class Example {
    var p : String by Delegate()
}

/**
 * There's some new syntax: you can say `val 'property name': 'Type' by 'expression'`.
 * The expression after by is the delegate, because get() and set() methods
 * corresponding to the property will be delegated to it.
 * Property delegates don't have to implement any interface, but they have
 * to provide methods named getValue() and setValue() to be called.</p>
 */
class Delegate {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}

/**
 * Delegates.lazy() is a function that returns a delegate that implements a lazy property:
 * the first call to get() executes the lambda expression passed to lazy() as an argument
 * and remembers the result, subsequent calls to get() simply return the remembered result.
 * If you want thread safety, use blockingLazy() instead: it guarantees that the values will
 * be computed only in one thread, and that all threads will see the same value.
 */
class LazySample {
    val lazy: String by lazy {
        println("computed!")
        "my lazy"
    }
}

fun main(args: Array<String>) {
    val e = Example()
    println(e.p)
    e.p = "NEW"

    val sample = LazySample()
    println("lazy = ${sample.lazy}")
    println("lazy = ${sample.lazy}")
}