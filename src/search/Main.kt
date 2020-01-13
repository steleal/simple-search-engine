package search

import java.lang.StringBuilder
import java.util.*

fun main() {
    val helper = ConsoleHelper(Scanner(System.`in`))

    val numberOfPeople = helper.askInput("Enter the number of people:").toInt()
    val peoplesData = mutableListOf<String>()

    helper.print("Enter all people:")
    repeat(numberOfPeople) {
        peoplesData.add(helper.askInput())
    }

    val numberOfQueries = helper.askInput("Enter the number of search queries:").toInt()
    repeat(numberOfQueries) {
        val searchData = helper.askInput("Enter data to search people:")
        val result = peoplesData.find(searchData)
        helper.print(result)
    }
}

fun List<String>.find(query: String): String {
    val builder = StringBuilder()

    for (i in this.indices) {
        val line = this[i]
        val words = line.split(" ")
        for (word in words) {
            if (word.contains(query,true)){
                builder.append(line)
                builder.appendln()
                break
            }
        }
    }

    return if (builder.isEmpty()) {
        "No matching people found.\n"
    } else {
        builder.insert(0, "Found people:\n")
        builder.toString()
    }
}
