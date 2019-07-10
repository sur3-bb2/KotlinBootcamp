fun demo() {
    JavaRun.runNow {
        println("passing to Java")
    }
}

fun demo1() {
    val runnable = object: Runnable {
        override fun run() {
            println("passing to Java")
        }
    }

    JavaRun.runNow(runnable)

    demoObject.demo()
}


val demoObject: DemoObject = object : DemoObject() {
    override fun demo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

abstract class DemoObject {
    abstract fun demo()
}