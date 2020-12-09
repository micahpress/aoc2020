package dayone

import base.BaseEngine
import base.BaseImporter
import data.DataInt

class DayOneEngine(
    importer: BaseImporter<DataInt>
) : BaseEngine<DataInt>(importer) {
    private val numList: MutableList<Int> = mutableListOf()
    private var numOne: Int = Int.MAX_VALUE
    private var numTwo: Int = Int.MAX_VALUE

    override fun run() {
        buildList()
        sortList()
        var nums = findTwoNums()
        println(nums.first.toString() + " * " + nums.second.toString() + " = " + (nums.first * nums.second).toString())
        var numsTriple = findThreeNums()
        println(
            numsTriple.first.toString() + " * " + numsTriple.second.toString()
                    + " * " + numsTriple.third.toString() + " = "
                    + (numsTriple.first * numsTriple.second * numsTriple.third).toString()
        )
    }

    private fun buildList() {
        val data = importer.getLines()
        for (wrapper in data) {
            numList.add(wrapper.num)
        }
    }

    private fun sortList() {
        numList.sort()
        numList.reverse()
    }

    private fun findTwoNums(): Pair<Int, Int> {
        for (begin in 0 until numList.size) {
            val beginNum = numList[begin]
            var end = numList.size - 1
            while (beginNum + numList[end] <= 2020 && end > begin) {
                if (beginNum + numList[end] == 2020) {
                    break
                } else {
                    end--
                }
            }
            if (beginNum + numList[end] == 2020) {
                return Pair<Int, Int>(beginNum, numList[end])
            }
        }
        throw NoSuchElementException()
    }

    private fun findThreeNums(): Triple<Int, Int, Int> {
        for (first in 0 until numList.size) {
            var second = numList.size - 1
            while (numList[first] + numList[second] <= 2020 && second > first) {
                var third = second - 1
                while (numList[first] + numList[second] + numList[third] <= 2020 && third > first + 1) {
                    if (numList[first] + numList[second] + numList[third] == 2020) {
                        return Triple<Int, Int, Int>(numList[first], numList[second], numList[third])
                    } else {
                        third--
                    }
                }
                second--
            }
        }
        throw NoSuchElementException()
    }
}