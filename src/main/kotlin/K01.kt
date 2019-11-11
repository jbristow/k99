/*
 * P01 (*) Find the last element of a list.
 *   Example:
 *     ?- myLast(["a","b","c","d"]).
 *     "d"
 */
tailrec fun <T> myLast(xs: List<T>): T {
    return when {
        xs.size == 1 -> xs.first()!!
        else -> myLast(xs.drop(1))
    }
}

fun main() {
    println(
        myLast(listOf("a", "b", "c", "d"))
    )
// returns "d"
    println(
        myLast(listOf(1, 2, 4, 3))
    )
// returns 3
    println(
        myLast(listOf(listOf(1, 2, 3), listOf(3, 4, 5), listOf(3, 1, 4, 1, 5), listOf(5, 1)))
    )
// returns [5, 1]

}
