package daythirteen

import base.BaseEngine
import base.BaseImporter
import data.DataStr

class DayThirteenEngine(
    importer: BaseImporter<DataStr>
) : BaseEngine<DataStr>(importer) {
    private val startLimit: Int
    private val idList: MutableList<Int> = mutableListOf<Int>()
    private val offsetMap = HashMap<Int, Int>()

    init {
        val input = importer.getLines()
        startLimit = input[0].str.toInt()
        val idListWithX = input[1].str.split(",").toMutableList()
        for (id in idListWithX) {
            if (id != "x") {
                idList.add(id.toInt())
            }
        }
        for (ind in 0 until idListWithX.size) {
            if (idListWithX[ind] != "x") {
                val id = idListWithX[ind].toInt()
                var offset = if (ind != 0) id - (ind % id) else ind
                offsetMap[id] = offset
            }
        }
    }

    override fun run() {
//        partOne()
        partTwo()
    }

    private fun partOne() {
        val remList = mutableListOf<Int>()
        for (id in idList) {
            remList.add(startLimit % id)
        }

        val diffList = mutableListOf<Int>()
        for (ind in 0 until remList.size) {
            diffList.add(idList[ind] - remList[ind])
        }

        var minDiff = Int.MAX_VALUE
        diffList.forEach { diff -> if (diff < minDiff) minDiff = diff }
        val earliestId = idList[diffList.indexOf(minDiff)]
        println("The earliest bus I can take after $startLimit is $earliestId.")
        val departTime = ((startLimit / earliestId) + 1) * earliestId
        println(
            "I would leave at $departTime, so I'd wait for ${departTime - startLimit}.\n" +
                    "Multiplying the ID by the time I'd wait gives ${earliestId * (departTime - startLimit)}"
        )
    }

    private fun partTwo() {
        val prodOfIds = idList.fold(1, { acc: Long, i: Int -> acc * i })
        val workList = mutableListOf<Long>()
        for (id in idList) {
            val bigX = (prodOfIds / id.toLong())
            val bigXInverse = calcModInverse(bigX, id)
            workList.add(bigX * bigXInverse * offsetMap.getOrDefault(id, 0))
        }
        val earliestDepartTime = workList.sum() % prodOfIds
        println("The earliest departure time that satisfies the equations is $earliestDepartTime.")
    }

    private fun calcModInverse(arg: Long, mod: Int): Int {
        var inverse = 1
        while (((arg * inverse) % mod) != 1.toLong()) {
            inverse++
        }
        return inverse
    }
}