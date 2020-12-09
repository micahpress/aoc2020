package dayone

import base.BaseImporter
import data.DataInt

class DayOneImporter(
    filePath: String
) : BaseImporter<DataInt>(filePath) {
    override fun parseLine(line: String): DataInt {
        return DataInt(line.toInt())
    }
}