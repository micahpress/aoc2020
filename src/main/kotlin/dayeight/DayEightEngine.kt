package dayeight

import base.BaseEngine
import base.BaseImporter
import data.DataStrStr

class DayEightEngine(
    importer: BaseImporter<DataStrStr>
) : BaseEngine<DataStrStr>(importer) {
    override fun run() {
        val computer = GameboyComputer()
        val instructions: List<DataStrStr> = importer.getLines()
        val acc = computer.processInstructions(instructions)
        println("The value in the accumulator at the end of the loop was ${acc.second}.")

        var moddedInstructions = cloneList(instructions)
        computer.reset()

        for (i in instructions.indices) {
            when (instructions[i].str1) {
                "nop" -> moddedInstructions[i].str1 = "jmp"
                "jmp" -> moddedInstructions[i].str1 = "nop"
                else -> continue
            }

            computer.reset()
            val result = computer.processInstructions(moddedInstructions)
            if (result.first) {
                println("The value in the accumulator was ${result.second}")
                break
            }
            moddedInstructions = cloneList(instructions)
        }
    }

    private fun cloneList(lst: List<DataStrStr>): MutableList<DataStrStr> {
        val newList = mutableListOf<DataStrStr>()
        lst.forEach { orig -> newList.add(DataStrStr(orig.str1, orig.str2)) }
        return newList
    }
}