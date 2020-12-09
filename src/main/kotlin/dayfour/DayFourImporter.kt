package dayfour

import base.BaseImporter
import data.DataStr

class DayFourImporter(
    filePath: String
) : BaseImporter<DataStr>(filePath) {
    override fun parseLine(line: String): DataStr {
        return DataStr(line)
    }
}