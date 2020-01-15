package search

import java.util.*

class ConsoleHelper(val scanner: Scanner) {

    fun print(msg: String = "") = println(msg)

    fun askInput(msg: String = ""): String {
        if (msg != "") this.print(msg)
        val answer = scanner.nextLine()
        return answer
    }
}
