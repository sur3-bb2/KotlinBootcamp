package Destructuring

data class User(val name: String, val id: Int)

fun getUser(): User {
    return User("Alex", 1)
}

fun traverseMap() {
    val map = hashMapOf<String, Int>()
    map.put("one", 1)
    map.put("two", 2)

    for ((key, value) in map) {
        println("key = $key, value = $value")
    }
}

fun autoGen() {
    val user = User("Alex", 1)
    println(user) // toString()

    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)

    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")

    // copy() function
    println(user.copy())
    println(user.copy("Max"))
    println(user.copy(id = 2))
    println(user.copy("Max", 2))
}

fun main(args: Array<String>) {
    val user = getUser()
    println("name = ${user.name}, id = ${user.id}")

    // or

    val (name, id) = getUser()
    println("name = $name, id = $id")

    // or

    println("name = ${getUser().component1()}, id = ${getUser().component1()}")


    traverseMap()

    autoGen()
}