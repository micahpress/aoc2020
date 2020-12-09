package dayseven

import base.BaseImporter
import data.DataStrStr

class DaySevenImporter(
    filePath: String
) : BaseImporter<DataStrStr>(filePath) {
    override fun parseLine(line: String): DataStrStr {
        val splitLine = line.split(" bags contain ")
        val container = splitLine[0]
        val contained = splitLine[1].substring(0, splitLine[1].length - 1)

        return DataStrStr(container, contained)
    }
}