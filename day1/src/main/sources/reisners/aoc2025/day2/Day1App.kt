package reisners.aoc2025.day2

import reisners.aoc2025.common.readInputFile

fun main() {
    val inputFileName = "input.txt"
    val input = readInputFile(inputFileName)
    val parsed = input.map { Movement.parse(it) }
    println(parsed.size)
    val positions =
        parsed.fold(listOf(Transition(50, 0))) { head, movement ->
            val lastPosition = head.last().position
            val newTransition = Transition.of(lastPosition, movement.delta)
            head + listOf(newTransition)
        }

    val result = positions.sumOf { it.zeroes }

    println("Positions: $positions")
    println(result)
}
