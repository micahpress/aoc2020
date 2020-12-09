package base

import java.io.BufferedReader
import java.io.File

abstract class BaseImporter<T>(
    private val filePath: String
) {
    private val file: File = File(filePath)
    private val reader: BufferedReader = file.bufferedReader()

    private fun readLine(): String {
        return reader.readLine()
    }

    private fun readLines(): List<String> {
        return reader.readLines()
    }

    protected abstract fun parseLine(line: String): T

    private fun parseLines(lines: List<String>): List<T> {
        return lines.map { parseLine(it) }
    }

    fun getLine(): T {
        val line = readLine()
        return parseLine(line)
    }

    fun getLines(): List<T> {
        val lines = readLines()
        return parseLines(lines)
    }
}
