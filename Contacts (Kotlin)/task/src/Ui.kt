package contacts

enum class Action {
    ADD,
    REMOVE,
    EDIT,
    SEARCH,
    COUNT,
    LIST,
    MENU,
    EXIT
}


class Ui {
    fun displayMainMenu(action: Action) {
        print("[${action.name.lowercase()}] Enter action (add, list, search, count, exit): ")
    }

    fun displayAddMenu(action: Action) {
        print("[${action.name.lowercase()}] Enter the type (person, organization): ")
    }

    fun displayListMenu(action: Action) {
        print("[${action.name.lowercase()}] Enter action ([number], back): ")
    }

    fun displayRecordMenu() {
        print("[record] Enter action (edit, delete, menu): ")
    }
    fun displaySearchMenu(action: Action) {
        print("[${action.name.lowercase()}] Enter action ([number], back, again): ")
    }
}