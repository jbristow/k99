tailrec fun <X> compress(remaining: List<X>, output: List<X> = emptyList()): List<X> {
    return when {
        remaining.isEmpty() -> output
        output.isEmpty() -> compress(remaining.drop(1), output = listOf(remaining.first()))
        remaining.first() == output.last() -> compress(remaining.drop(1), output = output)
        else -> compress(remaining.drop(1), output = output + remaining.first())
    }
}

fun main() {
    println(compress(listOf("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e")))
}