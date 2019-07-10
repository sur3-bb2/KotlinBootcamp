package Aquarium

import kotlin.math.PI

open class Aquarium(var height: Int = 40, var width: Int = 20, var length: Int = 100) {
    open var volume : Int
        get() {
            return height * width * length / 1000
        }
        set(value) {
            height = value
        }

    open var water = volume * 0.9

    constructor(numberOfFish : Int) : this() {
        val waterx = numberOfFish
    }
}

class TowerTank() : Aquarium() {
    override var water = volume * 0.8

    override var volume : Int
        get()  = (height * width * length / 1000 * PI).toInt()
        set(value) {
            height = value
        }

}