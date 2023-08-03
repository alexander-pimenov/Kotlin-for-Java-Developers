package _utils

fun main() {
    println("Hello world")

    val properties = System.getProperties()
    properties.forEach { e -> println(e) }
    println("==========================")
    val getenv = System.getenv()
    getenv.entries.forEach { e -> println(e) }

}