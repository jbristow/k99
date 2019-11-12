/*
 *
 * K13 ($$) Run-length encoding of a list (direct solution).
 *
 * Implement the so-called run-length encoding data compression method directly.
 * I.e. don't explicitly create the sublists containing the duplicates, as in problem P09,
 * but only count them. As in problem P11, simplify the result list by replacing
 * the singleton terms [1,X] by X.
 */

tailrec fun <X> encodeDirect(
    remaining: List<X>,
    curr: List<X> = emptyList(),
    output: List<Pair<Int, X>> = emptyList()
): List<Pair<Int, X>> {
    return when {
        remaining.isEmpty() && curr.isEmpty() -> output
        remaining.isEmpty() -> output + (curr.size to curr.first())
        curr.isEmpty() -> encodeDirect(remaining.drop(1), curr = listOf(remaining.first()), output = output)
        curr.last() == remaining.first() -> encodeDirect(
            remaining.drop(1),
            curr = curr + remaining.first(),
            output = output
        )
        else -> encodeDirect(
            remaining.drop(1),
            curr = listOf(remaining.first()),
            output = output + (curr.size to curr.first())
        )
    }
}

fun main() {
    println(
        encodeDirect(listOf("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e"))
    )
}
