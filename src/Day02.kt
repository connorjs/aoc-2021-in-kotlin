fun main() {

    /** Calculate the horizontal position and depth you would have after following the planned course. What do you get if you multiply your final horizontal position by your final depth? */
    fun part1(input: List<List<String>>): Int {
        var horizontal = 0
        var depth = 0
        for (line in input) {
            val num = line[1].toInt()
            when (line[0]) {
                "forward" -> horizontal += num
                "down" -> depth += num
                "up" -> depth -= num
            }
        }
        return horizontal * depth
    }

    /** Using this new interpretation of the commands, calculate the horizontal position and depth you would have after following the planned course. What do you get if you multiply your final horizontal position by your final depth? */
    fun part2(input: List<List<String>>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0
        for (line in input) {
            val num = line[1].toInt()
            when (line[0]) {
                "forward" -> {
                    horizontal += num
                    depth += (num * aim)
                }
                "down" -> aim += num
                "up" -> aim -= num
            }
        }
        return horizontal * depth
    }

    val testInput = readInput("Day02_test").map { it.split(" ") }
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02").map { it.split(" ") }
    check(part1(input) == 2102357)
    check(part2(input) == 2101031224)
}
