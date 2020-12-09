package daytwo

import base.BaseImporter
import data.DataIntIntChrStr
import java.util.*

class DayTwoImporter(
    filePath: String
) : BaseImporter<DataIntIntChrStr>(filePath) {
    override fun parseLine(line: String): DataIntIntChrStr {
        val tokenizer: StringTokenizer = StringTokenizer(line, "-: ")
        if (tokenizer.countTokens() != 4) {
            throw Exception("The input is malformed.")
        }
        return DataIntIntChrStr(
            tokenizer.nextToken().toInt(),
            tokenizer.nextToken().toInt(),
            tokenizer.nextToken().toCharArray()[0],
            tokenizer.nextToken()
        )
    }
}