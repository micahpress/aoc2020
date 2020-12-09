package daysix

import base.BaseEngine
import base.BaseImporter
import data.DataStr

class DaySixEngine(
    importer: BaseImporter<DataStr>
) : BaseEngine<DataStr>(importer) {
    val groupAnswersList = mutableListOf<String>()

    init {
        val lines = importer.getLines()
        var i = 0
        var currGroupAnswers = ""

        while (i < lines.size) {
            if (lines[i].str == "") {
                groupAnswersList += currGroupAnswers
                currGroupAnswers = ""
            } else {
                currGroupAnswers += " " + lines[i].str
            }
            i++
        }
    }

    override fun run() {
        println("There were a cumulative ${countYesQuestions()} affirmative answers.")
        println("There were ${countAllYesQuestions()} questions that everyone in a group answered Yes to.")
    }

    private fun countYesQuestions(): Int {
        var total = 0
        for (groupAnswers in groupAnswersList) {
            val yesSet = HashSet<Char>()
            groupAnswers.map {c: Char ->
                if (c != ' ') yesSet.add(c)
            }
            total += yesSet.size
        }
        return total
    }

    private fun countAllYesQuestions(): Int {

        var total = 0
        for (groupAnswers in groupAnswersList) {
            val indivAnswers = groupAnswers.split(" ")
            var yesSet: HashSet<Char> = HashSet<Char>()

            indivAnswers[1].forEach { c -> yesSet.add(c) }

            for (answer in indivAnswers.subList(2, indivAnswers.size)) {
                val remaining = HashSet<Char>()
                yesSet.forEach { c -> if (answer.contains(c)) remaining.add(c) }
                yesSet = remaining
            }

            total += yesSet.size
        }
        return total
    }
}