package delegates

class Maps(val map: Map<String, Any?>) {
    val name: String by map
    val age : Int by map
}

fun main() {
    val maps = Maps(mapOf(
            "name" to "suresh",
            "age" to 38
    ))

    println("name = ${maps.name}, age = ${maps.age}")
}