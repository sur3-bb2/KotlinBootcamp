package hof

fun main(args: Array<String>) {
    var game = Game()

    /*game.north()
    game.south()
    game.west()
    game.east()
    game.end()*/

    print("Enter a direction: n/s/e/w:")
    game.makeMove(readLine()?.trim())
}

class Game {
    var path = mutableListOf(Directions.START)

    val north = { path.add(Directions.NORTH) }
    val south = { path.add(Directions.SOUTH) }
    val west = { path.add(Directions.WEST) }
    val east = { path.add(Directions.EAST) }
    val end: () -> Boolean = {
        path.add(Directions.END)
        println("game over")
        println(path)
        path.clear()

        false
    }

    fun move(where : () -> Boolean) {
        println("moving ${where::class.simpleName}")
        where()
    }

    fun makeMove(input : String?) {
        when(input) {
            "n" -> move(north)
            "s" -> move(south)
            "w" -> move(west)
            "e" -> move(east)
            else -> move(end)

        }
    }
}

enum class Directions {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    START,
    END
}