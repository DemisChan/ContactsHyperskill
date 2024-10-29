package contacts


fun main() {
    val contacts = ContactBook()
    val phoneBookUI = Ui()
    fun processAction(action: Action, contacts: ContactBook, phoneBookUI: Ui) {
        when (action) {
            Action.ADD -> {
                phoneBookUI.displayAddMenu(Action.ADD)
                contacts.add()
            }
            Action.REMOVE -> contacts.remove()
            Action.EDIT -> contacts.edit()
            Action.SEARCH ->  {
                contacts.search()
                println()
                phoneBookUI.displaySearchMenu(Action.SEARCH)
                contacts.searchOption(phoneBookUI)
            }
            Action.COUNT -> contacts.count()
            Action.LIST -> {
                contacts.info()
                println()
                phoneBookUI.displayListMenu(Action.LIST)
                contacts.listOption(phoneBookUI)
                println()
            }
            Action.MENU -> return
            Action.EXIT -> return
        }
    }
    var loop = true
    while (loop) {
        phoneBookUI.displayMainMenu(Action.MENU)
        val actionInput = readln()
        val action = try {
            loop = !(actionInput == "exit" || actionInput == "EXIT")
            Action.valueOf(actionInput.uppercase())
        } catch (e: IllegalArgumentException) {
            println("Invalid action.")
            continue
        }
        processAction(action, contacts, phoneBookUI)
    }
}