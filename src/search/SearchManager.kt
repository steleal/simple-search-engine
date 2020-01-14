package search

class SearchManager(val helper: ConsoleHelper,
                    val peoplesData: SearchData) {

    fun printMenu() {
        helper.print()
        helper.print("=== Menu ===")
        helper.print("1. Find a person")
        helper.print("2. Print all people")
        helper.print("0. Exit")
    }

    fun doCmd(cmd: String) {
        helper.print()
        when (cmd) {
            "0" -> exit()
            "1" -> findPerson()
            "2" -> printAllPeople()
            else -> incorrectOption()
        }
    }

    fun exit() = helper.print("Bye!")

    fun incorrectOption() = helper.print("Incorrect option! Try again.")

    fun findPerson() {
        val searchData = helper.askInput("Enter a name or email to search all suitable people.")
        val result = peoplesData.find(searchData)
        val peopleCnt = result.size
        when (peopleCnt) {
            0 -> helper.print("No matching people found.")
            else -> helper.print("$peopleCnt persons found:")
        }
        result.forEach(helper::print)
    }

    fun printAllPeople() {
        helper.print("=== List of people ===")
        peoplesData.getAll().forEach(helper::print)
    }
}
