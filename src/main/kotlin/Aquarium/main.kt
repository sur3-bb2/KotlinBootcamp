package Aquarium

fun main(args: Array<String>) {
    val p = makePlecto()
    p.eat()
    println(p.color)
    //makeFish()
    //fishExample()
    makeDefaultFish()
    //buildAquarium()
    //buildSpices()
}

fun buildSpices() {
    val spices = SimpleSpice();

    println("name: ${spices.name}, heat: ${spices.heat}")

}

fun buildAquarium() {
    val aquarium = Aquarium()

    println("height : ${aquarium.height}, width: ${aquarium.width}, length: ${aquarium.length}")

    println("volume : ${aquarium.volume} litres")

    aquarium.volume = 50

    println("height : ${aquarium.height}, width: ${aquarium.width}, length: ${aquarium.length}")

    println("volume : ${aquarium.volume} litres")

    val smallAquarium = Aquarium(height = 10, length = 10, width = 10)

    println("smallAquarium height : ${smallAquarium.height}, width: ${smallAquarium.width}, length: ${smallAquarium.length}")

}