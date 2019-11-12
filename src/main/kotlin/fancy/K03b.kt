package fancy

import arrow.core.Option
import fancy.types.Listable
import fancy.types.Listable.Empty
import fancy.types.Listable.Listy
import fancy.types.Listable.Single
import fancy.types.listListableOf

/*
 * P03 (*) Find the K'th element of a list.
 *   The first element in the list is number 1.
 *    Example:
 *      ?- elementA(3,listOf["a","b","c","d","e"]).
 *      "c"
 */

fun <T> elementAtB(x: Int, xs: Empty<T>) = Option.empty<Listable<T>>()
fun <T> elementAtB(x: Int, xs: Single<T>) =
    when (x) {
        1 -> Option.just(xs)
        else -> Option.empty()
    }

val x = 1 > 2

fun <T> elementAtB(x: Int, xs: Listy<T>): Option<Listable<T>> {
    return when {
        x < 1 -> Option.empty()
        x == 1 -> Option.just(xs.head())
        else -> when (xs.tail()) {
            is Single -> elementAtB(x - 1, xs.tail() as Single)
            is Listy -> elementAtB(x - 1, xs.tail() as Listy)
            is Empty -> elementAtB(x - 1, xs.tail() as Empty)
        }
    }
}

fun main() {
    println(
        elementAtB(2, listListableOf("a", "b", "c", "d"))
    )
// returns "b"
    println(
        elementAtB(4, listListableOf(1, 2, 4, 3))
    )
// returns 3
    println(
        elementAtB(
            1,
            listListableOf(
                listListableOf(1, 2, 3),
                listListableOf(3, 4, 5),
                listListableOf(3, 1, 4, 1, 5),
                listListableOf(5, 1)
            )
        )
    )
// returns [1,2,3]
}