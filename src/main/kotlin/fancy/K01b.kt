package fancy

import arrow.core.Option
import arrow.core.some
import fancy.types.Listable
import fancy.types.listListableOf

/*
 * P01 (*) Find the last element of a list.
 *   Example:
 *     ?- myLast(["a","b","c","d"]).
 *     "d"
 */

/*
 * This code doesn't work:
 *     fun <T> myLast(xs: Empty<T>): Option<Listable<T>> = Option.empty()
 *     fun <T> myLast(xs: Single<T>): Option<Listable<T>> = xs.some()
 *     tailrec fun <T> myLast(xs: Listy<T>): Option<Listable<T>> = myLast(xs.tail())
 *
 *  Why? Because of TYPE ERASURE.
 */
tailrec fun <T> myLast(xs: Listable<T>): Option<T> {
    return when (xs) {
        is Listable.Empty<T> -> Option.empty()
        is Listable.Single<T> -> xs.item.some()
        is Listable.Listy<T> -> myLast(xs.tail())
    }
}

fun main() {
    println(
        myLast(
            Listable.Listy(
                Listable.Single("a"),
                Listable.Single("b"),
                listListableOf("a", "b"),
                Listable.Single("c"),
                Listable.Single("d")
            )
        )
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
    // returns the list [5, 1]

    println(myLast(Listable.Empty<Int>()))


}
