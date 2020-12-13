package daytwelve

import base.BaseEngine
import base.BaseImporter
import data.DataChrInt
import kotlin.math.abs

class DayTwelveEngine(
    importer: BaseImporter<DataChrInt>
) : BaseEngine<DataChrInt>(importer) {
    private val input = importer.getLines().map { data -> Pair<Char, Int>(data.chr, data.num) }
    private var currX = 0
    private var currY = 0
    private var currHeading = 'E'

    private val dirOrder = listOf('N', 'E', 'S', 'W')
    private val dirSet: Set<Char> = HashSet(listOf('N', 'E', 'S', 'W'))
    private val turnSet: Set<Char> = HashSet(listOf('R', 'L'))

    private var wayPointX = 10
    private var wayPointY = 1

    private fun reset() {
        currX = 0
        currY = 0
        currHeading = 'E'
    }

    override fun run() {
//        partOne()
        partTwo()
    }

    private fun partOne() {
        for (instPair in input) {
            parseInstruction(instPair)
        }
        println(
            "The ship's heading is $currHeading, current X position is $currX, and current Y position is $currY. " +
                    "The Manhattan distance the ship has traveled is ${abs(currX) + abs(currY)}."
        )
    }

    private fun parseInstruction(inst: Pair<Char, Int>) {
        val code = inst.first
        val amount = inst.second

        if (dirSet.contains(code)) {
            execDirInst(code, amount)
        } else if (turnSet.contains(code)) {
            execTurnInst(code, amount)
        } else if (code == 'F') {
            execContInst(amount)
        }
    }

//    private fun execDirInst(dir: Char, dist: Int) {
//        if (dir == 'E') {
//            currX += dist
//        } else if (dir == 'W') {
//            currX -= dist
//        } else if (dir == 'N') {
//            currY += dist
//        } else if (dir == 'S') {
//            currY -= dist
//        }
//    }
//
//    private fun execTurnInst(dir: Char, amount: Int) {
//        val multiplicator = if (dir == 'R') 1 else -1
//        val degreesInUnits = amount / 90
//        currHeading = dirOrder[(dirOrder.indexOf(currHeading) + (multiplicator * degreesInUnits) + 4) % 4]
//    }
//
//    private fun execContInst(dist: Int) {
//        execDirInst(currHeading, dist)
//    }

    private fun partTwo() {
        for (instPair in input) {
            parseInstruction(instPair)
        }
        println(
            "The ship's heading is $currHeading, current X position is $currX, and current Y position is $currY. " +
                    "The Manhattan distance the ship has traveled is ${abs(currX) + abs(currY)}."
        )
    }

    private fun execDirInst(dir: Char, dist: Int) {
        if (dir == 'E') {
            wayPointX += dist
        } else if (dir == 'W') {
            wayPointX -= dist
        } else if (dir == 'N') {
            wayPointY += dist
        } else if (dir == 'S') {
            wayPointY -= dist
        }
    }

    private fun execMoveInst() {
        currX += wayPointX
        currY += wayPointY
    }

    private fun execTurnInst(dir: Char, amount: Int) {
        var units = amount / 90
        if (units % 2 == 0) {
            if (units % 4 != 0) {
                wayPointX *= -1
                wayPointY *= -1
            }
        } else {
            units -= 1
            val temp = wayPointX
            wayPointX = wayPointY
            wayPointY = temp
            if (units % 4 == 0) {
                if (dir == 'R') wayPointY *= -1
                if (dir == 'L') wayPointX *= -1
            } else {
                if (dir == 'R') wayPointX *= -1
                if (dir == 'L') wayPointY *= -1
            }
        }
    }

    private fun execContInst(amount: Int) {
        for (i in 0 until amount) {
            execMoveInst()
        }
    }
}