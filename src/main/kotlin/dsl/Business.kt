package dsl.B

data class Business(
        val name: String,
        val address: Address,
        val employees: List<Employee>
)

data class Address(
        val street: String,
        val city: String
)

data class Employee(
        val name: String,
        val id: String,
        val title: String,
        val salary: Int
)


class AddressBuilder {
    private var street: String = ""
    private var city: String = ""

    fun street(lambda: () -> String)  {
        this.street = lambda()
    }

    fun city(lambda: () -> String)  {
        this.city = lambda()
    }

    fun build() = Address(street, city)

}

class EmployeeBuilder {
    private var name: String = ""
    private var id: String = ""
    private var title: String = ""
    private var salary: Int = 0

    fun name(lambda: () -> String) { this.name = lambda() }
    fun id(lambda: () -> String) { this.id = lambda() }
    fun title(lambda: () -> String) { this.title = lambda() }
    fun salary(lambda: () -> Int) { this.salary = lambda() }
    fun build() = Employee(name, id, title, salary)
}

fun address(block: AddressBuilder.() -> Unit) : Address  = AddressBuilder().apply(block).build()