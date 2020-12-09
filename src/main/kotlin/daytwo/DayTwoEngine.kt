package daytwo

import base.BaseEngine
import base.BaseImporter
import data.DataIntIntChrStr

class DayTwoEngine(
    importer: BaseImporter<DataIntIntChrStr>
) : BaseEngine<DataIntIntChrStr>(importer) {
    var totalValidPasswords: Int = 0

    override fun run() {
        val lines = importer.getLines()
        lines.forEach { data: DataIntIntChrStr ->
            if (testValidity(
                    data.num1..data.num2,
                    data.chr,
                    data.str
                )
            ) this.totalValidPasswords++
        }
        println("There are $totalValidPasswords valid passwords.")

        totalValidPasswords = 0
        lines.forEach { data: DataIntIntChrStr ->
            if (testValidity(
                    data.num1,
                    data.num2,
                    data.chr,
                    data.str
                )
            ) this.totalValidPasswords++
        }
        println("There are $totalValidPasswords valid passwords.")
    }

    private fun testValidity(range: IntRange, keyChar: Char, password: String): Boolean {
        var occurrences = password.fold(0, { acc, c -> if (c == keyChar) acc + 1 else acc })
        return (occurrences in range)
    }

    private fun testValidity(firstPos: Int, secondPos: Int, keyChar: Char, password: String): Boolean {
        return ((password[firstPos - 1] == keyChar) xor (password[secondPos - 1] == keyChar))
    }
}