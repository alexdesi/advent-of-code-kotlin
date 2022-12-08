import java.io.File

fun main() {
    val regex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex() // to group use round parenthesis

    fun sections(input: List<String>): List<List<Int>> {
        return input.map { line ->
            val matchResult = regex.find(line)
            matchResult!!.groupValues.subList(1, 5).map { it.toInt() }
        }
    }

    fun part1(input: List<String>): Int {
        val fullyContainedSections = sections(input).filter { (a1, a2, b1, b2) ->
            ((a1 in b1..b2) && (a2 in b1..b2)) || ((b1 in a1..a2) && (b2 in a1..a2))
        }

        return fullyContainedSections.count()
    }

    fun part2(input: List<String>): Int {
        val containedSections = sections((input)).filter { (a1, a2, b1, b2) ->
            ((a2 in b1..b2) || (b2 in a1..a2))
        }

        return containedSections.count()
    }

    // test if implementation meets criteria from the description
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
