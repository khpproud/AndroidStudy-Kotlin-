fun <T> varargsFun(a1: Int, vararg array: T) {
    for (a in array) {
        println(a)
    }
}

fun main() {
    varargsFun(10, "hello", "world")
    varargsFun(10, 20, false)
}