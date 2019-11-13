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

tailrec fun <T> myLastButOne(xs: Listable<T>): Option<Listable<T>> =
    when (xs) {
        is Empty -> Option.empty()
        is Single -> Option.just(xs.head())
        is Listy -> myLastButOne(xs.tail())
    }

fun main() {
    println(
        myLastButOne(listListableOf("a", "b", "c", "d"))
    )
// returns "d"
    println(
        myLastButOne(listListableOf(1, 2, 4, 3))
    )
// returns 3
    println(
        myLastButOne(
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