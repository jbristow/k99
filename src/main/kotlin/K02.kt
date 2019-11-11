/*
 * P02 (*) Find the last but one element of a list.
 *   (zweitletztes Element, l'avant-dernier élément)
 */
tailrec fun <T> myLastButOne(xs: List<T>): T {
    return when {
        xs.drop(1).size == 1 -> xs.first()!!
        else -> myLastButOne(xs.drop(1))
    }
}

fun main() {
    println(
        myLastButOne(listOf("a", "b", "c", "d"))
    )
// returns "d"
    println(
        myLastButOne(listOf(1, 2, 4, 3))
    )
// returns 3
    println(
        myLastButOne(listOf(listOf(1, 2, 3), listOf(3, 4, 5), listOf(3, 1, 4, 1, 5), listOf(5, 1)))
    )
// returns [3, 1, 4, 1, 5]
}