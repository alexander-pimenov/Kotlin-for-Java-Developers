package _20lambdaexpressions

fun main() {

    // lambdas are declared in curly braces and can be assigned to a variable
    // but other than in Java, we can execute them with a run-command:
    run { println("i'm in a lambda") }

    val employees = listOf(
        Employee("John", "Smith", 2010),
        Employee("Joe", "Miller", 2005),
        Employee("Jane", "Anderson", 2000),
        Employee("Mike", "Ness", 2015)
    )

    // minBy takes lambda to find lowest value
    // we can write lambdas outside the () if it is the last parameter of the funciton. Otherwise it has to go inside
    // usually the compiler can infer the type of e, but if not, we can tell the compiler with the usual e: Employee
    println(employees.minByOrNull { e -> e.startYear })

    // if there is only one param to the lambda, we dont need to declare it, but use 'it' instead
    println(employees.maxByOrNull { it.startYear })

    // local variables are accessible from within lambdas:
    var num = 10
    run {
        num += 15
        println(num)
    }
    println(num)

    // using member reference
    println(employees.minByOrNull { e -> e.startYear })

    // using run with a top level function
    run(::topLevel)
}

fun topLevel() = "i'm a top level function"

fun useParameter(employees: List<Employee>, num: Int) {
    employees.forEach {
        println(it.firstName)
        println(num)
    }
}
