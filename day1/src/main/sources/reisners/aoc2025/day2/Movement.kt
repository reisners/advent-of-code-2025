package reisners.aoc2025.day2

enum class Direction(val letter: Char, val sign: Int) {
    LEFT('L', -1),
    RIGHT('R', 1);

    companion object {
        fun ofLetter(ch: Char): Direction =
            when (ch) {
                'L' -> LEFT
                'R' -> RIGHT
                else -> throw IllegalArgumentException("Unknown direction letter: $ch")
            }
    }
}

data class Movement(val direction: Direction, val distance: Int) {
    companion object {
        fun parse(line: String) = Movement(Direction.ofLetter(line[0]), line.substring(1).toInt())
    }

    val delta: Int = direction.sign * distance
}

data class Transition(val position: Int, val zeroes: Int) {
    companion object {
        fun of(lastPosition: Int, delta: Int): Transition {
            val newPosition = ((lastPosition + delta) % 100 + 100) % 100
            val zeroes =
                if (delta > 0) {
                    (lastPosition + delta) / 100
                } else {
                    ((100 - lastPosition) % 100 - delta) / 100
                }
            return Transition(newPosition, zeroes)
        }
    }
}
