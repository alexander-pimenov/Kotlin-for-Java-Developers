package _08constructors

fun main() {
    val linda = Human("Linda", 40)
    println(linda.description())
    linda.hadBirthday()
    println(linda.description())
    //---------------------------
    val p1 = Perzon("ali", "dehghani")
    println(p1)
    val p2 = Perzon("dehghani")
    println(p2)

}

/**
 * В этом классе мы вроде бы ни как не используем поле [doesSmile],
 * но с помощью него управляем еще одним параметром [nickename], которое
 * используется в методе [description] для добавления некоторых данных.
 */
class Human(private val name: String, private var age: Int, private var doesSmile: Boolean = true) {

    private var nickename: String

    init {
        val smiley = if (doesSmile) ":)" else ":("
        nickename = "$name [$smiley]"
    }

    fun description() =
        "Name: $nickename, age: $age."

    fun hadBirthday() {
        age += 1
    }
}

/**
 * Компилятор Kotlin сгенерирует большой конструктор, содержащий логику всех
 * инициализаторов свойств и инициализаторов блоков инициализации.
 * Проще говоря, блоки инициализации и инициализаторы свойств станут частью
 * основного конструктора.
 */
data class Perzon(val firstName: String, val lastName: String) {

    private val fullName: String = "$firstName $lastName".trim()
        .also { println("Initializing full name") }

    init {
        println("You're $fullName")
    }

    //инициализатор свойств
    private val initials =  "${firstName.firstOrEmpty()}${lastName.firstOrEmpty()}".trim()
        .also { println("Initializing initials") }

    init {
        println("You're initials are $initials")
    }

    constructor(lastName: String): this("", lastName) {
        println("I'm secondary")
    }

    private fun String.firstOrEmpty(): Char = firstOrNull()?.toUpperCase() ?: ' '
}

