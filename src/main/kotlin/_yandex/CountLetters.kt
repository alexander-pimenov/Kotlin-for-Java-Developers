package yandex

/**
 * Kotlin за час. Теория и практика.
 * 2-я задача:
 * https://youtu.be/30tchn0TjaM?t=4201
 */
fun main() {
    //Посчитать количество раз повторений буквы в выражении
    // AAAABBBCCXYZDDDDDEEEFFFAAAABBBBBBBBBBBBBBBBB -> A4B3C2XYZD5E3F3A4B17
    println(countLetters("AAAABBBCCXYZDDDDDEEEFFFAAAABBBBBBBBBBBBBBBBB"))
}

fun countLetters(str: String): String {
    //указатель на первую букву в строке
    var currentLetter = str[0]
    //переменная счетчик
    var count = 1
    //результат
    var result = ""

    //итерируемся по подстроке начиная с 1 элемента
    for (letter in str.substring(1)) {
        if (currentLetter == letter) {
            count++
        } else {
            if (count == 1)
                result += currentLetter
            else
                result += "$currentLetter$count"

            //сбрасываем счетчик
            count = 1
            //укажем текущую букву на букву, на которой сейчас находимся
            currentLetter = letter
        }
    }

    //обработаем последние повторяющиеся буквы в строке
    if (count == 1)
        result += currentLetter
    else
        result += "$currentLetter$count"

    return result
}