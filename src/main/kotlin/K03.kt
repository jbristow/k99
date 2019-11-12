/*
 * P03 (*) Find the K'th element of a list.
 *   The first element in the list is number 1.
 *    Example:
 *      ?- elementA(3,listOf["a","b","c","d","e"]).
 *      "c"
 */
tailrec fun <T> elementAt(x: Int, xs: List<T>): T {
    return when (x) {
        1 -> xs.first()!!
        else -> elementAt(x - 1, xs.drop(1))
    }
}

fun main() {
    println(
        elementAt(2, listOf("a", "b", "c", "d"))
    )
// returns "b"
    println(
        elementAt(4, listOf(1, 2, 4, 3))
    )
// returns 3
    println(
        elementAt(1, listOf(listOf(1, 2, 3), listOf(3, 4, 5), listOf(3, 1, 4, 1, 5), listOf(5, 1)))
    )
// returns [1,2,3]
}
