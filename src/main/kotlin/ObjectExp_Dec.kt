import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JComponent

/**
    https://kotlinlang.org/docs/reference/object-declarations.html

    Sometimes we need to create an object of a slight modification of some class,
    without explicitly declaring a new subclass for it. Kotlin handles this case with object expressions and object declarations.
 **/



fun main() {
    demoSimpleObjectExpression()
    demoFancyObjectExpression()
    foo()
    demoCompanion()
}

fun demoSimpleObjectExpression() {
    mouseAdapter.mouseClicked(null)
}

fun demoFancyObjectExpression() {
    println(iamA.y)
    iamB.inB()
}

val mouseAdapter = object: MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) {
        super.mouseClicked(e)
        println("mouse clicked")
    }
}

open class A(x: Int) {
    open val y = x
}

interface B {
    fun inB()
}

val iamA: A = object : A(1), B {
    override fun inB() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val y = 20
}

val iamB: B = object : A(1), B {
    override fun inB() {
        println("implemented")
    }

    override val y = 20
}

fun foo() {
    val points = object {
        val x = 0
        val y = 10
    }

    println(points.x + points.y)
}

class C {
    // Private function, so the return type is the anonymous object type
    private fun foo() = object {
        val x: String = "x"
    }

    // Public function, so the return type is Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // Works
        //val x2 = publicFoo().x  // ERROR: Unresolved reference 'x'
    }
}

fun countClicks(window: JComponent) {
    var clickCount = 0
    var enterCount = 0

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            clickCount++
        }

        override fun mouseEntered(e: MouseEvent) {
            enterCount++
        }
    })
    // ...
}

//***********************************

object DefaultListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {  }

    override fun mouseEntered(e: MouseEvent) { }
}

class MyClass {
    companion object Named {
        val x = 20
    }
}

class MyClass1 {
    companion object {
        val x = 30
        val y = x + 30
    }
}

fun demoCompanion() {
    val a = MyClass.Named
    val b = MyClass1.Companion
    val c = MyClass1

    println(a.x)
    println(b.x)
    println(c.y)
}