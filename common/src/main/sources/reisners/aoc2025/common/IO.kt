package reisners.aoc2025.common

fun readInputFile(inputFileName: String): List<String> {
    return Thread.currentThread()
        .contextClassLoader
        .getResourceAsStream(inputFileName)
        ?.bufferedReader()
        ?.use { it.readLines() }
        ?: throw IllegalStateException("Could not read input.txt from classpath")
}
