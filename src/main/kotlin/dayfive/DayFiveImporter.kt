package dayfive

import base.BaseImporter
import data.DataStrStr

class DayFiveImporter(
    filePath: String
) : BaseImporter<DataStrStr>(filePath){
    override fun parseLine(line: String): DataStrStr {
        return DataStrStr(line.substring(0, 7), line.substring(7))
    }
}