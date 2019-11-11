tailrec fun <X> pack(remaining: List<X>, curr: List<X> = emptyList(), output: List<List<X>> = emptyList()): List<List<X>> {
    return when {
        remaining.isEmpty() && curr.isEmpty() -> output
        remaining.isEmpty() -> output.plusElement(curr)
        curr.isEmpty() -> pack(remaining.drop(1), curr = listOf(remaining.first()), output = output)
        curr.last() == remaining.first() -> pack(remaining.drop(1), curr = curr + remaining.first(), output = output)
        else -> pack(remaining.drop(1), curr = listOf(remaining.first()), output = output.plusElement(curr))
    }
}

fun main() {
    println(pack(listOf("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e")))
}