package _08lazydelegate

fun main() {
    //список ЗП людей
    val salaries = listOf(50, 80, 70)
    //тут перебираем ЗП и у кого ЗП выше указанного порога,
    //тот платит налоги
    for (salary in salaries) {
        taxCollector.payTaxes(salary)
        if (salary > SALARY_THRESHOLD) accountant.findTaxSaving()
    }

}

/**
 * Пример ленивой инициализации - lazy initialization (lazy delegate),
 * т.е. будет создан только тогда, когда к нему обратятся.
 */
private const val SALARY_THRESHOLD = 100
private val taxCollector = TaxCollector()

//private val accountant = Accountant() //когда так, то при запуске приложения создается объект Accountant()
private val accountant by lazy { Accountant() }

class TaxCollector {
    init {
        println("init tax collector")
    }

    fun payTaxes(salary: Int) = println("Paying taxes on salary of $salary")
}

class Accountant {
    init {
        println("init account - this is a hard work!")
    }

    fun findTaxSaving() = println("\tFound tax savings!")
}

