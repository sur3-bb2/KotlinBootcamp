package dsl.p

data class Person(var name: String? = null,
                  var age: Int? = null,
                  var address: Address? = null)


data class Address(var street: String? = null,
                   var number: Int? = null,
                   var city: String? = null)

fun person(block : Person.() -> Unit) : Person
    = Person().apply(block)

fun Person.address(block: Address.() -> Unit): Address
    = Address().apply(block).apply {
        address = this
    }

fun main() {
    val p = person {
        name = "Suresh"
        age = 36
        address {
            street = "ITPL"
            number = 123
            city = "Bengaluru"
        }
    }

    println(p.toString())
}