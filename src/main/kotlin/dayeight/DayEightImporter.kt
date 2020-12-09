package dayeight

import base.BaseImporter
import data.DataStrStr

class DayEightImporter(
    filePath: String
) : BaseImporter<DataStrStr>(filePath) {
    override fun parseLine(line: String): DataStrStr {
        val splitLine = line.split(" ")
        return DataStrStr(splitLine[0], splitLine[1])
    }

}