package dayten

import base.BaseEngine
import base.BaseImporter
import data.DataInt

class DayTenEngine(
    importer: BaseImporter<DataInt>
) : BaseEngine<DataInt>(importer) {
    private val input: List<Int> = importer.getLines().map { data -> data.num }
    private val numSeq: HashMap<Int, Long> = HashMap()

    override fun run() {
        partOne()
        partTwo()
    }

    private fun findPartOneSeq(): List<Int> {
        val joltOrder: MutableList<Int> = mutableListOf(0)
        val joltsInBag = input.toMutableList()
        joltsInBag.sort()
        joltOrder.addAll(joltsInBag)
        joltOrder.add(joltsInBag.last() + 3)
        return joltOrder
    }

    private fun findJoltDiffDist(jolts: List<Int>): Map<Int, Int> {
        val joltDiffMap: HashMap<Int, Int> = HashMap<Int, Int>()
        for (i in 0 until jolts.size - 1) {
            val diff = jolts[i + 1] - jolts[i]
            joltDiffMap[diff] = joltDiffMap.getOrDefault(diff, 0) + 1
        }
        return joltDiffMap
    }

    private fun partOne() {
        val joltList = findPartOneSeq()
        val joltDiffDist = findJoltDiffDist(joltList)
        val numOneDiffs = joltDiffDist.getOrDefault(1, 1)
        val numThreeDiffs = joltDiffDist.getOrDefault(3, 1)
        println("The number of 1-diffs * 3-diffs is ${numOneDiffs * numThreeDiffs}.")
    }

    private fun findReachable(num: Int): List<Int> {
        val possibilities = mutableListOf<Int>()
        for (i in (num + 1) until (num + 4)) {
            if (input.contains(i)) possibilities.add(i)
        }
        return possibilities
    }

    private fun findNumSeq(elem: Int): Long {
        val reachable = findReachable(elem)
        if (reachable.isEmpty()) {
            return 1
        }
        return reachable.fold(0, { totalSeq, item ->
            if (!numSeq.containsKey(item)) {
                numSeq[item] = findNumSeq(item)
            }
            totalSeq + numSeq.getOrDefault(item, 0)
        })
    }

    private fun partTwo() {
        println("There are ${findNumSeq(findPartOneSeq()[0])} different legal sequences.")
    }
}