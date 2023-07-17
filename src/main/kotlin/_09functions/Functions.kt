package _09functions

fun main() {
    println(labelledMultiply(3, 4))
    println(simplifiedLabelledMultiply(9, 4, "The result is:"))
    // if you name one argument, all of them must be named
    println(simplifiedLabelledMultiply(label = "Here's the result:", operand2 = 4, operand1 = 6))
    // this can be useful if you have functions with lots of params. Self documenting code!
    println(functionWithLotsOfArgs(height = 3,
        width = 4,
        depth = 5,
        weight = 12))

    // class functions
    val emp1 = Employee("jane")
    println(emp1.uppercaseFirstName())
    val emp2 = Employee("joe")
    val emp3 = Employee("john")
    printEmployeeNamesInUppercase(emp1, emp2, emp3)

    val employeeList = arrayOf(emp1, emp2, emp3)
    // this would work in J ava, but not in Kotlin:
    // printEmployeeNamesInUppercase(employeeList)
    // so we can use the spread-operator to unpack the array
    printEmployeeNamesInUppercase(*employeeList)
    // we can use this for example to
    val anotherEmployeeList = arrayOf(emp1, emp2, emp3)
    val fullEmployeeList = arrayOf(emp1, *employeeList, *anotherEmployeeList)
    for (emp in fullEmployeeList) {
        println(emp)
    }

    println("===== Функции =====")

    println(testSimple1())
    println(testSimple2())
    println(testSimple3(4, 5))
    println(testSimple4(6, 8))
    testSimple5(7, 9)

    //посмотрим, как работают именованные аргументы
    //когда в функции большое количество аргументов и мы явно указываем
    //какому аргументу передаем значение
    testNamedArguments(4, 5, 6)
    testNamedArguments(z = 4, x = 5, y = 6)

    //вызов функции с аргументами по умолчанию
    testDefaultArguments()
    testDefaultArguments(y = 15)
    testDefaultArguments(x = 25)
    testDefaultArguments(35)
    testDefaultArguments(10, 20)
    println(foo("Vasya"))
    println(foo("Vasya", 55))
    println(foo("Vasya", 55, true))
    println(foo(number = 655, name = ""))

    println("----------Печатаем четные числа----------")
    //нужно через запятую указать элементы
    printEvent(1, 2, 3, 4, 5, 6, 7, 8, 9)

    //Если у нас есть массив, то с помощью оператора * мы его распределим на варарги
    //и после элементов из массива можем еще указывать элементы
    printEvent(*intArrayOf(1, 2, 3, 4, 5), 6, 7, 8, 9)

    //создадим диапазон, преобразуем его в массив и используя * используем в качестве
    //аргумента в функции
    val intRange = 100..110
    printEvent(*intRange.toList().toIntArray())
    println("------------------------------")
}

// default return type is Unit, unless specified.
// this is redundant:
// fun redundantReturnType(args: Array<String>): Unit { }

// simple function with block body and default param. Function param defaults require the type
fun labelledMultiply(operand1: Int, operand2: Int, label: String = "The answer is:"): String {
    return ("$label ${operand1 * operand2}")
}

// one line function with expression body that returns whatever follows the = sign, the compiler can infer the return type
// even the main function could have an expression body
fun simplifiedLabelledMultiply(operand1: Int, operand2: Int, label: String) =
    "$label ${operand1 * operand2}"

// this isn't going to work bc return type mismatch, found Int, required Unit
// fun notWorkingExample(): Unit = 3 * 4

fun functionWithLotsOfArgs(height: Int, width: Int, depth: Int, weight: Int): Int {
    return (height * width * depth * weight)
}

data class Employee(val firstName: String) {
    // public and final by default
    fun uppercaseFirstName() = firstName.uppercase()
}

fun printEmployeeNamesInUppercase(vararg employees: Employee) {
    // vararg should be the last argument in the function signature
    // otherwise we have to use names parameter in the function call to let the compiler know which params are part of
    // the vararg and which of the regular function params
    for (employee in employees) {
        println(employee.uppercaseFirstName())
    }
}

//простая функция без аргументов и возвращающая целое число
fun testSimple1(): Int {
    return 5 + 5
}

//более простая запись простой функции - в одну строчку
fun testSimple2(): Int = 5 + 5

//функция с аргументами
fun testSimple3(x: Int, y: Int): Int = x + y

//функция с аргументами и типом String
fun testSimple4(x: Int, y: Int): String {
    return "String -> ${x + y}"
}

//функция с аргументами типа VOID
fun testSimple5(x: Int, y: Int) {
    println("String -> ${x + y}")
}

//функция с именованными аргументами
fun testNamedArguments(x: Int, y: Int, z: Int): List<Int> {
    return listOf(x, y, z)
}

//аргументы по умолчанию (такое есть в Python)
//это очень хорошая альтернатива перегрузке методов !!!
fun testDefaultArguments(x: Int = 1, y: Int = 2) {
    println(x + y)
}

//В Kotlin не нужна перегрузка методов, т.к. есть функции с аргументами по-умолчанию
fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false): String {
    return (if (toUpperCase) name.uppercase() else name) + number
}


//vararg
//в этой функции будем печатать пришедшие аргументы, но только четные
fun printEvent(vararg numbers: Int) {
    numbers.forEach { e -> if (e % 2 == 0) println(e) }
}