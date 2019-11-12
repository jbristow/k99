package fancy

import fancy.types.Listable
import fancy.types.listListableOf

/*
 * P04 (*) Find the number of elements of a list.
 */

private tailrec fun <T> lengthInner(len: Int, xs: Listable<T>): Int =
    when (xs) {
        is Listable.Empty<T> -> len
        is Listable.Single<T>, is Listable.Listy<T> -> lengthInner(len + 1, xs.tail())
    }

fun <T> length(xs: Listable<T>) =
    lengthInner(0, xs)

fun main() {
    println(
        length(listListableOf("a", "b", "c", "d"))
    )
// returns 4
    println(
        length(listListableOf(1, 2, 4, 3))
    )
// returns 4
    println(
        length(
            listListableOf(
                listListableOf(1, 2, 3),
                listListableOf(3, 4, 5),
                listListableOf(3, 1, 4, 1, 5),
                listListableOf(5, 1)
            )
        )
    )
// returns 4
}

