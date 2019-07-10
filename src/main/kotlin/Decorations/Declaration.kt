package Decorations

fun main(args: Array<String>) {
    makeDeclaration()
}

fun makeDeclaration() {
    val d1 = Declaration("rocky")
    val d2 = Declaration("salt")
    val d3 = Declaration("salt")

    println(d1)
    println(d2)
    println(d3)

    println(d1.equals(d2))
    println(d3.equals(d2))

    val d3copy = d3.copy()

    println(d3copy)

    val e1 = Employee("suresh", 37, "ssn")
    println(e1)

    val(name: String, age: Int, city: String) = e1
    println("$name   $age $city")

}

data class Declaration(val rock: String) {}

data class Employee(val name: String, val age: Int, val ssn: String) {
    val city: String = "bangalore"
    val color: Color = Color.BLUE
    val sex: Sex = Sex.MALE
}

enum class Color() {
    RED,
    BLUE,
    GREEN
}

enum class Sex(val sex: Char) {
    MALE('M'),
    FEMALE('F')
}


//can be sub classed in this file only....
sealed class SeaFish() {

}