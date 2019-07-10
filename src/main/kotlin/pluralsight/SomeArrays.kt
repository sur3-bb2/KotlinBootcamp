package pluralsight

fun main(args: Array<String>) {
    var ints: Array<Int> = arrayOf(1, 2, 3, 4, 5)

    var intss = IntArray(2)
    intss[1] = 23

    var int3 = intArrayOf(1, 2, 3, 4, 5)

    var b1 = ByteArray(2)

    var a1 = Array(int3.size) {
        it * 2
    }

    println(a1[2])
}