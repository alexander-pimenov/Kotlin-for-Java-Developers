package _01helloworld

fun main() {
    println("Hello, World! ")

    val properties = System.getProperties()
    properties.forEach { e -> println(e) }
    println("==========================")
    val getenv = System.getenv()
    getenv.entries.forEach { e -> println(e) }
}