import fancy.types.Listable

fun <T> flatten(list: Listable<T>): Listable<T> {
    return when (list) {
        is Listable.Empty<T> -> list
        is Listable.Single<T> -> list
        is Listable.Listy<T> -> flattenInner(
            remaining = list,
            curr = Listable.Empty<T>(),
            output = Listable.Empty<T>()
        )
    }
}

fun <T> flattenInner(
    remaining: Listable<T>,
    curr: Listable<T>,
    output: Listable<T>
): Listable<T> =
    when (curr) {
        is Listable.Empty<T> -> {
            when (remaining) {
                is Listable.Empty<T> -> output
                is Listable.Single<T>, is Listable.Listy<T> ->
                    flattenInner(remaining = remaining.tail(), curr = remaining.head(), output = output)
            }
        }
        is Listable.Single<T>, is Listable.Listy<T> ->
            flattenInner(remaining = remaining, curr = curr.tail(), output = (output + curr.head()))
    }

fun main() {
    println(
        flatten(
            Listable.Listy(
                Listable.Single("A"),
                Listable.Listy(
                    Listable.Single("B"),
                    Listable.Single("C"),
                    Listable.Single("X"),
                    Listable.Listy(
                        Listable.Single("Y"),
                        Listable.Single("Z")
                    )
                ),
                Listable.Single("Crazy")
            )
        )
    )

    println(flatten(Listable.Listy(Listable.Single("X"), Listable.Single("Y"))).toString())
    println(
        flatten(
            Listable.Listy(
                Listable.Single(3),
                Listable.Listy(
                    Listable.Single(1),
                    Listable.Single(4),
                    Listable.Single(1),
                    Listable.Listy(
                        Listable.Single(5),
                        Listable.Single(9)
                    )
                ),
                Listable.Single(-2),
                Listable.Empty()
            )
        )
    )
}
