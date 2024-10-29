package contacts

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

class ContactBook {

    private val a = """[^\+?][A-Za-z0-9]{2,}(\s|-)?(\(?[A-Za-z0-9]{2,}\)?)?((\s|-)?[A-Za-z0-9]{2,})*"""
    private val b = """(^\+)?\(?[A-Za-z0-9]{1,}\)?((\s|-)?[A-Za-z0-9]{2,})*"""
    private val c = """\([A-Za-z0-9]{2,}\)((\s|-)?[A-Za-z0-9]{2,})*"""
    private val d = """\+?\d{1,}(\s|-)?\(?\d{3}\)?(\s|-)?\d{3}(\s|-)?\d{3}(\s|-)?\d{4}"""

    private val concat = "$a|$b|$c|$d"

    private val regex = Regex(concat)

    private val contacts = mutableListOf<BaseContact>()
    private var record = mutableListOf<BaseContact>()

    private var birthday = ""
        set(value) {
            if (value.isNotBlank()) {
                field = value
            } else {
                field = "[no data]"
                println("Bad birth date!")
            }

        }

    private var gender = ""
        set(value) {
            if (value == "M" || value == "F") {
                field = value
            } else {
                field = "[no data]"
                println("Bad gender!")
            }
        }

    private var phoneNumber: String = ""
        set(value) {
            if (value != "") {
                field = value
            } else {
                field = "[no number]"
                println("Wrong number format!")
            }

        }


    private fun addPerson() {
        val name = print("Enter the name:").run { readln() }
        val surname = print("Enter the surname:").run { readln() }
        birthday = print("Enter the birth date:").run { readln() }
        gender = print("Enter the gender (M, F):").run { readln() }
        phoneNumber = print("Enter the number:").run { readln() }
        val timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
        val person = Person(
            name = name,
            surname = surname,
            birthday = birthday,
            gender = gender,
            phoneNumber,
            createdTime = timeNow,
            modifiedTime = timeNow
        )
        contacts.add(person)
        println("The record added.")

    }

    private fun addOrganization() {
        val name = print("Enter the name:").run { readln() }
        val address = print("Enter the address:").run { readln() }
        phoneNumber = print("Enter the number:").run { readln() }
        val timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
        val organization =
            Organization(name = name, address = address, phoneNumber, createdTime = timeNow, modifiedTime = timeNow)
        contacts.add(organization)
        println("The record added.")
    }

    fun add() {
        when (readln()) {
            "person" -> {
                addPerson()
            }

            "organization" -> {
                addOrganization()
            }
        }
        println()
    }


    fun remove() {
        if (contacts.size == 0) {
            println("No records to remove!")
        } else {
            contacts.forEachIndexed { index, _ ->
                when (val entity = contacts[index]) {
                    is Person -> println("${index + 1}. ${entity.name} ${entity.surname}, ${entity.phoneNumber}")
                    is Organization -> println("${index + 1}. ${entity.name} ${entity.address}, ${entity.phoneNumber}")
                }
                print("Select a record:")
                val n = readln().toInt() - 1
                contacts.removeAt(n)
                print("The record removed!")
            }
        }
        println()
    }

    fun edit() {
        var temp: BaseContact? = null
        if (contacts.size == 0) {
            println("No records to edit!")
        } else {
            contacts.forEachIndexed { index, type ->
                when (type) {
                    is Person -> println("${index + 1}. ${type.name}")
                    is Organization -> println("${index + 1}. ${type.name}")
                }
            }
            print("Select a record:")
            val n = readln().toInt() - 1
            temp = contacts[n]
            contacts[n].edit()
        }
        println("Saved")
        print(temp?.detailedInfo())
        println()
    }


    fun count() {
        println("The Phone Book has ${contacts.size} records.")
        println()
    }

    fun info() {
        contacts.forEachIndexed { index, baseContact ->
            baseContact.info(index)
        }
    }

    fun listOption(phoneBookUI: Ui) {
        try {
            val input = readln().toInt() - 1
            val contact = contacts[input]
            contact.detailedInfo()
            println()
            while (true) {
                phoneBookUI.displayRecordMenu()
                when (readln()) {
                    "edit" -> {
                        contact.edit()
                        println("The record updated!")
                        println()
                    }

                    "delete" -> {
                        contacts.removeAt(input)
                        println("The record removed!")
                        println()
                        break
                    }

                    "menu" -> break
                    else -> println("Invalid action.")
                }
            }
        } catch (e: Exception) {
            return
        }
    }


    fun searchOption(phoneBookUI: Ui) {
        try {
            val input = readln().toInt() - 1
            val contact = contacts[input]
            contact.detailedInfo()
            println()
            while (true) {
                phoneBookUI.displayRecordMenu()
                when (readln()) {
                    "edit" -> {
                        contact.edit()
                        println("The record updated!")
                        println()
                    }

                    "delete" -> {
                        contacts.removeAt(input)
                        println("The record removed!")
                        println()
                        break
                    }

                    "menu" -> break
                    else -> println("Invalid action.")
                }
            }
        } catch (e: Exception) {
            return
        }
    }

    fun search() {
        print("Enter search query: ")
        val query = try {
            Regex(readln(), RegexOption.IGNORE_CASE)
        } catch (e: Exception) {
            println("Invalid syntax!")
            return
        }

        val results = contacts.filter { contact ->
            contact.getPossibleProperties().any {
                query.containsMatchIn(it)
            }
        }
        if (results.isEmpty()) {
            println("No matching records found.")
        } else {
            results.forEachIndexed { index, contact ->
                contact.info(index)
            }
        }

    }

    private fun isNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.matches(regex)
    }
}