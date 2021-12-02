fun main() {

    fun count(input: List<Int>, window: Int = 1): Int {
        assert(input.isNotEmpty())
        var count = 0
        var prev = input[0]
        for (index in window until input.size) {
            val curr = input[index]
            if (curr > prev) {
                ++count
            }
            prev = input[index - window + 1]
        }
        return count
    }

    /** Count the number of times a depth measurement increases from the previous measurement. */
    fun part1(input: List<Int>): Int {
        return count(input)
    }

    /** Count the number of times the sum of measurements in this sliding window (length 3) increases from the previous sum. */
    fun part2(input: List<Int>): Int {
        return count(input, 3)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map { it.toInt() }
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01").map { it.toInt() }
    check(part1(input) == 1316)
    check(part2(input) == 1344)
}
