package stdlib.Buildings

open class Geometry(val id: Int)

open class Shape(val type: String = "Shape", id: Int) : Geometry(id)

class Circle(id: Int) : Shape("Circle", id)

fun main(args: Array<String>) {
    demo()
}

interface Comparator<in T> {
    fun compare(one: T, two : T) : Boolean
}

class ShapeComparator: Comparator<Shape> {
    override fun compare(one: Shape, two: Shape): Boolean {
        return one.id.equals(two.id)
    }
}

fun demo() {
    val shapeComparator = ShapeComparator()

    val circleCompator: Comparator<Circle> = shapeComparator

    compare(shapeComparator)
}

fun compare(circle: Comparator<Circle>) {
    println("same ${circle.compare(Circle(1), Circle(1))}")

    println("diff ${circle.compare(Circle(21), Circle(1))}")
}