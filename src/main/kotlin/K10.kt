/*
 * K10 ($) Run-length encoding of a list.
 * Use the result of problem [P09](#k09) to implement the so-called run-length encoding
 * data compression method. Consecutive duplicates of elements are encoded as
 * pairs `(N to E)` where `N` is the number of duplicates of the element `E`.
 */
tailrec fun <X> encode(
    remaining: List<X>,
    curr: List<X> = emptyList(),
    output: List<Pair<Int, X>> = emptyList()
): List<Pair<Int, X>> {
    return when {
        remaining.isEmpty() && curr.isEmpty() -> output
        remaining.isEmpty() -> output + (curr.size to curr.first())
        curr.isEmpty() -> encode(remaining.drop(1), curr = listOf(remaining.first()), output = output)
        curr.last() == remaining.first() -> encode(remaining.drop(1), curr = curr + remaining.first(), output = output)
        else -> encode(
            remaining.drop(1),
            curr = listOf(remaining.first()),
            output = output + (curr.size to curr.first())
        )
    }
}

fun main() {
    println(
        encode(listOf("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e"))
    )
}
