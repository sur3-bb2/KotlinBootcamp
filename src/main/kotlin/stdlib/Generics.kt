package stdlib

fun main(args: Array<String>) {

}

class MyList<T> {
    var myItem: T? = null

    var item: T?
        get() = myItem
        set(value) {
            myItem = value
        }
}