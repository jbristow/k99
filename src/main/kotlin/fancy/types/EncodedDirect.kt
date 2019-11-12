package fancy.types

import arrow.core.Option
import arrow.core.some

sealed class EncodedDirect<X> {

    abstract val count: Int
    abstract val value: X
    abstract fun increment(): EncodedDirect<X>
    fun displayValue() = "${this::class.simpleName}[$count,$value]"
    abstract fun decrement(): Option<EncodedDirect<X>>

    class Singleton<X>(override val value: X) : EncodedDirect<X>() {
        override fun increment() = Multiple(count + 1, value)
        override fun decrement() = Option.empty<EncodedDirect<X>>()
        override val count: Int = 1
        override fun toString() = value.toString()
    }

    class Multiple<X>(override val count: Int, override val value: X) : EncodedDirect<X>() {
        override fun decrement() =
            when (count) {
                2 -> Singleton(value)
                else -> Multiple(count - 1, value)
            }.some()

        override fun increment() = Multiple(count + 1, value)
        override fun toString() = "[$count,$value]"
    }
}

fun encodedDirect(i: Int, s: String) = EncodedDirect.Multiple(i, s)
fun encodedDirect(s: String) = EncodedDirect.Singleton(s)
