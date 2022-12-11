import java.io.File

fun String.occurrences(c: Char) = this.count { it == c }

fun main() {
    fun duplicatesPresent(string4: String): Boolean {
        string4.forEach {
            println(string4.occurrences(it))
            if (string4.occurrences(it) > 1)
                return true
        }
        return false
    }

    fun part1(code: String): Int {
        (0..code.length - 4).forEach { i ->
            if (!duplicatesPresent(code.substring(i, i + 4))) {
                return i + 4
            }
        }

        return -1
    }

    // Part 2 is identical to part 1, it just use 14 instead of 4 for the packet size
    // This would need refactor.
    fun part2(code: String): Int {
        (0..code.length - 4).forEach { i ->
            if (!duplicatesPresent(code.substring(i, i + 14))) {
                return i + 14
            }
        }

        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    println(part1(testInput[0]))
    check(part1(testInput[0]) == 5)

    val input = readInput("Day06")
    println(part1(input[0]))
    println(part2(input[0]))
}
