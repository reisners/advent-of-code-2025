package reisners.aoc2025.day3

import reisners.aoc2025.common.readInputFile

data class Bank(val batteries: List<Int>) {
    companion object {
        fun of(line: String) = Bank(line.map { it.digitToInt() })
    }

    fun maxJoltage(n: Int): Long {
        val l = batteries.size
        require(n <= l) { "n must be <= l" }
        require(n > 0) { "n must be > 0" }

        val work = batteries.toMutableList()
        while (work.size > n) {
            if (!removeDigit(work)) break
        }
        if (work.size > n) {
            work.subList(n, work.size).clear()
        }
        return work.fold(0L) { acc, i -> acc * 10 + i }
    }

    fun removeDigit(work: MutableList<Int>): Boolean {
        (0..<work.size - 1).forEach {
            if (work[it] < work[it + 1]) {
                work.removeAt(it)
                return true
            }
        }
        return false
    }
}

fun main() {
    val inputFileName = "input.txt"
    val input = readInputFile(inputFileName)
    val parsed = input.map { Bank.of(it) }
    parsed.forEach { println("$it: ${it.maxJoltage(12)}") }
    val sum = parsed.sumOf { it.maxJoltage(12) }
    println(sum)
}
