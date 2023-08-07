package _20lambdaexpressions

fun main() {

    println(countTo100UsingWith())
    println(countTo100UsingApply())

    val employees = listOf(
        Employee("John", "Smith", 2010),
        Employee("Joe", "Miller", 2005),
        Employee("Jane", "Anderson", 2000),
        Employee("Mike", "Ness", 2015)
    )

    findByLastName(employees, "Smith")
    findByLastName(employees, "Hudson")

    // using labels to get the right receiver object in nested apply methods
    // obvs naming the parameters is much better than labels
    "Some String".apply outer@{
        "Another String".apply {
            println(toLowerCase())
            println(this@outer.toUpperCase())
        }
    }

    println("===== Lambda =====")

    //создадим список и позапускаем на нем методы

    val aNum = listOf(1, 2, 3, 4, 5, 6, 4, 3, 2, 1)

    //lambda в Kotlin помещается в фигурные скобки
    aNum.forEach { e -> println(e) }
    println(aNum.map { e -> e * 2 })
    println(aNum.filter { e -> e % 2 == 0 })
    println(aNum.reduce { sum, e -> sum + e }) //sum=0 - аккумулятор
    println(aNum.sum())

    /**it - это временная переменная для прохода по коллекции должна всегда называться it*/
    //сортируем по убыванию по самим (it) элементам
    val sortedByDescending1 = aNum.sortedBy { it }
    println(sortedByDescending1) //[1, 1, 2, 2, 3, 3, 4, 4, 5, 6]

    //сортируем по убыванию по длине элемента (it.length)
    val aStr = listOf("15", "2000", "132", "40", "5")
    val sortedByDescending = aStr.sortedBy { it.length }
    println(sortedByDescending) //[5, 15, 40, 132, 2000]

    val userFlow = listOf("Tom", "Bob", "Kate", "Sam", "Alice")
    val reducedValue = userFlow.reduce { a, b -> "$a $b" }
    println(reducedValue)   // Tom Bob Kate Sam Alice

    //any проходит по списку и вернет true если хотя бы один элемент удовлетворяет условию
    println(aNum.any { it > 10 })
    //all проходит по списку и вернет true если все элементы удовлетворяют условию
    println(aNum.all { it < 10 })

    //partition - может разделить коллекцию на два списка по условию
    val aNum2 = listOf(1, -2, 3, -4, 5, -6)
    val (positive, negative) = aNum2.partition { it > 0 }
    println(positive) //[1, 3, 5]
    println(negative) //[-2, -4, -6]

    //groupBy - сгруппируем, например по длине слова
    val users = listOf("Om", "Bob", "Kate", "Sam", "Alice")
    //на выходе получим HashMap
    val result = users.groupBy { it.length } //HashMap или LinkedHashMap
    println(result) //{2=[Om], 3=[Bob, Sam], 4=[Kate], 5=[Alice]}


    //Переменная может представлять функцию
    val message: () -> Unit
    message = ::hello
    message()

    // переменная ссылается на функцию с параметрами sum
    var operation: (Int, Int) -> Int = ::sum
    val result2 = operation(3, 5)
    println(result2) // 8

    // operation указывает на функцию subtract
    operation = ::subtract
    val result3 = operation(14, 5)
    println(result3) // 9

    println(alphabetUsingApply())
    println(alphabetUsingBuildStringFunction())
}

// with converts the instance you are passing to a receiver object, and inside the lambda we don't need to refer to the receiver
fun countTo100UsingWith() =
    with(StringBuilder()) {
        for (i in 1..99) {
            append(i)
            append(", ")
        }
        append("100")
    }.toString()

// Using apply is similar to with, but apply returns the receiver object, that apply is called on (SB in this case
fun countTo100UsingApply() =
    StringBuilder().apply {
        for (i in 1..99) {
            append(i)
            append(", ")
        }
        append("100")
        toString()
    }

// return in the lambda returns from both the lambda and the function if the calling funciton (forEach) is inlined
// this is called a non-local return
// we can use labels to modify this behaviour. Again, this can get sticky quickly like in this example right here
fun findByLastName(employees: List<Employee>, key: String) {
    employees.forEach returnBlock@{
        if (it.lastName == key) {
            println("yes, there is an employee with the last name $key")
            return@returnBlock
        }
    }
    println("nope, there is no employee with the last name $key")
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun subtract(a: Int, b: Int): Int {
    return a - b
}

fun hello() {
    println("Hello Kotlin")
}

// Использование apply для генерации алфавита
fun alphabetUsingApply() =
    StringBuilder().apply {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know alphabet!!!")
        toString()
    }

fun alphabetUsingBuildStringFunction() =
    buildString {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know alphabet!!!")
    }