package search

class SearchManager(val helper: ConsoleHelper,
                    val peoplesData: MutableList<String>) {

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
        helper.print(result)
    }

    fun printAllPeople() {
        helper.print("=== List of people ===")
        peoplesData.forEach(helper::print)
    }

    private fun List<String>.find(query: String): String {
        val builder = StringBuilder()
        for (line in this) {
            if (line.contains(query, true)) {
                if (!builder.isEmpty()) builder.appendln()
                builder.append(line)
            }
        }

        return if (builder.isEmpty()) "No matching people found." else builder.toString()
    }
}
