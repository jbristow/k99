import fancy.types.Listable
import fancy.types.listListableOf

/*
 * K05 (*) Reverse a list.
 */

private tailrec fun <T> reverseInner(reversed: Listable<T>, xs: Listable<T>): Listable<T> =
    when (xs) {
        is Listable.Empty<T> -> reversed
        is Listable.Single, is Listable.Listy -> reverseInner(xs.head() + reversed, xs.tail())
    }

fun <T> reverse(xs: Listable<T>) = reverseInner(Listable.Empty(), xs)

fun main() {
    println(
        reverse(listListableOf("a", "b", "c", "d"))
    )
// returns Listable["d","c","b","a"]
    println(
        reverse(listListableOf(1, 2, 4, 3))
    )
// returns Listable[3,4,2,1]
    println(
        reverse(
            listListableOf(
                listListableOf(1, 2, 3),
                listListableOf(3, 4, 5),
                listListableOf(3, 1, 4, 1, 5),
                listListableOf(5, 1)
            )
        )
    )
// returns Listable[[5, 1], [3, 1, 4, 1, 5], [3, 4, 5], [1, 2, 3]]
}
