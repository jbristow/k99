tailrec fun <X> encode(remaining: List<X>, curr: List<X> = emptyList(), output: List<Pair<Int, X>> = emptyList()): List<Pair<Int, X>> {
    return when {
        remaining.isEmpty() && curr.isEmpty() -> output
        remaining.isEmpty() -> output + (curr.size to curr.first())
        curr.isEmpty() -> encode(remaining.drop(1), curr = listOf(remaining.first()), output = output)
        curr.last() == remaining.first() -> encode(remaining.drop(1), curr = curr + remaining.first(), output = output)
        else -> encode(remaining.drop(1), curr = listOf(remaining.first()), output = output + (curr.size to curr.first()))
    }
}

fun main() {
    println(encode(listOf("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e")))
}