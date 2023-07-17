package yandex

/**
 * Kotlin за час. Теория и практика.
 * 1-я задача:
 * https://youtu.be/30tchn0TjaM?t=3807
 */
fun main() {
    //Даны два массива: [1,2,3,2,0,2,2] и [5,1,2,7,3,2]
    //Надо вернуть [1, 2, 2, 3] (порядок не важен)

    println(
        getRepeatedIntersection(
            intArrayOf(1, 2, 3, 2, 0, 2, 2),
            intArrayOf(5, 1, 2, 7, 3, 2)
        )
    )
}

fun getRepeatedIntersection(arr1: IntArray, arr2: IntArray): List<Int> {
    //сделаем их массивов HashSet
    val s1 = arr1.toHashSet()
    val s2 = arr2.toHashSet()

    //результат
    val result = mutableListOf<Int>()

    for (el in s1) {
        if (s2.contains(el)) {
            //получим минимальное число встречаний числа в массиве
            val numOfRepeats = minOf(arr1.count { it == el }, arr2.count { it == el })
            //Выполняет заданное функциональное действие в {...} указанное в параметре количество раз.
            //Аналог цикла for, но немного изящнее выглядит.
            repeat(numOfRepeats) { result.add(el) }
        }
    }
    return result
}