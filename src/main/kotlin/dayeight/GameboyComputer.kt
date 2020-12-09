package dayeight

import data.DataStrStr

class GameboyComputer {
    private var accumulator = 0
    private var instructions: List<DataStrStr> = mutableListOf()
    private var runInstructions: HashSet<Int> = HashSet()
    private var currInstructionIndex: Int = 0

    private infix fun acc(num: Int) {
        accumulator += num
        incInstructionIndex(1)
    }

    private infix fun nop(arg: Int) {
        incInstructionIndex(1)
    }

    private infix fun jmp(index: Int) {
        incInstructionIndex(index)
    }

    private fun incInstructionIndex(offset: Int) {
        currInstructionIndex += offset
    }

    fun processInstructions(toProcess: List<DataStrStr>): Pair<Boolean, Int> {
        instructions = toProcess

        while (currInstructionIndex !in runInstructions && currInstructionIndex < instructions.size) {
            runInstructions.add(currInstructionIndex)
            val instInfo = instructions[currInstructionIndex]
            val instCode = instInfo.str1
            val arg = instInfo.str2

            when (instCode) {
                "acc" -> this acc arg.toInt()
                "jmp" -> this jmp arg.toInt()
                "nop" -> this nop arg.toInt()
            }
        }

        return Pair(currInstructionIndex == instructions.size, accumulator)
    }

    fun reset() {
        accumulator = 0
        currInstructionIndex = 0
        instructions = mutableListOf()
        runInstructions = HashSet()
    }
}