import Aquarium.Aquarium
import java.util.*

fun main(args: Array<String>) {
    dirtyProcessor()
    filterExample()
    println(canAddFish(10.0, listOf(3,3,3))) //---> false
    println(canAddFish(8.0, listOf(2,2,2), hasDecorations = false)) //---> true
    println(canAddFish(9.0, listOf(1,1,3), 3)) //---> false
    println(canAddFish(10.0, listOf(), 7, true)) //---> true

    swim()
    swim(speed = "slow")
    //println(getFortuneCookie())

    println("hello, world : ${args[0]}, ${args[1]}")

    when(dayOfWeek()) {
        1 -> println("sunday")
        2 -> println("mon")
        3 -> println("tue")
        4 -> println("wed")
        5 -> println("thu")
        6 -> println("fri")
        7 -> println("sat")
    }
    //println(dayOfWeek())

    val isUnit = println("this is an expression")

    println(isUnit)

    val temp = 90

    val isHot = if (temp > 50) true else false

    println("is this fish ${ if (temp > 50) "fried" else "safe" }")

    println("${if (args[1].toInt() < 12) "Good morning, Kotlin" else "Good night, Kotlin" }")
}

fun dayOfWeek(): Int {
    return Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    //println("What day is it today?")
}


fun swim(speed: String = "fast") {
    println("fish swims $speed")
}


fun getFortuneCookie(): String {
    val fortunes = arrayOf("You will have a great day!",
            "Things will go well for you today.",
            "Enjoy a wonderful day of success.",
            "Be humble and all will turn out well.",
            "Today is a good day for exercising restraint.",
            "Take it easy and enjoy life!",
            "Treasure your friends because they are your greatest fortune."
            )


    print("Enter your birthday: ")
    val value = readLine()?.toIntOrNull() ?: 1

    return "Your fortune is : ${fortunes[value.rem(fortunes.size)]}"
}

fun buildAquarium() {
    val aquarium = Aquarium()

    println("height : ${aquarium.height}, width: ${aquarium.width}, length: ${aquarium.length}")

    println("volume : ${aquarium.volume} litres")

    aquarium.volume = 50

    println("height : ${aquarium.height}, width: ${aquarium.width}, length: ${aquarium.length}")

    println("volume : ${aquarium.volume} litres")
}