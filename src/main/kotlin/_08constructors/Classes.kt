package _08constructors

fun main() {
    println("===== Классы и объекты =====")
    val p1 = Person("Bob", "Jones", 35)
    //что бы вызвать поле мы не используем геттеры, просто напрямую обращаемся к полю
    println(p1.firstName)

    //создадим человека с ребенком
    val child = Person("Sash", "Green", 10)
    val p2 = Person("Jack", "Green", 30, child)
    println(p2.toString())

    //поработаем с прямоугольниками
    val rectangle1 = Rectangle(5.0, 4.0)
    println("The perimeter is ${rectangle1.perimetr}")
    println(rectangle1)
    println("The area is ${rectangle1.area()}")

    //Создадим еще второй равный  прямоугольник сравним их.
    //Это возможно, т.к. созданы equals и hashCode.
    val rectangle2 = Rectangle(5.0, 4.0)
    println(rectangle1 == rectangle2) //true

}

/**
 * Класс создается сразу с ПЕРВИЧНЫМ конструктором (Primary Constructor)
 * с указанием полей - это главный конструктор с помощью которого будут
 * создаваться объекты класса.
 *
 * Так же возможны и вторичные конструкторы, которые нам могут понадобиться для
 * создания объекта. Вторичный конструктор ВСЕГДА должен вызывать в себе
 * первичный конструктор.
 */
class Person(val firstName: String, val lastName: String, var age: Int) {
    //добавим еще полей
    //у человека могут быть дети
    //создадим поле с типом лист и инициализируем его пустым списком
    var children: MutableList<Person> = mutableListOf() //ArrayList

    //в первичном конструкторе нет кода, и если при создании объекта нам нужно
    //выполнить какой-то код, то выполняем в init блоке
    init {
        println("Person $firstName $lastName is created")
    }

    //Вторичный конструктор - похож на перегрузку конструктора в Java
    //когда мы можем задать конструктор с разными аргументами.
    //Вызов первичного происходит с помощью синтаксиса : this(...), в аргументе
    //пишем аргументы первичного конструктора
    constructor(firstName: String, lastName: String, age: Int, child: Person) : this(firstName, lastName, age) {
        //добавим в список ребенка
        children.add(child)
    }

    //Так же можно создать конструктор без аргументов, но он так же должен
    //вызывать первичный конструктор в себе, но уже в первичном конструкторе можно
    //прописать аргументы по-умолчанию, например: "","",-1
    constructor() : this("", "", -1)

    //Как и в Java переопределим метод toString, чтобы выводить удобочитаемую
    //информацию о человеке
    override fun toString(): String {
        return "Person(firstName='$firstName', lastName='$lastName', age=$age, children=$children)"
    }
}

/**
 * Создадим еще один класс - Прямоугольник.
 * Пометим его словом data и тогда автоматически буду созданы
 * методы equals, hashCode, toString
 */
data class Rectangle(var height: Double, var length: Double) {
    //добавим ему поле, в котором будет считаться периметр
    var perimetr = (height + length) * 2

    //также можем создать кастомные геттеры и сеттеры
    //field обращается к полю, как this
    var test = 1
        get() = field + 1 //this.поле
        set(value) {
            if (value < 0) println("Negative value. Set positive value")
            field = value
        }

    //создадим и функцию, вычисляющую площадь внутри класса
    fun area() = height * length
}