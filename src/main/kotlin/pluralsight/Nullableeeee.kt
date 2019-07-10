package pluralsight

interface IP {

}

class P: IP {
    fun d() {

    }
}

fun doP2(p: P) {
    p.d()
}

fun doP(p: P?) {
    p?.d()
}

fun demos() {
    var p = P()
    val pn : P? = P()

    val pp = p as? IP

    p = pn ?: P()

    doP(p)
    doP(pn)

    pn?.run {
        doP2(pn)
    }

    pn?.let {
        doP2(pn)
    }

    println(pp)
}