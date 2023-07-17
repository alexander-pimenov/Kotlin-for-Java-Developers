package _05nullreferences

/**
 * Приведем пример, как Kotlin решает проблему NPE.
 */
fun main(args: Array<String>) {

    println("===== Решаем проблему NPE =====")

    //создадим переменную
    //в ней не может лежать никогда Null
    var a: String = "Hello"
    //a = null //NPE исключен!!!

    println(a.length)

    //НО все же можно создать переменную которая может быть Null
    var b: String? = "Java"
    b = null

    //и на таких переменных мы не можем вызывать методы напрямую, т.к.
    //может быть NPE и компилятор будет нас ограничивать
    //и говорить, что только сейфколы(безопасный вызов) можно вызывать на такого типа переменных
    //println(b.length)
    //если в переменной есть значение, то вернет длину этого слова, а если нет, т.е. там лежит
    //null, то вернет null и никакого NPE Не будет, и никакого иксепшена тоже не выбрасывается
    println(b?.length)

    //эти вызовы полезны в цепочке вызовов методов
    // bob?.department?.head?.name -> null, если где-нибудь в цепочке будет null, вернется null

    //если попробовать сделать сейфколл на переменной a, то IDEA подскажет, что это не нужное действие
    a?.length

    //Оператор Elvis - ?:  -> тоже работает с null
    //если в переменной есть значение, то получаем значение, а если null, то хотим получить
    // какое-то другое значение
    val l = b?.length ?: -1

    //внесем некоторую случайность
    b = if ((0..10).random() > 5) "asdf" else null
    // !! - еще оператор, работающий с null -> выбрасывает NPE в случае если в переменной null
    //т.е. будет ошибка в программе
    val t = b!!.length


    //--------------------------------------------------

    val strOne: String? = null

    val strTwo: String? = "Nullable, but not null"
    // плохая практика   :-(
    if (strTwo != null) strTwo.toUpperCase()

    // хорошая практика   :-)
    strTwo?.toUpperCase()
    println("Let's see what happens here: ${strOne?.toUpperCase()}")


    // null checks
    val country = Country(3)
    val address = Address(country)
    val branch = Branch(address)

    val code: Int? = branch?.address?.country?.code
    println("code without `null`: $code")

    val code2: Int? = Branch(null)?.address?.country?.code ?: 599
    println("code with default, cause `null`: $code2")


    // elvis operator
    val nullString: String? = null
    val nonNullString: String = nullString ?: "Jail House Rock"

    // safe cast operator, datatype has to be nullable (: String?)
    val something: Any = arrayOf(1, 2, 3, 4)
    val someString = something as? String
    println("something as? String : $someString")

    // non null assertion with !! If str is indeed null, 'NullPointerException' is thrown
    val noNullString: String? = "This is definitely noy null"
    val anotherNotNullString = noNullString!!.toUpperCase()

    // deliberately causing an exception. The exception references line 49, and not line 50 though!
    // val anotherNullString = nullString!!
    // val upperNullString = anotherNullString.toUpperCase()

    // avoid chaining non-null assertions in this fashion. If an exception occurs we dont know which of the values was null
    val someCode = branch!!.address!!.country!!.code

    val nonNullString2: String? = "Definitely not null"
    // method printText expects String, not String? so this would not work
    // printText(nonNullString2)
    // this works but is not good practise as it would throw an exception if the string was null.
    printText(nonNullString2!!)

    // long hand version of what we should do
    if (nonNullString2 != null) {
        printText(nonNullString2)
    }

    // and shorthand for the same thing
    // 'let' expects a lambda expression, and 'it' refers to the object that is calling 'let
    nonNullString2?.let { printText(it) }

    // '==' defers to the equal-method of nullString, but is null safe:
    println(nullString == nonNullString)

    // nullable arrays
    val nullIntArray = arrayOfNulls<Int?>(5)
    for (i in nullIntArray) {
        println(i)
    }
    println(nullIntArray[3].toString())
}

class Branch(val address: Address?)
class Address(val country: Country)
class Country(val code: Int)

fun printText(text: String) {
    println(text)
}