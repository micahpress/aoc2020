package daynine

import base.BaseImporter
import data.DataLong

class DayNineImporter(
    filePath: String
) : BaseImporter<DataLong>(filePath) {
    override fun parseLine(line: String): DataLong {
        return DataLong(line.toLong())
    }
}