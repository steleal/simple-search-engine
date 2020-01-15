package search

class SearchData() {
    val lines = mutableListOf<String>()
    val invertedIndex = mutableMapOf<String, MutableSet<Int>>()

    fun add(line: String) {
        if (line.isBlank()) return
        val lineNumber = addInLines(line)
        val words = line.toLowerCase().split(" ")
        words.forEach {
            addInvertedIndex(it, lineNumber)
        }
    }

    fun getAll(): List<String> = lines

    fun find(query: String, strategy: String): List<String> {
        if (query.isBlank()) return emptyList()

        val words = query.toLowerCase().split(" ")
        return when (strategy.toLowerCase()) {
            "all" -> findLinesWithAll(words)
            "any" -> findLinesWithAny(words)
            "none" -> findLinesNotContain(words)
            else -> mutableListOf()
        }
    }

    private fun findLinesWithAll(words: List<String>): List<String> {
        val lineNumbers = invertedIndex.get(words[0]) ?: return emptyList()
        words.forEach { word ->
            val wordLineNumbers = invertedIndex.get(word) ?: return emptyList()
            lineNumbers.forEach {
                if (!wordLineNumbers.contains(it)) lineNumbers.remove(it)
            }
        }
        return lineNumbers.toList().toLines()
    }

    private fun findLinesWithAny(words: List<String>): List<String> {
        val lineNumbers = mutableSetOf<Int>()
        words.forEach { word ->
            val wordLineNumbers = invertedIndex.get(word)
            wordLineNumbers?.forEach { lineNumbers.add(it) }
        }
        return lineNumbers.toList().toLines()
    }

    private fun findLinesNotContain(words: List<String>): List<String> {
        val lineNumbers = MutableList(lines.size) { it }
        words.forEach { word ->
            val wordLineNumbers = invertedIndex.get(word)
            wordLineNumbers?.forEach { if (lineNumbers.contains(it)) lineNumbers.remove(it) }
        }
        return lineNumbers.toLines()
    }

    private fun List<Int>.toLines(): List<String> = this.map { n -> lines[n] }

    private fun addInLines(line: String): Int {
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
