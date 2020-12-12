package daynine

import base.BaseEngine
import base.BaseImporter
import data.DataLong

class DayNineEngine(
    importer: BaseImporter<DataLong>
) : BaseEngine<DataLong>(importer) {
    private val input = importer.getLines().map { data -> data.num }

    override fun run() {
        partOne()
        partTwo()
    }

    private fun partOne() {
        for (i in 25 until input.size) {
            if (!findTwoNums(input.subList(i - 25, i).toMutableList(), input[i])) {
                println("The first number that the condition breaks on is ${input[i]}.")
                break
            }
        }
    }

    private fun partTwo() {
        val target: Long = 25918798
        val sumRange = findRange(target)
        println("The sublist that adds to $target is $sumRange. The smallest plus largest value is ${sumRange[0] + sumRange[sumRange.size - 1]}.")
    }

    private fun findTwoNums(numList: MutableList<Long>, target: Long): Boolean {
        numList.sort()
        for (begin in 0 until numList.size) {
            val beginNum = numList[begin]
            var end = begin + 1
            while (end < numList.size && beginNum + numList[end] <= target) {
                if (beginNum + numList[end] == target) {
                    return true
                } else {
                    end++
                }
            }
        }
        return false
    }

//    private fun findRange(target: Long): List<Long> {
//        var beginInd = 0
//        var endInd = 1
//        var runningTotal = input[beginInd] + input[endInd]
//        while(endInd < input.size && beginInd < endInd && runningTotal != target) {
//            if (runningTotal < target) {
//                endInd++
//                runningTotal += input[endInd]
//            } else if (runningTotal > target) {
//                beginInd++
//                runningTotal -= input[beginInd]
//            }
//        }
//        if (runningTotal == target) {
//            return input.subList(beginInd, endInd + 1)
//        } else {
//            throw NoSuchElementException()
//        }
//    }

    private fun findRange(target: Long): List<Long> {
        var beginInd = 0
        while (beginInd < input.size - 1) {
            var endInd = beginInd + 1
            var runningTotal = input[beginInd] + input[endInd]

            while (endInd < input.size && runningTotal < target) {
                endInd++
                runningTotal += input[endInd]
            }

            if (runningTotal == target) {
                val toReturn = input.subList(beginInd, endInd + 1).toMutableList()
                toReturn.sort()
                return toReturn
            }

            beginInd++
        }

        throw NoSuchElementException()
    }
}