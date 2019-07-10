fun canAddFish(tankSize: Double, currentFish: List<Int>,
                fishSize: Int = 2, hasDecorations: Boolean = true) : Boolean {

    val leftSize = tankSize
                    .minus(if (hasDecorations) 2 else 0)
                    .minus(currentFish.sum())

    return leftSize >= fishSize
}

fun shouldChangeWater(
    day: String,
    temperature: Int = 22,
    dirty: Int = 20
): Boolean {

    // this works if.. elseif.. elseif... else   when there is no argument for'when'

    //if(temp > 30 ) else if(dirty > 30) else if(day) else....
    return when {
        temperature > 30 -> true
        dirty > 30 -> true
        day.toLowerCase() == "sunday" -> true
        else -> false
    }
}

fun getDirtySensor() = 42

fun shouldChangeWater2(
        day: String,
        temperature: Int = 22,
        dirty: Int = 20,
        dirtySensor: Int = getDirtySensor()): Boolean {

    val isTooHot = temperature > 30
    val isTooDirty = dirty > 30
    val isSunday = day.toLowerCase() == "sunday"

    return when {
        isTooHot(temperature) -> true
        isTooDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}

fun isTooHot(temperature: Int) = temperature > 35

fun isTooDirty(dirty: Int) = dirty > 30

fun isSunday(day: String) = day.toLowerCase() == "sunday"

fun whatShouldIDoToday(mood: String, weather: String = "sunny", temperature: Int = 24) : String {

    val isSunday = { day: String -> day.toLowerCase() == "sunday" }

    val mood = readLine()!!


    return when {
        shoudGoForWalk(mood, weather) -> "go for a walk"
        shoudStayInBed(mood, weather, temperature) -> "stay in bed"
        isTooHot(temperature) -> "go swimming"
        else -> "Stay home and read."
    }
}


fun shoudStayInBed(mood: String, weather: String, temperature: Int) =
        mood == "sad" && weather == "rainy" && temperature == 0

fun shoudGoForWalk(mood: String, weather: String) = mood == "happy" && weather == "Sunny"


fun filterExample() {
    val decorations = listOf<String>("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

    val eager = decorations.filter { it.startsWith('p') }

    println(eager)

    val lazy = decorations.asSequence().filter {
        println(it)
        it.startsWith('p')
    }


    //println(lazy.first()) // this will iterate rock and pagoda and stops there.. last() will iterate whole list
    val it = lazy.iterator()

    while(it.hasNext()){
        println("found " +  it.next())
    }
    //println(lazy.iterator().next())
}

fun mySpices() {
    val spices = listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper" )

    spices.filter { it.contains("curry") }.sortedBy { it.length }
    //spices.sortedBy { it.length }

    spices.filter { it.startsWith('c') && it.endsWith('e') }
    spices.filter{it.startsWith('c')}.filter{it.endsWith('e')}

    //spices.filter { it[0] == 'c' && it[it.length - 1] == 'e' }

    spices.take(3).filter { it.startsWith('c') }
}