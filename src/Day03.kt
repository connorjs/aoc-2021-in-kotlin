fun main() {

    fun x(input: List<CharArray>, index: Int): List<Int> {
        return input.map { it[index].digitToInt() }
    }

    fun y(input: MutableList<List<Int>>, index: Int): List<Int> {
        return input.map { it[index] }
    }

    fun part1(input: List<CharArray>): Int {
        val num = input.size;
        val gammaBin = CharArray(input[0].size)
        val gammaEps = CharArray(input[0].size)
        for (i in 0 until input[0].size) {
            val sum = x(input, i).sum()
            gammaBin[i] = if (sum > num / 2) '1' else '0'
            gammaEps[i] = if (sum > num / 2) '0' else '1'
        }
        val gamma = String(gammaBin).toInt(2)
        val eps = String(gammaEps).toInt(2)
        return gamma * eps
    }

    fun part2(input: List<List<Int>>): Int {
        val oxygen = input.toMutableList()
        var oxygenBin: List<Int>? = null
        for (i in 0 until input[0].size) {
            if (oxygen.size == 1) {
                oxygenBin = oxygen[0]
            }
            val sum = y(oxygen, i).sum()
            val oxygenToKeep = if (sum >= oxygen.size / 2.0) 1 else 0
            for (j in oxygen.size - 1 downTo 0) {
                if (oxygen[j][i] != oxygenToKeep) {
                    oxygen.removeAt(j)
                }
            }
        }
        if (oxygenBin == null) {
            oxygenBin = oxygen[0];
        }
        val ox = oxygenBin.joinToString("").toInt(2)

        val co2 = input.toMutableList()
        var co2Bin: List<Int>? = null
        for (i in 0 until input[0].size) {
            if (co2.size == 1) {
                co2Bin = co2[0]
            }
            val sum = y(co2, i).sum()
            val co2ToKeep = if (sum >= co2.size / 2.0) 0 else 1
            for (j in co2.size - 1 downTo 0) {
                if (co2[j][i] != co2ToKeep) {
                    co2.removeAt(j)
                }
            }
        }
        if (co2Bin == null) {
            co2Bin = co2[0];
        }
        val co = co2Bin.joinToString("").toInt(2)

        return ox * co;
    }

    val testInput = readInput("Day03_test").map { it.toCharArray() }
    val testInput2 = readInput("Day03_test").map { it.toCharArray() }.map { it.map { c -> c.digitToInt() } }
    check(part1(testInput) == 198)
    check(part2(testInput2) == 230)

    val input = readInput("Day03").map { it.toCharArray() }
    val input2 = readInput("Day03").map { it.toCharArray() }.map { it.map { c -> c.digitToInt() } }
    check(part1(input) == 852500)
    check(part2(input2) == 1007985)
}
