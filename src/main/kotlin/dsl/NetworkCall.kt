package dsl

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

fun main() {
    networkCall<String, String> {
        System.out.println("calling networkCall")
    }
}

fun<RESPONSE: Any, DATA: Any> networkCall(block : () -> Unit) {
    block()
}