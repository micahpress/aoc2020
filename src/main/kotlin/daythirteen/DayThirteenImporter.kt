package daythirteen

import base.BaseImporter
import data.DataStr

class DayThirteenImporter(
    filePath: String
) : BaseImporter<DataStr>(filePath) {
    override fun parseLine(line: String): DataStr {
        return DataStr(line)
    }
}