package dayeleven

import base.BaseImporter
import data.DataStr

class DayElevenImporter(
    filePath: String
) : BaseImporter<DataStr>(filePath) {
    override fun parseLine(line: String): DataStr {
        return DataStr(line)
    }
}