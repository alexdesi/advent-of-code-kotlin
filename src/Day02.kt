import java.io.File

fun main() {
    fun value(move: Char): Int {
        return when (move) {
            'A', 'X' -> 1
            'B', 'Y' -> 2
            'C', 'Z' -> 3
            else -> -1
        }
    }

    fun getScore(move1: Char, move2: Char): Int {
        return when ("$move1$move2") {
            "AA", "BB", "CC" -> 3   // draw
            "AB", "BC", "CA" -> 6   // win
            "AC", "BA", "CB" -> 0   // lose
            else -> -1
        } + value(move2)
    }

    fun choseMove(move: Char, action: Char): Char {
//    X means you need to lose,
//    Y means you need to end the round in a draw, and
//    Z means you need to win
        return when (action) {
            'X' -> when (move) { // Lose
                'A' -> 'C'
                'B' -> 'A'
                'C' -> 'B'
                else -> ' '
            }

            'Y' -> when (move) { // Draw
                'A', 'B', 'C' -> move
                else -> ' '
            }

            'Z' -> when (move) {
                'A' -> 'B'   // win
                'B' -> 'C'
                'C' -> 'A'
                else -> ' '
            }

            else -> ' '
        }
    }

    fun convertToMove(wantedResult: Char): Char {
        return when (wantedResult) {
            'X' -> 'A'
            'Y' -> 'B'
            'Z' -> 'C'
            else -> ' '
        }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            getScore(
                it.first(),
                convertToMove(it.last())
            )
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            getScore(
                it.first(),
                choseMove(it.first(), it.last())
            )
        }
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(testInput)
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
