/*
 * P04 (*) Find the number of elements of a list.
 */

private tailrec fun <T> lengthInner(len: Int, xs: List<T>): Int =
    when {
        xs.isEmpty() -> len
        else -> lengthInner(len + 1, xs.drop(1))
    }

fun <T> length(xs: List<T>) = lengthInner(0, xs)

fun main() {
    println(
        length(listOf("a", "b", "c", "d"))
    )
// returns 4
    println(
        length(listOf(1, 2, 4, 3))
    )
// returns 4
    println(
        length(listOf(listOf(1, 2, 3), listOf(3, 4, 5), listOf(3, 1, 4, 1, 5), listOf(5, 1)))
    )
// returns 4
}
