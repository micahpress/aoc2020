package daythree

import base.BaseEngine
import base.BaseImporter
import data.DataStr

class DayThreeEngine(
    importer: BaseImporter<DataStr>
) : BaseEngine<DataStr>(importer) {
    private val treeGrid: MutableList<List<String>> = mutableListOf()

    init {
        constructGrid()
    }

    private var height: Int = Int.MAX_VALUE
    private var width: Int = Int.MAX_VALUE

    init {
        setDims()
    }

    override fun run() {
        val trees = countTrees(3)
        println("There are $trees in the path!")
        val treeCounts: MutableList<Int> = mutableListOf()
        treeCounts.addAll(listOf(countTrees(1), countTrees(3), countTrees(5), countTrees(7), countTrees(1, 2)))
        val totalTrees = treeCounts.fold(1, { acc, i -> acc * i })
        println("You would encounter $totalTrees trees.")
    }

    private fun constructGrid() {
        val lines = importer.getLines()
        lines.forEach { data: DataStr ->
            val line = data.str
            val splitLine = line.split("")
            val asList = splitLine.subList(1, splitLine.size - 1)
            treeGrid.add(asList)
        }
    }

    private fun setDims() {
        height = treeGrid.size
        width = treeGrid[0].size
    }

    private fun countTrees(across: Int, down: Int = 1): Int {
        var currX = 0
        var currY = 0
        var trees = 0
        while (currY < height) {
            if (treeGrid[currY][currX] == "#") {
                trees++
            }
            currX = (currX + across) % width
            currY = (currY + down)
        }
        return trees
    }
}
