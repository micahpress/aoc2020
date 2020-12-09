package dayfour

import base.BaseEngine
import base.BaseImporter
import data.DataStr
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class DayFourEngine(
    importer: BaseImporter<DataStr>
) : BaseEngine<DataStr>(importer) {
    val reqFields: List<String> = mutableListOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    val optFields: List<String> = mutableListOf("cid")
    val passports: MutableList<String> = mutableListOf()

    override fun run() {
        buildPassports()
        val total = countValidPassports()
        println("There are $total valid passports.")
        println("There are ${countStrictlyValidPassports()} strictly valid passports.")
    }

    private fun buildPassports() {
        val input = importer.getLines()
        var i = 0
        var currPassport = ""
        while (i < input.size) {
            val line = input[i].str
            if (line != "") {
                currPassport += " $line"
            } else {
                passports.add(currPassport)
                currPassport = ""
            }

            i++
        }
    }

    private fun isValid(passport: String): Boolean {
        return reqFields.fold(true, { acc: Boolean, field: String -> acc && passport.contains(field) })
    }

    private fun countValidPassports(): Int {
        var validPassports = 0
        passports.forEach { passport: String -> if (isValid(passport)) validPassports++ }
        return validPassports
    }

    private fun isStrictlyValid(passport: String): Boolean {
        if (isValid(passport)) {
            val tokenizer = StringTokenizer(passport, " ")
            while (tokenizer.hasMoreTokens()) {
                val field = tokenizer.nextToken()
                val prefix = field.substring(0, 3)
                val value = field.substring(4)
                when (prefix) {
                    "byr" -> {
                        val byrRegex = """[0-9]{4}""".toRegex()
                        if (byrRegex matches value) {
                            val year = value.toInt()
                            if (year !in 1920..2003) {
                                return false
                            }
                        } else {
                            return false
                        }
                    }
                    "iyr" -> {
                        val iyrRegex = """[0-9]{4}""".toRegex()
                        if (iyrRegex matches value) {
                            val year = value.toInt()
                            if (year !in 2010..2021) {
                                return false
                            }
                        } else {
                            return false
                        }
                    }
                    "eyr" -> {
                        val eyrRegex = """[0-9]{4}""".toRegex()
                        if (eyrRegex matches value) {
                            val year = value.toInt()
                            if (year !in 2020..2031) {
                                return false
                            }
                        } else {
                            return false
                        }
                    }
                    "hgt" -> {
                        val hgtCmRegex = """([0-9]{3})cm""".toRegex()
                        val hgtInRegex = """([0-9]{2})in""".toRegex()

                        if (hgtCmRegex matches value) {
                            val height = value.substring(0, 3).toInt()
                            if (height !in 150..194) {
                                return false
                            }
                        } else if (hgtInRegex matches value) {
                            val height = value.substring(0, 2).toInt()
                            if (height !in 59..77) {
                                return false
                            }
                        } else {
                            return false
                        }
                    }
                    "hcl" -> {
                        val hclRegex = """#[0-9a-f]{6}""".toRegex()
                        if (!(hclRegex matches value)) {
                            return false
                        }
                    }
                    "ecl" -> {
                        val eclRegex = """amb|blu|brn|gry|grn|hzl|oth""".toRegex()
                        if (!(eclRegex matches value)) {
                            return false
                        }
                    }
                    "pid" -> {
                        val pidRegex = """^[0-9]{9}$""".toRegex()
                        if (!(pidRegex matches value)) {
                            return false
                        }
                    }
                    "cid" -> {

                    }
                    else -> {
                        return false
                    }
                }
            }
            return true
        } else {
            return false
        }
    }

    private fun countStrictlyValidPassports(): Int {
        var validPassports = 0
        passports.forEach { passport: String -> if (isStrictlyValid(passport)) validPassports++ }
        return validPassports
    }
}