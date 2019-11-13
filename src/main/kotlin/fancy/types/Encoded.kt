package fancy.types

class Encoded<X> private constructor(val values: List<X>) {

    constructor (value: X) : this(listOf(value))

    val count: Int get() = values.size

    override fun toString() =
        when (count) {
            1 -> values.first().toString()
            else -> values.toString()
        }

    operator fun plus(x: X) = Encoded(values + x)
}

