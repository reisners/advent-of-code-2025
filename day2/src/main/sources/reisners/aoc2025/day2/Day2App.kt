package reisners.aoc2025.day2

data class IdRange(val min: Long, val max: Long) {

    companion object {
        fun of(str: String): IdRange =
            IdRange(str.substringBefore("-").toLong(), str.substringAfter("-").toLong())
    }

    val invalidIdsContained: List<Long>
        get() {
            return (min..max).filter { it.toString().matches(Regex("^(\\d+)\\1+$")) }
        }
}

fun main() {
    val inputFileName = "input.txt"
    val input = readInputFile(inputFileName)[0]
    val parsed = input.split(",").map { IdRange.of(it) }

    parsed.forEach { println("$it: ${it.invalidIdsContained}") }

    val sum = parsed.sumOf { it.invalidIdsContained.sum() }

    println(sum)
}

fun readInputFile(inputFileName: String): List<String> {
    return Thread.currentThread()
        .contextClassLoader
        .getResourceAsStream(inputFileName)
        ?.bufferedReader()
        ?.use { it.readLines() }
        ?: throw IllegalStateException("Could not read input.txt from classpath")
}
