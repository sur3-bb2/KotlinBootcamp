package Aquarium

class SimpleSpice {
    var name: String = "curry"
    var spiciness: String = "mild"

    private var spiceLevels: Map<String, Int> = mapOf<String, Int>(Pair("mild", 4), Pair("spicy", 5))

    val heat: Int
        get() {
            return spiceLevels.getOrDefault(spiciness, 4)
        }
}