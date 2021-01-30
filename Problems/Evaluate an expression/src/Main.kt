import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val x = scanner.nextDouble()
    println("${pot(x, 3) + pot(x, 2) + x + 1}")
}

fun pot( a: Double,  b: Int): Double {
    var result = 1.0
    for(i in 1..b)
        result *= a
    return result
}