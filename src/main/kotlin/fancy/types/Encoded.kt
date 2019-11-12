package fancy.types

class Encoded<X>(value: X) {

    val values: List<X> = listOf(value)
    val count: Int get() = values.size
}

