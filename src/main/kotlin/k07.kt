import Listables.*

interface Listable<X> {
    fun isEmpty(): Boolean
    fun head(): Listable<X>
    fun tail(): Listable<X>
}

sealed class Listables {
    class EmptyListable<X> : Listable<X> {
        override fun tail(): Listable<X> = this
        override fun head(): EmptyListable<X> = EmptyListable()
        override fun isEmpty() = true

        override fun toString() = "listable:empty"
    }

    class SingleListable<X>(val item: X) : Listable<X> {
        override fun tail() = EmptyListable<X>()
        override fun head(): Listable<X> = this
        override fun isEmpty() = false
        fun unwrap() = item
        override fun toString() = "listable:${unwrap()}"
    }

    class ListListable<X>(vararg val item: Listable<X>) : Listable<X> {

        override fun tail() = when (item.size) {
            0, 1 -> EmptyListable()
            2 -> item.last()
            else -> ListListable(*item.drop(1).toTypedArray())
        }

        override fun head() = item.first()
        override fun isEmpty() = false
        override fun toString() = "listable:[${item.joinToString(", ") { it.toString() }}]"
    }
}


fun <T> flatten(list: Listable<T>): List<SingleListable<T>> {
    return flattenInner(emptyList(), EmptyListable(), list)
}

tailrec fun <T> flattenInner(output: List<SingleListable<T>>, curr: Listable<T>, remaining: Listable<T>): List<SingleListable<T>> {
    println("output=${output}, curr=${curr} remaining=${remaining}")
    return when {
        remaining is EmptyListable
                && curr is EmptyListable -> output
        curr is EmptyListable -> flattenInner(output, remaining.head(), remaining.tail())
        curr.head() is EmptyListable -> flattenInner(output, curr.tail(), remaining)
        curr.head() is SingleListable -> flattenInner(output + (curr.head() as SingleListable), curr.tail(), remaining)
        curr.head() is ListListable -> flattenInner(output + flatten(curr.head()), curr.tail(), remaining)
        else -> throw Error("curr is ${curr.head()::class}")
    }

}

fun main() {
    println(flatten(
            ListListable(
                    SingleListable("A"),
                    ListListable(
                            SingleListable("B"),
                            SingleListable("C"),
                            SingleListable("X"),
                            ListListable(
                                    SingleListable("Y"),
                                    SingleListable("Z")
                            )
                    ),
                    SingleListable("Crazy")
            )
    ).joinToString { it.unwrap() })

    println(flatten(ListListable(SingleListable("X"), SingleListable("Y"))).joinToString { it.unwrap() })
    println(flatten(
            ListListable(
                    SingleListable(3),
                    ListListable(
                            SingleListable(1),
                            SingleListable(4),
                            SingleListable(1),
                            ListListable(
                                    SingleListable(5),
                                    SingleListable(9)
                            )
                    ),
                    SingleListable(-2),
                    EmptyListable()
            )
    ).joinToString { it.unwrap().toString() })
}
