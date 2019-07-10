package Aquarium

abstract class AquariumFish {
    abstract val color: String
}

class GoldFish : AquariumFish(), FishAction {
    override fun eat() {
        println("munch")
    }

    override val color: String
        get() = "Gold"
}

class SharkFish : AquariumFish(), FishAction {
    override fun eat() {
        println("hunt and eat")
    }

    override val color: String
        get() = "Gray"
}

interface  FishAction {
    fun eat()
}

fun makeFish() {
    val shark = SharkFish()
    val gold = GoldFish()

    println("shar color ${shark.color}, gold color ${gold.color}")

    gold.eat()
    shark.eat()
}

fun feedFish(fish: FishAction) {
    fish.eat()
}