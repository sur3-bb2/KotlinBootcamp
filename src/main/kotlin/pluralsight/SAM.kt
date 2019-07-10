package pluralsight

fun main(args: Array<String>) {
    val user = User()
    var count = 0

    val createdEvent: (User) -> Unit = { it -> println("user created $it, ${++count} times") }
    val createdEvent1 = Created({ println("user created $it, ${++count} times") })

    user.name = "Suresh Babu"

    user.Create { println("user created $it, ${++count} times")}

    user.Create { println("user created $it, ${++count} times ")}

    user.Create(createdEvent)
    user.Create(createdEvent1)
}