/*
 * K05 (*) Reverse a list.
 */

private tailrec fun <T> reverseInner(reversed: List<T>, xs: List<T>): List<T> =
    when {
        xs.isEmpty() -> reversed
        else -> reverseInner(listOf(xs.first()) + reversed, xs.drop(1))
    }

fun <T> reverse(xs: List<T>) = reverseInner(emptyList(), xs)

fun main() {
    println(
        reverse(listOf("a", "b", "c", "d"))
    )
// returns ["d","c","b","a"]
    println(
        reverse(listOf(1, 2, 4, 3))
    )
// returns [3,4,2,1]
    println(
        reverse(listOf(listOf(1, 2, 3), listOf(3, 4, 5), listOf(3, 1, 4, 1, 5), listOf(5, 1)))
    )
// returns [[5, 1], [3, 1, 4, 1, 5], [3, 4, 5], [1, 2, 3]]
}
