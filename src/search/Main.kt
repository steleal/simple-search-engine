package search

import java.io.File
import java.util.Scanner


fun main(args: Array<String>) {
    val helper = ConsoleHelper(Scanner(System.`in`))
    val peoplesData = SearchData()

    initPeoplesData(args, peoplesData)

    val manager = SearchManager(helper, peoplesData)

    while (true) {
        manager.printMenu()
        val cmd = helper.askInput("")
        manager.doCmd(cmd)
        if (cmd == "0") break
    }
}


private fun initPeoplesData(args: Array<String>, peoplesData: SearchData) {

    if (args.size < 2) return
    if (args[0] != "--data") return
    val peoplesDataFile = File(args[1])
    if (!peoplesDataFile.exists()) return

    peoplesDataFile.forEachLine {
        peoplesData.add(it)
    }
}
