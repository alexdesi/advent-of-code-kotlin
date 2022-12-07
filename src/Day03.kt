import java.io.File

fun main() {
    fun priority(item: Char): Int {
        return if (item.isUpperCase())
            item.code - 38
        else
            item.code - 96
    }

    fun part1(input: List<String>): Int {
        val splittedLines = input.map {
            Pair(
                it.substring(0, it.length / 2).toList(),
                it.substring(it.length / 2, it.length).toList()
            )
        }

        val errors = splittedLines.map {
            it.first.intersect(it.second).first() // There is only one element (by design)
        }

        return errors.sumOf { priority(it) }
    }

    fun part2(input: List<String>): Int {
        val groups = input.map {
            it.toList()
        }.chunked(3)

        val badges = groups.map {
            it[0].intersect(it[1]).intersect(it[2]).first()
        }
        return badges.sumOf { priority(it) }
    }

// test if implementation meets criteria:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
