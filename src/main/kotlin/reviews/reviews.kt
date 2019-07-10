package reviews

class ErrorAlert(val errorCode: String)

object CheckoutError {
    val UNKNOWN = ""
}


fun main(args: Array<String>) {
    val errors = listOf(ErrorAlert("123456"))
    val s0 = ServiceError(errorAlerts = errors, code = "123")

    println(s0.getErrorCode())

    val s1 = ServiceError(code = "123")

    println(s1.getErrorCode())

    val s2 = ServiceError(code = "  ")

    println(s2.getErrorCode())
}


data class ServiceError @JvmOverloads constructor(
        val errorAlerts: List<ErrorAlert?>? = null,
        val message: String = "",
        val code: String
) {

    fun firstError(): ErrorAlert? = errorAlerts?.first()

    val codeOrNull = if (code.isBlank()) null else code

    fun getErrorCode(): String = errorAlerts?.firstOrNull()?.errorCode ?: codeOrNull ?: CheckoutError.UNKNOWN
}

