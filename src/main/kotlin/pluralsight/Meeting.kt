package pluralsight


open class Meeting1

class BoardMeeting :Meeting1()

class TeamMeeting : Meeting1()

inline fun <reified T : Meeting1> List<*>.typeOf() : List<T> {
    val returnList = mutableListOf<T>()

    for(item in this) {
       if(item is T) {
           returnList.add(item)
       }
    }

    return returnList
}

fun demoReified() {
    val values = listOf(BoardMeeting(), TeamMeeting())

    values.typeOf<BoardMeeting>()
}

fun <T: Meeting> buildMeeting(type: Class<T>, action: T.() -> Unit) : T{
    val instance = type.newInstance()

    instance.action()

    return instance
}

inline fun <reified T: Meeting> buildMeeting(action: T.() -> Unit) : T{
    val instance = T::class.java.newInstance()
    val f: List<Meeting>


    instance.action()

    return instance
}