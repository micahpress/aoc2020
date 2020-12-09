package daysix

import base.BaseImporter
import data.DataStr

class DaySixImporter(
    filePath: String
) : BaseImporter<DataStr>(filePath) {
    override fun parseLine(line: String): DataStr {
        return DataStr(line)
    }
}