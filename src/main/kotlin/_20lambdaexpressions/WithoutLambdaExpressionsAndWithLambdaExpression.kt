package _20lambdaexpressions


fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // ===== Если не использовать лямбды =====
    // Используем анонимный класс для передачи предиката.
    //Т.е если не использовать лямбды, то необходимо реализовывать в аргументе анонимный класс,
    // который реализует этот интерфейс Predicate.
    val evenNumbers1 = numbers.filter1(object : Predicate<Int> {
        override fun test(param: Int): Boolean {
            return param % 2 == 0
        }
    })

    println(evenNumbers1)  // Вывод: [2, 4]

    // ===== Сила использования лямбд =====
    // Используем лямбду для передачи предиката
    val evenNumbers2 = numbers.filter { it % 2 == 0 } //то же что и последняя строчка в переопределенном методе примере выше: param % 2 == 0

    println(evenNumbers2)  // Вывод: [2, 4]
}

// ===== Если не использовать лямбды =====
// Нужно определить интерфейс для функции
interface Predicate<in T> {
    fun test(param: T): Boolean
}

// Функция filter, которая принимает предикат как аргумент, используется в качестве фильтрации элементов списка.
fun <T> List<T>.filter1(predicate: Predicate<T>): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (predicate.test(item)) {
            result.add(item)
        }
    }
    return result
}

// ===== Сила использования лямбд смотри в main() =====
// Функция filter с лямбдой
// В аргумент передается функция, которая принимает один параметр типа T и возвращает значение типа Boolean.
fun <T> List<T>.filter2(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (predicate.invoke(item)) { //invoke можно опустить (тут просто для примера)
        //if (predicate(item)) {
            result.add(item)
        }
    }
    return result
}
// В Kotlin `invoke` — это специальная функция, которая позволяет вызывать объект как функцию.
// Если у класса есть метод `invoke`, то экземпляр этого класса можно вызывать как функцию.
// Лямбда-выражения в Kotlin — это объекты, которые реализуют функциональный интерфейс.
// У них есть метод `invoke`, который позволяет вызывать лямбду как функцию.


//Библиотечная функция filter, которая принимает предикат как аргумент, используется в качестве фильтрации элементов списка
///**
// * Returns a list containing only elements matching the given [predicate].
// *
// * @sample samples.collections.Collections.Filtering.filter
// */
//public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
//    return filterTo(ArrayList<T>(), predicate)
//}







