package search

class SearchData() {
    val lines = mutableListOf<String>()
    val invertedIndex = mutableMapOf<String, MutableSet<Int>>()

    fun find(word: String): List<String> {
        val lineNumbers = invertedIndex.getOrDefault(word.toLowerCase(), mutableSetOf())
        val result = mutableListOf<String>()
        lineNumbers.forEach{result.add(lines[it])}
        return result
    }

    fun add(line: String) {
        if (line.isBlank()) return
        val lineNumber = addLine(line)
        val words = line.toLowerCase().split(" ")
        words.forEach {
            addInvertedIndex(it, lineNumber)
        }
    }

    fun getAll(): List<String> {
        return lines
    }

    private fun addLine(line: String): Int {
        lines.add(line)
        return lines.lastIndex
    }

    private fun addInvertedIndex(word: String, lineNumber: Int) {
        if (word.isEmpty()) return
        val lineNumbers = invertedIndex.getOrDefault(word, mutableSetOf())
        lineNumbers.add(lineNumber)
        invertedIndex.put(word, lineNumbers)
    }



}