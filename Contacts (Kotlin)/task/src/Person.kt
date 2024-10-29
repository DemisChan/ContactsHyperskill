package contacts

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Person(
    override var name: String,
    var surname: String,
    var birthday: String,
    var gender: String,
    override var phoneNumber: String,
    override var createdTime: String,
    override var modifiedTime: String
): BaseContact() {
    override fun setProperty(propertyName: String, value: Any) {
        when (propertyName) {
            "name" -> name = value as String
            "surname" -> surname = value as String
            "birth" -> birthday = value as String
            "gender" -> gender = value as String
            "number" -> phoneNumber = value as String
        }
    }

    override fun getAdditionalProperties(): List<String> {
        return listOf(name,surname, birthday, gender)
    }

    override fun getProperty(propertyName: String): Any? {
        TODO("Not yet implemented")
    }

    override fun edit() {
        print("Select a field (name, surname, birth, gender, number): ")
        when (readln()) {
            "name" -> this.name = readln()
            "surname" -> this.surname = readln()
            "birth" -> {
                print("Enter the birth date: ")
                this.birthday = readln()
            }

            "gender" -> {
                print("Enter the gender (M, F): ")
                this.gender = readln()
            }

            "number" -> {
                print("Enter the number: ")
                this.phoneNumber = readln()
            }
        }
        this.modifiedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
    }

    override fun info(index: Int) {
        println("${index + 1}. ${name} ${surname}")
    }

    override fun detailedInfo() {
        println(
            "Name: ${this.name}" +
                    "\nSurname: ${this.surname}" +
                    "\nBirth date: ${this.birthday}" +
                    "\nGender: ${this.gender}" +
                    "\nNumber: ${this.phoneNumber}" +
                    "\nTime created: ${this.createdTime}" +
                    "\nTime last edit: ${this.modifiedTime}"
        )
    }
}