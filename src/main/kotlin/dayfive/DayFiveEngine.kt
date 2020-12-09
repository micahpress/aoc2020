package dayfive

import base.BaseEngine
import base.BaseImporter
import data.DataStrStr

class DayFiveEngine(
    importer: BaseImporter<DataStrStr>
) : BaseEngine<DataStrStr>(importer) {
    override fun run() {
        println(partTwo())
    }

    private fun toDecimal(oneChar: Char, zeroChar: Char, input: String): Int {
        return input.fold(0, { acc: Int, c: Char ->
            val digit = if (c == oneChar) 1 else 0
            ((acc * 2) + digit)
        })
    }

    private fun calcSeatID(row: Int, col: Int): Int {
        return (row * 8 + col)
    }

    private fun partOne() {
        val lines = importer.getLines()
        val maxSeatID = lines.fold(0, { acc: Int, line: DataStrStr ->
            val row = toDecimal('B', 'F', line.str1)
            val col = toDecimal('R', 'L', line.str2)
            val id = calcSeatID(row, col)
            if (id > acc) id else acc
        })
        println(maxSeatID)
    }

    private fun partTwo(): Int {
        val seatIDList = mutableListOf<Int>()
        val lines = importer.getLines()
        val maxSeatID = lines.map { line ->
            val row = toDecimal('B', 'F', line.str1)
            val col = toDecimal('R', 'L', line.str2)
            val id = calcSeatID(row, col)
            seatIDList.add(id)
        }
        seatIDList.sort()
        var i = 0
        while (i < seatIDList.size - 1) {
            if (seatIDList[i] + 2 == seatIDList[i + 1]) {
                return (seatIDList[i] + 1)
            }
            i++
        }
        throw NoSuchElementException()
    }
}