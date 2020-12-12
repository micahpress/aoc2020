package dayeleven

import base.BaseEngine
import base.BaseImporter
import data.DataStr

class DayElevenEngine(
    importer: BaseImporter<DataStr>
) : BaseEngine<DataStr>(importer) {
    private val startState: List<List<String>> = importer.getLines().map { data ->
        val innerList = data.str.split("")
        innerList.subList(1, innerList.size - 1)
    }
    private var currState: List<List<String>> = cloneList(startState)
    private var nextState: MutableList<MutableList<String>> = cloneList(startState)
    private val height = startState.size
    private val width = startState[0].size
    private val nextCellFuncs: List<(Int, Int) -> Pair<Int, Int>?> = listOf(
        this::nextCellAbove,
        this::nextCellBelow,
        this::nextCellRight,
        this::nextCellLeft,
        this::nextCellRightAbove,
        this::nextCellRightBelow,
        this::nextCellLeftAbove,
        this::nextCellLeftBelow
    )

    override fun run() {
        partTwo()
    }

    private fun partOne() {
        updateNextState()
        while (statesAreDifferent()) {
            currState = nextState
            updateNextState()
        }
        println("There are ${countOccupiedSeats()} occupied seats when people stop moving.")
    }

    private fun partTwo() {
        updateNextStateTwo()
        while (statesAreDifferent()) {
            currState = nextState
            updateNextStateTwo()
        }
        println("There are ${countOccupiedSeats()} occupied seats when people stop moving.")
    }

    private fun printState() {
        for (i in currState.indices) {
            for (j in currState[i].indices) {
                print(currState[i][j])
            }
            println()
        }
        println()
    }

    private fun cloneList(lst: List<List<String>>): MutableList<MutableList<String>> {
        val newList = mutableListOf<MutableList<String>>()
        lst.forEach { orig ->
            val toAdd = mutableListOf<String>()
            toAdd.addAll(orig)
            newList.add(toAdd)
        }
        return newList
    }

    private fun updateNextState() {
        val rowList = mutableListOf<MutableList<String>>()
        for (row in currState.indices) {
            val colList = mutableListOf<String>()
            for (col in currState[row].indices) {
                when (currState[row][col]) {
                    "#" -> if (countAdjacentOccupiedSeats(row, col) >= 4) colList.add("L") else colList.add("#")
                    "L" -> if (countAdjacentOccupiedSeats(row, col) == 0) colList.add("#") else colList.add("L")
                    "." -> colList.add(".")
                }
            }
            rowList.add(colList)
        }
        nextState = rowList
    }

    private fun statesAreDifferent(): Boolean {
        for (i in currState.indices) {
            for (j in currState[i].indices) {
                if (currState[i][j] != nextState[i][j]) {
                    return true
                }
            }
        }
        return false
    }

    private fun countAdjacentOccupiedSeats(row: Int, col: Int): Int {
        var total = 0
        for (i in (row - 1)..(row + 1)) {
            for (j in (col - 1)..(col + 1)) {
                if ((i == row && j == col) || i !in 0 until height || j !in 0 until width) {
                    continue
                } else {
                    if (currState[i][j] == "#") total++
                }
            }
        }
        return total
    }

    private fun countOccupiedSeats(): Int {
        var total = 0
        for (row in currState.indices) {
            for (col in currState[row].indices) {
                if (currState[row][col] == "#") total++
            }
        }
        return total
    }

    private fun updateNextStateTwo() {
        val rowList = mutableListOf<MutableList<String>>()
        for (row in currState.indices) {
            val colList = mutableListOf<String>()
            for (col in currState[row].indices) {
                when (currState[row][col]) {
                    "#" -> if (countVisibleOccupiedSeats(row, col) >= 5) colList.add("L") else colList.add("#")
                    "L" -> if (countVisibleOccupiedSeats(row, col) == 0) colList.add("#") else colList.add("L")
                    "." -> colList.add(".")
                }
            }
            rowList.add(colList)
        }
        nextState = rowList
    }

    private fun countVisibleOccupiedSeats(row: Int, col: Int): Int {
        var total = 0
        for (func in nextCellFuncs) {
            var nextCoords = func(row, col)
            while (nextCoords != null) {
                when (currState[nextCoords.first][nextCoords.second]) {
                    "#" -> {
                        total++
                        break
                    }
                    "L" -> break
                }
                nextCoords = func(nextCoords.first, nextCoords.second)
            }
        }
        return total
    }

    private fun nextCellAbove(i: Int, j: Int): Pair<Int, Int>? {
        return if ((i - 1) >= 0) Pair(i - 1, j) else null
    }

    private fun nextCellRightAbove(i: Int, j: Int): Pair<Int, Int>? {
        return if ((i - 1) >= 0 && (j + 1) < width) Pair(i - 1, j + 1) else null
    }

    private fun nextCellRight(i: Int, j: Int): Pair<Int, Int>? {
        return if ((j + 1) < width) Pair(i, j + 1) else null
    }

    private fun nextCellRightBelow(i: Int, j: Int): Pair<Int, Int>? {
        return if ((i + 1) < height && (j + 1) < width) Pair(i + 1, j + 1) else null
    }

    private fun nextCellBelow(i: Int, j: Int): Pair<Int, Int>? {
        return if ((i + 1) < height) Pair(i + 1, j) else null
    }

    private fun nextCellLeftBelow(i: Int, j: Int): Pair<Int, Int>? {
        return if ((i + 1) < height && (j - 1) >= 0) Pair(i + 1, j - 1) else null
    }

    private fun nextCellLeft(i: Int, j: Int): Pair<Int, Int>? {
        return if ((j - 1) >= 0) Pair(i, j - 1) else null
    }

    private fun nextCellLeftAbove(i: Int, j: Int): Pair<Int, Int>? {
        return if ((i - 1) >= 0 && (j - 1) >= 0) Pair(i - 1, j - 1) else null
    }
}