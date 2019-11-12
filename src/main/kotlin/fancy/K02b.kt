package fancy

import arrow.core.Option
import fancy.types.Listable
import fancy.types.Listable.Empty
import fancy.types.Listable.Listy
import fancy.types.Listable.Single
import fancy.types.listListableOf

/*
 * P02 (*) Find the last but one element of a list.
 *   (zweitletztes Element, l'avant-dernier élément)
 */

fun <T> myLastButOneB(xs: Empty<T>) = Option.empty<Listable<T>>()
fun <T> myLastButOneB(xs: Single<T>) = Option.empty<Listable<T>>()
tailrec fun <T> myLastButOneB(xs: Listy<T>): Option<Listable<T>> =
    when (xs.tail()) {
        is Empty -> Option.empty()
        is Single -> Option.just(xs.head())
        is Listy -> myLastButOneB(xs.tail() as Listy)
    }

fun main() {
    println(
        myLastButOneB(listListableOf("a", "b", "c", "d"))
    )
// returns "d"
    println(
        myLastButOneB(listListableOf(1, 2, 4, 3))
    )
// returns 3
    println(
        myLastButOneB(
            listListableOf(
                listListableOf(1, 2, 3),
                listListableOf(3, 4, 5),
                listListableOf(3, 1, 4, 1, 5),
                listListableOf(5, 1)
            )
        )
    )
// returns [3, 1, 4, 1, 5]
}