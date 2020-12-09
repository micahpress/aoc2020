package daythree

import base.BaseImporter
import data.DataStr

class DayThreeImporter(
    filePath: String
) : BaseImporter<DataStr>(filePath) {
    override fun parseLine(line: String): DataStr {
        return DataStr(line)
    }
}