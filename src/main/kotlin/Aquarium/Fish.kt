package Aquarium

/**
 * all init blocks will be first... and secondary constructors will be called once all 'init' completes
 */
class Fish(val friendly: Boolean = true, val volumeNeeded: Int) {
    init {
        println("first init block")
    }

    constructor() : this(volumeNeeded = 50) {
        println("running secondary constructor")
    }

    var size: Int = 0

    init {
        println("calculating size")

        if(friendly) {
            size = volumeNeeded
        } else {
            size = volumeNeeded * 2
        }
    }

    init {
        println("constructed fish of size $volumeNeeded final size $size")
    }

    init {
        println("final init")
    }
}

fun makeDefaultFish() = Fish()

fun fishExample() {
    val fish = Fish(volumeNeeded = 20)
}