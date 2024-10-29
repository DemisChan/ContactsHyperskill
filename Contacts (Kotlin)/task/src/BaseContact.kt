package contacts

abstract class BaseContact(
    open var name: String = "",
    open var phoneNumber: String = "",
    open var createdTime: String = "",
    open var modifiedTime: String = ""
) {
    abstract fun setProperty(propertyName: String, value: Any)

    abstract fun getAdditionalProperties(): List<String>

    fun getPossibleProperties(): List<String> {
        return listOf(
            name,
            phoneNumber,
            createdTime,
            modifiedTime
        ) + getAdditionalProperties()
    }


    abstract fun getProperty(propertyName: String): Any?

    abstract fun edit()

    abstract fun info(index: Int)

    abstract fun detailedInfo()

}