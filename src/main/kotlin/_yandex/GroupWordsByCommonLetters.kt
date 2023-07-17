package yandex

/**
 * Kotlin за час. Теория и практика.
 * 3-я задача:
 * https://youtu.be/30tchn0TjaM?t=4500
 */
fun main() {
    /**
     * Sample Input ["eat", "tea", "tan", "ate", "nat", "bat"]
     * Sample Output [ ["ate", "eat", "tea"],["nat", "tan"],["bat"] ]
     * Т.е. сгруппировать слова по "общим буквам"
     */

    println(groupWordsB(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")))
}

fun groupWordsB(words: Array<String>): List<List<String>> {

    val result: MutableList<List<String>> = mutableListOf()

    val map = mutableMapOf<String, MutableList<String>>()

    for (word in words) {
        //берем слово сортируем лексикографически по чарам и соединяем опять в строку
        val sortedWord = word.toCharArray().sorted().joinToString("")

        if (map.containsKey(sortedWord)) {
            //1. обращаемся к мапе по ключу (указанному в [..])
            //2. нам возвращается список стрингов
            //3. добавляем в него слово
            map[sortedWord]?.add(word) //сейфколл (?) нужен как проверка на null
            //если в переменной map есть ключ, то вернет список лежащий по этому ключу, и можем добавить в него слово,
            //а если нет, т.е. там ключ=null, то вернет null и вызов add не произойдет, вернется null, но никакого NPE Не будет,
            //и никакого иксепшена тоже не выбрасывается
        } else {
            //если ключа в мапе еще нет, то создаем его и кладем в него
            //пустой изменяемый список
            map[sortedWord] = mutableListOf(word)
        }
    }
    //можем посмотреть, как выглядит мапа
    println(map)

    //пройдемся по мапе и выберем из нее списки, которые в ней сформировались
    for ((key, value) in map) {
        result.add(value)
    }
    return result
}