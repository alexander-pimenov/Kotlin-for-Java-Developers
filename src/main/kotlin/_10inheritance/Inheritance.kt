package _10inheritance

fun main() {
    val laserPrinter = LaserPrinter("epson")
    laserPrinter.printModel()
    println(laserPrinter.bestSellingPrice())

    SubclassOfClassWithoutPrimaryConstructor(10)

    val sam = RestaurantCustomer("Sam", "Mixed salad")
    sam.greet() // An implementation of an abstract function
    sam.order() // A member function
    sam.eat() // An implementation of an interface function
    sam.pay(10) // A default implementation in an interface
}

abstract class Printer(val modelName: String) {
    // 'normal' classes are final by default - "обычные" классы по умолчанию являются конечными/финальными
    // 'open' classes can be inherited from - "открытые" классы могут быть унаследованы
    // 'abstract' classes are open by default. - "абстрактные" классы по умолчанию открыты.
    // otherwise, what's the point? - иначе какой в этом смысл?

    // similarly, functions are final by default, so must be declared open to be overridden
    // аналогично, функции по умолчанию являются окончательными, поэтому для переопределения они должны быть объявлены открытыми
    open fun printModel() = println("the model name is $modelName")

    // abstract function must be implemented by the subclass
    abstract fun bestSellingPrice(): Double
}

open class LaserPrinter(modelName: String) : Printer(modelName) {
    // class extending Printer using the default primary constructor (remember the ()'s)

    // add override keyword to override implementation of super class
    // otherwise we would create two functions with the same signature
    override fun printModel() = println("the model name of this laser printer is $modelName")

    // abstract function defined in the super class must be implemented in the sub class with override keyword
    // final prevents us from overriding this method in any subclass (like MultiFunctionLaserPrinter
    final override fun bestSellingPrice(): Double = 129.99
}

class MultiFunctionLaserPrinter(modelName: String) : LaserPrinter(modelName) {

    override fun printModel() = println("the model of that MultiFunctinLasterPrinter is $modelName")
    // cannot override bestSellingPrive because it is final
    // override fun bestSellingPrice(): Double = 129.99
}

open class ClassWithoutPrimaryConstructor {

    val someProperty: Int

    constructor(param: Int) {
        someProperty = param
        println("im the superclass constructor")
    }
}

class SubclassOfClassWithoutPrimaryConstructor : ClassWithoutPrimaryConstructor {

    // every secondary constructor delegates to the primary constructor
    // we can only explicitly call the secondary constructor of the super class if it does not have a primary constructor
    // каждый вторичный конструктор делегирует полномочия первичному конструктору,
    // мы можем явно вызвать вторичный конструктор суперкласса только в том случае, если у него нет первичного конструктора
    constructor(param: Int) : super(param) {
        println("im the subclass constructor")
    }
}
// data classes cannot be extended - data классы не могут быть расширены
// open data class DataClassParent(val x: Int) { }

abstract class Human(val name: String) {
    //абстрактный метод абстрактного класса
    abstract fun greet()
}

interface FoodConsumer {
    //абстрактный метод в интерфейсе
    fun eat()
    //дефолтный метод в интерфейсе
    fun pay(amount: Int) = println("Delicious! Here's $amount bucks!")
}

class RestaurantCustomer(name: String, val dish: String) : Human(name), FoodConsumer {
    fun order() = println("$dish, please!")
    override fun greet() {
        println("*Eats $dish*")
    }

    override fun eat() {
        println("It's me, $name")
    }
}
