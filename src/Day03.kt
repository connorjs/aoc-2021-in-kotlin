/** https://adventofcode.com/2021/day/3 */
fun main() {

    /** Get values for the current bit position (column). */
    fun currentBitPositionValues(input: List<List<Int>>, index: Int) = input.map { it[index] }

    fun part1(input: List<List<Int>>): Int {
        // Note: eps is gamma XOR-ed with 1s, so "have fun" and use that (less space)
        val numValues = input.size;
        val gammaBinary = CharArray(input[0].size)
        for (bitPosition in 0 until input[0].size) {
            val sum = currentBitPositionValues(input, bitPosition).sum()
            gammaBinary[bitPosition] = if (sum > numValues / 2) '1' else '0'
            // > or >= here (unspecified for part 1)
        }
        val gamma = String(gammaBinary).toInt(2)
        val eps = gamma xor ((1 shl input[0].size) - 1)
        return gamma * eps
    }

    fun part2Helper(input: List<List<Int>>, chooseMostCommon: Boolean): Int {
        val values = input.toMutableList()
        for (bitPosition in 0 until input[0].size) {
            if (values.size == 1) {
                break
            }
            val sum = currentBitPositionValues(values, bitPosition).sum()
            // Handle oxygen vs. CO2 with `chooseMostCommon` variable via `xor` (ugly boolean but oh well)
            val bitToKeep = if (chooseMostCommon xor (sum >= values.size / 2.0)) 0 else 1
            for (lineIndex in values.size - 1 downTo 0) {
                if (values[lineIndex][bitPosition] != bitToKeep) {
                    values.removeAt(lineIndex)
                }
            }
        }
        return values[0].joinToString("").toInt(2)
    }

    fun part2(input: List<List<Int>>): Int {
        val oxygenGeneratorRating = part2Helper(input, true)
        val co2ScrubberRating = part2Helper(input, false)
        return oxygenGeneratorRating * co2ScrubberRating;
    }

    fun convertInput(input: List<String>) = input.map { it.toCharArray() }.map { it.map { c -> c.digitToInt() } }

    val testInput = convertInput(readInput("Day03_test"))
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = convertInput(readInput("Day03"))
    check(part1(input) == 852500)
    check(part2(input) == 1007985)
}
