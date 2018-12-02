package util

import java.io.FileInputStream
import java.io.InputStreamReader

object InputUtils {
    fun getInputAsLineArray(day: String): List<String> {
        return getInputAsString(day).split("\n")
    }

    fun getInputAsString(day: String): String {
        val stream = FileInputStream("src/$day/input.txt").buffered()
        return InputStreamReader(stream, "UTF-8").readText()
    }
}