package _12enums

import java.text.DecimalFormat

fun main() {

    println(Department.ACCOUNTING.getDepartmentInfo())
    println(Department.HR.fullName)
    println(Department.SALES.fullName)

    println(Status.nameLowercase(Status.WORK))
    println(Status.WORK.getStatusInfo())
    println(Status.values().size)
    println(Status.fromStatusNumber(1))
    println(if (Status.fromStatusNumber(1) is Status) "true" else " -- ")

    println(ByteUnit.formatBytes(1024L))
    println(ReportFileName.fromTypeNumber(1))
    println(ReportFileName.fromTypeNumber(1)?.value)
}

enum class Department(val fullName: String, val numEmployees: Int) {

    // one of the only places we need a semicolon is when adding a function to an enum class
    // after the last enum entry
    HR("Human Resources", 3),
    IT("Information Technology", 10),
    ACCOUNTING("Accounting", 4),
    SALES("Sales", 15);

    fun getDepartmentInfo() = "the $fullName department has $numEmployees employees"
}

enum class Status(val number: Int, val rusName: String) {
    OPEN(1, "Открыт"), WORK(2, "Работает"), CLOSE(3, "Закрыт");

    fun getStatusInfo() = "The $name has rus word = $rusName and number $number"

    companion object {
        fun nameLowercase(status: Status): String = status.name.lowercase()
        fun fromStatusNumber(_number: Int): Status? {
            require(_number >= 0) { "Invalid number size: $_number" } //java.lang.IllegalArgumentException
            for (el in values()) {
                if (el.number == _number) {
                    return el
                }
            }
            return null
        }
    }
}

internal enum class ByteUnit(private val size: Long) {
    B(1), KB(1024), MB(KB.size * KB.size), GB(MB.size * KB.size), TB(GB.size * KB.size);

    fun fromBytes(value: Long?): Long {
        return if (value == null) {
            0L
        } else value / size
    }

    fun toBytes(value: Long?): Long {
        return if (value == null) {
            0L
        } else size * value
    }

    companion object {
        fun formatBytes(value: Long): String? {
            require(value >= 0) { "Invalid value size: $value" } //java.lang.IllegalArgumentException
            if (value == 0L) { //если размер равен 0 B
                return "0"
            }
            val units = values()
            for (i in units.indices.reversed()) {
                val unit = units[i]
                if (value >= unit.size) {
                    val result = if (unit.size > 1) value.toDouble() / unit.size.toDouble() else value.toDouble()
                    return DecimalFormat("#,##0.##").format(result) + " " + unit.name
                }
            }
            return null
        }
    }
}

internal enum class ReportFileName(private val typeNumber: Int, val value: String) {
    //Отчеты
    INFORMATION_ON_STATE_OF_INFORMATION_EXCHANGE(
        1, "Сведения о состоянии информационного взаимодействия"
    ),
    INFORMATION_ABOUT_USERS_WHO_HAVE_ACCESS(
        2, "Сведения о пользователях, имеющих доступ"
    );

    companion object {
        fun fromTypeNumber(typeNumber: Int?): ReportFileName? {
            for (type in values()) {
                if (type.typeNumber == typeNumber) {
                    return type
                }
            }
            return null
        }
    }
}