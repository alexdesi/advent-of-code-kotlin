import java.io.File

fun main() {
    val regex = """\s*\[(\w)\]\s*""".toRegex()

    fun getStacks(input: List<String>): MutableList<MutableList<Char>> {
        val stackSize = (input.first().length + 1) / 4
        val stacks: MutableList<MutableList<Char>> = mutableListOf()

        repeat(stackSize) {
            stacks.add(mutableListOf())
        }

        val stackLines = input
            .dropLastWhile { !it.startsWith(" 1") }
            .dropLast(1)

        val stackChars = stackLines.map {
            it.chunked(4).map { markInBrackets ->
                val mark = regex.find(markInBrackets)
                if (mark != null)
                    mark.groupValues[1].first()// .first is to convert string to char
                else
                    null
            }
        }.reversed()

        stackChars.forEach { marksLine ->
            marksLine.forEachIndexed { i, mark ->
                if (mark != null) stacks[i].add(mark)
            }
        }

        return stacks
    }

    fun getMoves(input: List<String>): List<List<Int>> {
        // count, source, destination
        val regex = """\D+(\d+)\D+(\d+)\D+(\d+)""".toRegex()

        val moves = input
            .dropWhile { !it.startsWith("move") }
            .map {
                regex.find(it)!!
                    .groupValues
                    .drop(1).map {
                        it.toInt()
                    }
            }
        return moves
    }

    fun part1(input: List<String>): String {
        val stacks = getStacks(input)
        val moves = getMoves(input)

        moves.forEach { (count, source, destination) ->
            println("count: $count, source:$source, destination:$destination")
            repeat(count) {
                println(it)
                val el = stacks[source - 1].removeLast()
                stacks[destination - 1].add(el)
            }
        }

        return stacks.map {
            it.last()
        }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val stacks = getStacks(input)
        val moves = getMoves(input)

        moves.forEach { (count, source, destination) ->
            val cratesToMove = stacks[source - 1].takeLast(count)
            cratesToMove.forEach { stacks[destination - 1].add(it) }
            repeat(count) { stacks[source - 1].removeLast() }
        }

        return stacks.map {
            it.last()
        }.joinToString("")
    }

    // Test if implementation meets criteria from the description:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
