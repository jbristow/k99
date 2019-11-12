package fancy

import arrow.core.Option
import fancy.types.Listable
import fancy.types.Listable.Empty
import fancy.types.Listable.Listy
import fancy.types.Listable.Single
import fancy.types.listListableOf

/*
 * P01 (*) Find the last element of a list.
 *   Example:
 *     ?- myLast(["a","b","c","d"]).
 *     "d"
 */

tailrec fun <T> myLast(xs: Listable<T>): Option<T> {
    return when (xs) {
        is Empty<T> -> Option.empty()
        is Single<T> -> Option.just(xs.item)
        is Listy<T> -> myLast(xs.tail())
    }
}

fun main() {
    println(
        myLast(Listy(Single("a"), Single("b"), Single("c"), Single("d")))
    )
// returns "d"
    println(
        myLast(listListableOf(1, 2, 4, 3))
    )
// returns 3
    println(
        myLast(
            listListableOf(
                listListableOf(1, 2, 3),
                listListableOf(3, 4, 5),
                listListableOf(3, 1, 4, 1, 5),
                listListableOf(5, 1)
            )
        )
    )

    println(myLast(Empty<Int>()))

// returns the list [5, 1]
}
