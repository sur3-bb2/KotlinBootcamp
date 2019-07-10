package pluralsight

import java.math.BigInteger


fun main(args: Array<String>) {
    val i1 = Singleton.getSingleton()
    val i2 = Singleton.getSingleton()
    val i3 = Singleton.getSingleton()
    val i4 = Singleton.getSingleton()


    println((i1 == i2) && (i3 == i4) && (i1 == i4))

    //allCourses.compare()
}

class Singleton {
    var name: String

    init {
        name = "Suresh"
    }

    object SingletonFactory {
        val single = Singleton()
    }

    companion object {
        fun getSingleton() : Singleton? {
            return SingletonFactory.single
        }
    }
}

interface SomeTime {
    fun getTime() {}
    fun getTime(s: String) {}
}

interface SomeOtherTime {
    fun getTime() {}

    fun getTime(s: String) {}
}

class AllTimes: SomeTime, SomeOtherTime {
    override fun getTime(s: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getTime() {
        //super<SomeTime>.getTime()
        val e = SyncEvent()
    }

}

sealed class Event {
    fun send(e: Event) {

    }
}

class SyncEvent : Event() {

}

class AsyncEvent : Event() {

}


interface CaseInsensitiveCompator<T> {
    fun compare(fromThis: T, fromThat: T) : Boolean
}

data class Course(var name: String)

object allCourses : CaseInsensitiveCompator<Course> {
    val courses = arrayListOf<Course>()

    override fun compare(fromThis: Course, fromThat: Course): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun initialize() {
        courses.add(Course("Maths"))
        courses.add(Course("Science"))
        courses.add(Course("English"))

        val myCourse = MyCourse()

        val c = MyCourse.createMe()
    }
}

class MyCourse {
    //singleton, accessible within class only
    object helper {
        val a  = 10

        fun aa(a: Int) {

        }
    }

    // useful for factory objects and static members
    companion object : CaseInsensitiveCompator<MyCourse> {
        override fun compare(fromThis: MyCourse, fromThat: MyCourse): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        fun createMe() : MyCourse {
            return MyCourse()
        }
    }

    fun callHelper() {
        MyCourse.helper.aa(helper.a)
    }
}