package _11Objects

fun main(args: Array<String>) {
    // we don't really have statics in kotlin, so in order to be able to access fields without creating an instance
    // of a class we can use the companion object
    println(SomeClass.accessPrivateVar())
    // this won't work though because we have no instance of SomeClass
    // println(SomeClass.accessSomeVar())

    val someInstance = SomeClass.factoryUpperOrLower("Well hello there", false)
    println(someInstance.someString)
    val someOtherInstance = SomeClass.factoryJustAssign("welcome")
    println(someOtherInstance.accessSomeVar())

    // with the private primary constructor, we cannot do this anymore
    // SomeClass("someString")

    val persons = listOf(
        Person("Jack", 10), Person("Alice", 12),
        Person("Jack", 20), Person("Bob", 11)
    )
    val sortedWithName = persons.sortedWith(Person.NameComparator)
    println(sortedWithName)
    val sortedWithAge = persons.sortedWith(Person.AgeComparator)
    println(sortedWithAge)
    val sortedWithNameThenAge = persons.sortedWith(Person.NameComparator.thenComparing(Person.AgeComparator))
    println(sortedWithNameThenAge)

}

class SomeClass private constructor(val someString: String) {

    // this is not accessible without instance
    fun accessSomeVar() = "$someVar"
    var someVar = 66

    companion object {
        // think of things in the companion object to be static
        private var privateVar = 6
        fun accessPrivateVar() = "I'm accessing private var: $privateVar"

        // we can also use the companion object to implement the factory pattern
        // rather than having a bunch of different secondary constructors
        // to make sure the only way to create instances of this class is through the factory, we add also the
        // private constructor keywords to the class declaration
        fun factoryJustAssign(str: String) = SomeClass(str)
        fun factoryUpperOrLower(str: String, lowerCase: Boolean): SomeClass {
            if (lowerCase) return SomeClass(str.toLowerCase())
            else return SomeClass(str.toUpperCase())
        }
    }
}

/**
 * Реализация интерфейса Comparator как вложенного объекта.
 * Здесь с помощью object мы реализуем разные компараторы, которые потом используем для сортировки объекта Person.
 */
data class Person(val name: String, val age: Int) {
    object NameComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int =
            p1.name.compareTo(p2.name)
    }

    object AgeComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int =
            p1.age - p2.age
    }
}
