package dayten

import base.BaseImporter
import data.DataInt

class DayTenImporter(
    filePath: String
) : BaseImporter<DataInt>(filePath) {
    override fun parseLine(line: String): DataInt {
        return DataInt(line.toInt())
    }
}