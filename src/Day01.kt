import java.io.File

fun main() {
    fun sortedCalories(input: List<String>): List<Int> {
        val elvesCalories = mutableListOf(
            mutableListOf<Int>()
        )

        input.forEach {
            if (it == "")
                elvesCalories.add(mutableListOf())
            else
                elvesCalories.last().add(it.toInt())
        }

        return elvesCalories
            .map { it.sum() }
            .sortedDescending()
    }

    fun part1(input: List<String>): Int {
        return sortedCalories(input).first()
    }

    fun part2(input: List<String>): Int {
        return sortedCalories(input)
            .subList(0, 3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}


