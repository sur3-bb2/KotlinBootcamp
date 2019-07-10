package pluralsight

fun main(args: Array<String>) {
    flatmap()
    var total = 0

    fib(8, object : Action {
        override fun execute(value: Int) {
            println(value)
        }

    })

    fib1(8) { println(it) }

    fib1(8, ::println)

    fib1(8) { total += it }

    println("total $total")


    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val grThanThree = { it : Int -> it > 3 }
}

interface Action {
    fun execute(value: Int)
}

fun fib(upTo : Int, action: Action) {
    var prev = 0
    var prevprev = 0
    var current = 1

    for(i in 1..upTo) {
        action.execute(current)

        prevprev = prev
        prev = current
        current = prev + prevprev
    }
}

fun fib1(upTo : Int, action: (Int) -> Unit) {
    var prev = 0
    var prevprev = 0
    var current = 1

    for(i in 1..upTo) {
        action(current)

        prevprev = prev
        prev = current
        current = prev + prevprev
    }
}

class Meeting(val id: Int, val title: String) {
    val people = listOf(Persona("Suresh"), Persona("Priya"), Persona("Kushi"))
}

class Persona(val name: String)

fun flatmap() {
    val meetings  = listOf(Meeting(1, "Board meeting"), Meeting(2, "Team meeting"))
    val listofpersons = meetings.map { it.people }
    val persons = meetings.flatMap { it.people }

    println(persons[2].name)
}