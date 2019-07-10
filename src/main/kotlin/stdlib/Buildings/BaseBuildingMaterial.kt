package stdlib.Buildings

import java.util.*

open class BaseBuildingMaterial(var numberNeeded: Int = 1, val name : String)

class Wood: BaseBuildingMaterial(4, "Wood")

class Brick: BaseBuildingMaterial(8, "Brick")

class Building<T : BaseBuildingMaterial>(var buildingMaterial : T) {
    private val baseMaterialsNeeded : Int = 100
    private val actualMaterialsNeeded: Int = baseMaterialsNeeded.times(buildingMaterial.numberNeeded)

    fun build() {
        println("$actualMaterialsNeeded ${buildingMaterial.name} required")
    }
}

fun <T: BaseBuildingMaterial> isSmallBuilding(building: Building<T>) =
        if(building.buildingMaterial.numberNeeded < 500) "small building" else "large building"


fun main(args: Array<String>) {
    labels()

    val d: Double
    val wood = Wood()
    Building<Wood>(wood).build()

    genericExample()
}



open class WaterSupply(var needsProcess: Boolean = true)

class TapWater: WaterSupply(true) {
    fun addChemicals() {
        needsProcess = false
    }
}

class FishWater : WaterSupply(false)

class LakeWater: WaterSupply(true) {
    fun filter() = apply { needsProcess = false }
}

inline fun <reified R: WaterSupply> WaterSupply.isOfType() = this is R

class Aquarium<out T : WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        /*check(!waterSupply.needsProcess) {
            "water needs to be processed"
        }*/

        if(waterSupply.needsProcess) cleaner.clean(waterSupply)

        println("adding water")
    }

    inline fun <reified R> hasWaterSupplyOfType() = waterSupply is R
}

inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("add item")

fun <T> isWaterClean(aquarium: Aquarium<T>) where T: WaterSupply {
    println("${!aquarium.waterSupply.needsProcess}")
}

interface Cleaner<in T : WaterSupply> {
    fun clean(waterSupply: T)
}

interface Cleaner1<out T : WaterSupply> {
    fun <T : WaterSupply> clean(c : T) : Cleaner<T>
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) {
        waterSupply.addChemicals()
    }
}

fun genericExample() {
    val a1 = Aquarium<TapWater>(TapWater())
    val a2 = Aquarium(FishWater())

    //a1.addWater(TapWaterCleaner())
    isWaterClean(a1)
    isWaterClean(a2)
    println("tap water == ${a1.hasWaterSupplyOfType<TapWater>()}")
    println(a1.hasWaterSupplyOfType<FishWater>())
    println(a2.hasWaterSupplyOfType<FishWater>())
    //println(a1.waterSupply.needsProcess)
    //println(a2.waterSupply.needsProcess)
    println("oh my extension ${a1.waterSupply.isOfType<TapWater>()}")

    addItemTo(a1)
}

fun copy(from: Array<out Any>, to: Array<in Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}

fun labels() {
    label@for(i in 1..100) {
        for(j in 1..100) {
            if(i > 10) break@label
            println("$i == $j")
        }
    }



    println("break to label")
}