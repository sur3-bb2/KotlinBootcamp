package Aquarium

class Spice(val name: String, val spiceness: String = "mild") {

    private var spiceLevels: Map<String, Int> = mapOf<String, Int>(
            Pair("mild", 1),
            Pair("medium", 2),
            Pair("spicy", 3),
            Pair("very spicy", 4),
            Pair("super spicy", 5)
    )

    val heat: Int
        get() {
            return spiceLevels.getOrDefault(spiceness, 0)
        }

    init {
        println("Created Spice object $name with spiceness $spiceness")
    }
}

fun makeSpices() {
    val spices1 = listOf(
            Spice("curry", "mild"),
            Spice("pepper", "medium"),
            Spice("cayenne", "spicy"),
            Spice("ginger", "mild"),
            Spice("red curry", "medium"),
            Spice("green curry", "mild"),
            Spice("hot pepper", "extremely spicy")
    )

    val spicelist = spices1.filter {it.heat < 3}
}