package search

import java.util.*

class ConsoleHelper (val scanner: Scanner) {
    //val helperLog = mutableListOf<String>()


    fun print(msg: String = "") {
        //helperLog.add(msg)
        println(msg)
    }

    fun askInput(msg: String = ""): String {
        if (msg !="") this.print(msg)
        val answer = scanner.nextLine()
        //helperLog.add(answer)
        return answer
    }

    /*
    fun saveLog() {
        val name = askInput("File name:")
        val file = File(name)
        file.writeText(helperLog.joinToString("\n"))
        print("The log has been saved.")
    }
    */

}