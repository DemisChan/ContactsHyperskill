package contacts

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Organization(
    override var name: String,
    var address: String,
    override var phoneNumber: String,
    override var createdTime: String,
    override var modifiedTime: String
) : BaseContact(name, phoneNumber, createdTime, modifiedTime) {
    override fun setProperty(propertyName: String, value: Any) {
        when (propertyName) {
            "name" -> name = value as String
            "address" -> address = value as String
            "number" -> phoneNumber = value as String
        }
    }
    override fun getAdditionalProperties(): List<String> {
        return listOf(name, address)
    }

    override fun getProperty(propertyName: String): Any? {
        TODO("Not yet implemented")
    }

    override fun edit() {
        print("Select a field (name, address, number): ")
        when (readln()) {
            "name" -> this.name = readln()
            "address" -> this.address = readln()
            "number" -> {
                print("Enter the number: ")
                this.phoneNumber = readln()
            }
        }
        this.modifiedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
    }

    override fun info(index: Int) {
        println("${index + 1}. ${name}")
    }

    override fun detailedInfo() {
        println(
            "Organization name: ${this.name}" +
                    "\nAddress: ${this.address}" +
                    "\nNumber: ${this.phoneNumber}" +
                    "\nTime created: ${this.createdTime}" +
                    "\nTime last edit: ${this.modifiedTime}"
        )
    }
}