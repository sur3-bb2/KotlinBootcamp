import java.util.*

var dirtyWater = 30

val waterFilter = { dirty: Int -> dirty / 20 }

val waterFilter2: (Int) -> Int = { dirty -> dirty / 20 }

fun feedFish(dirty: Int) = dirty + 10

fun updateDirty(dirty : Int, operation: (Int) -> Int) : Int {
    return operation(dirty)
}

fun dirtyProcessor() {
    dirtyWater = updateDirty(dirtyWater, waterFilter2)
    dirtyWater = updateDirty(dirtyWater, ::feedFish)  //names functions needs to be passed like this
    dirtyWater = updateDirty(dirtyWater) { //last parameter call syntax, when calling HOF - lambda can be outside of func paranthesis
        dirtyWater -> dirtyWater + 30
    }

    println(dirtyWater)
}

val rollDice: (Int) -> Int = {
    numSides ->
        if(numSides == 0) 0
        else Random().nextInt(numSides) + 1
}

fun gamePlay(roll: Int, rollDice: (Int) -> Int) {
    println("rolled : ${rollDice(roll)}")
}



