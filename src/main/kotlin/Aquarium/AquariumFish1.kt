package Aquarium

interface FishColor {
    val color: String
}

interface FishActions {
    fun eat()
}


//Singletom
object GoldColor: FishColor {
    override val color: String
        get() = "Gold"
}

//Singletom
object GrayColor: FishColor {
    override val color: String
        get() = "Gray"
}

class PlectoFishAction(val food: String) : FishActions {
    override fun eat() {
        println(food)
    }

}

fun temo() {
    val color: String = GoldColor.color;
}

class Plecto(fishColor: FishColor = GoldColor) :
        FishColor by fishColor,
        FishActions by PlectoFishAction("a lot of algae")


fun makePlecto() = Plecto()


