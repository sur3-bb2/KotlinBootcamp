import kotlin.reflect.KClass

open class SingletonHolder<out T: Any, in A: Any>(creator: (A) -> T) {
    private var creator : ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg : A) : T {
        val i1 = instance

        if(i1 != null) {
            return i1
        }

        return synchronized(this) {
            val i2 = instance

            if(i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}

class Context(val label: String)

class Manager private constructor(val context: Context) {
    companion object : SingletonHolder<Manager, Context>(::Manager)
}

open class RoomDatabase

class UserDao

class UserDatabase : RoomDatabase() {
    lateinit var context: Context

    fun print() {
        println(context.label)
    }

    companion object : SingletonHolder<UserDatabase, Context>({
        UserDatabase().apply { context = it }
    })
}

/**
 *  Note: When the builder doesn’t require an argument, you can simply use a lazy delegated property instead:
 */

class Retrofit {
    fun baseUrl(url: String) = this

    fun build() = this

    fun <T> create(a : Class<T>)= a.constructors.first().newInstance() as T

    companion object {
        fun Builder() = Retrofit()
    }
}

interface GitHubService {
    companion object {
        val instance: GitHubService by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .build()
            retrofit.create(GitHubService::class.java)
        }
    }
}

fun main() {
    val m1 = Manager.getInstance(Context("Manager"))
    val m2 = Manager.getInstance(Context("Manager2"))

    println(m1 == m2)
    println(m1.context.label)
    println(m2.context.label)

    UserDatabase.getInstance(Context("UserDatabase")).print()
    UserDatabase.getInstance(Context("UserDatabase 2")).print()
}