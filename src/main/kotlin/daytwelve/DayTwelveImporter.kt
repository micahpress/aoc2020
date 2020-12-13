package daytwelve

import base.BaseImporter
import data.DataChrInt

class DayTwelveImporter(
    filePath: String
) : BaseImporter<DataChrInt>(filePath) {
    override fun parseLine(line: String): DataChrInt {
        return DataChrInt(line[0], line.substring(1).toInt())
    }
}