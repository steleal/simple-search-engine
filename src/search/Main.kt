package search

import java.util.*

fun main() {
    val helper = ConsoleHelper(Scanner(System.`in`))
    val peoplesData = initPeoplesData(helper)

    val manager = SearchManager(helper, peoplesData)

    while (true) {
        manager.printMenu()
        val cmd = helper.askInput("")
        manager.doCmd(cmd)
        if (cmd == "0") break
    }
}

fun initPeoplesData(helper: ConsoleHelper): MutableList<String> {
    val peoplesData = mutableListOf<String>()
    val numberOfPeople = helper.askInput("Enter the number of people:").toInt()
    repeat(numberOfPeople) {
        val personData = helper.askInput()
        peoplesData.add(personData)
    }
    return peoplesData
}

