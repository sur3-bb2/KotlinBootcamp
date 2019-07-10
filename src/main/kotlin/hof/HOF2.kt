package hof

fun main(args: Array<String>) {
    demo()
}

data class Fish(var name : String)

fun prints(msg : String) {
    println(msg)
}

fun demo() {
    val f = Fish("gold fish")

    with(f) {
        println(name.capitalize())
        println(name.toUpperCase())
    }

    myWith(f) {
        println(name.capitalize())
    }

    myWith2(f.name) {
        prints(capitalize())
        prints(toUpperCase())
    }

    println(f.run { name.toUpperCase() })
    println(f.run { name.toLowerCase() })
    println(f.run { })

    //run returns the result of executing the lambda, while apply returns object after lambda is applied
    println(f.apply { })
    println(f.name.apply { })

    // common pattern for initializing objects
    val f2 = Fish(name = "some fish").apply { name = "super fish" }

    println(f2)

    f2.let { it.name.capitalize() }.let { it.length }.let { it + 35 }
}

// block is convention, after that class on which HOF is applies, in this case Fish
fun myWith(fish: Fish, block: Fish.() -> Unit) {
    fish.block()
}

// block is convention, after that class on which HOF is applies, in this case Fish
fun myWith2(name: String, block: String.() -> Unit) {
    name.block()
}

fun main2(args: Array<String>) {
    val numbers = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    print(numbers.divisibleBy {
        it.rem(3)
    })
}

fun List<Int>.divisibleBy(block: (Int) -> Int) : List<Int> {
    val result = mutableListOf<Int>()

    for (item in this) {
        if (block(item) == 0) {
            result.add(item)
        }
    }

    return result
}