package _11Objects

import java.time.Year

fun main(args: Array<String>) {
    println(PrinterCopyrightNotice.getCopyRightNotice())
}

object PrinterCopyrightNotice {

    private val currentYear = Year.now().value

    private fun getTagLine() = "Best Printer Company"
    fun getCopyRightNotice() = "\u00A9 - ${getTagLine()}: $currentYear"
}