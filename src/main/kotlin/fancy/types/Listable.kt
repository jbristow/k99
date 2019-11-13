package fancy.types

import fancy.types.Listable.Listy
import fancy.types.Listable.Single

sealed class Listable<X> {
    abstract fun isEmpty(): Boolean
    abstract fun head(): Listable<X>
    abstract fun tail(): Listable<X>
    abstract fun plusElement(element: Empty<X>): Listable<X>
    abstract fun plusElement(element: Single<X>): Listable<X>
    abstract fun plusElement(element: Listy<X>): Listable<X>
    operator fun plus(element: Listable<X>): Listable<X> {
        return when (element) {
            is Empty -> this.plusElement(element)
            is Single -> this.plusElement(element)
            is Listy -> this.plusElement(element)
        }
    }

    class Empty<X> : Listable<X>() {

        override fun plusElement(element: Empty<X>) = Empty<X>()
        override fun plusElement(element: Single<X>) = element
        override fun plusElement(element: Listy<X>) = element

        override fun tail(): Empty<X> = this
        override fun head(): Empty<X> = Empty()
        override fun isEmpty() = true

        override fun toString() = "[]"

        override fun equals(other: Any?): Boolean {
            return when (other) {
                !is Listable<*> -> false
                is Empty<*> -> true
                else -> false
            }
        }

        override fun hashCode() = javaClass.hashCode()
    }

    class Single<X>(val item: X) : Listable<X>() {

        override fun plusElement(element: Empty<X>) = this
        override fun plusElement(element: Single<X>) = Listy(this, element)
        override fun plusElement(element: Listy<X>) = Listy(this, *element.item)

        override fun tail() = Empty<X>()
        override fun head(): Listable<X> = this
        override fun isEmpty() = false
        override fun toString() = "$item"
        override fun equals(other: Any?): Boolean {
            return when (other) {
                !is Listable<*> -> false
                is Single<*> -> item == other.item
                else -> false
            }
        }

        override fun hashCode() = item.hashCode()
    }

    class Listy<X>(vararg val item: Listable<X>) : Listable<X>() {

        override fun tail() = when (item.size) {
            0, 1 -> Empty()
            2 -> when (item.last()) {
                is Empty -> item.last()
                is Single -> item.last()
                is Listy -> item.last()
            }
            else -> Listy(*item.drop(1).toTypedArray())
        }

        override fun head() = item.first()
        override fun isEmpty() = false
        override fun toString() = "[${item.joinToString(", ") { it.toString() }}]"
        override fun plusElement(element: Empty<X>) = this

        override fun plusElement(element: Single<X>) = Listy(*item, element)

        override fun plusElement(element: Listy<X>) = Listy(*item, *element.item)

        override fun equals(other: Any?): Boolean {
            return when (other) {
                !is Listable<*> -> false
                is Listy<*> -> item.zip(other.item).all { (a, b) -> a == b }
                else -> false
            }
        }

        override fun hashCode() = item.contentHashCode()
    }
}

fun <T> listListableOf(vararg args: T) = Listy(*args.map { Single(it) }.toTypedArray())
