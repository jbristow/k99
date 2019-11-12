import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import fancy.types.EncodedDirect

/* ($) Modified run-length encoding.
 *
 * Modify the result of problem P10 in such a way that if an element has no
 * duplicates it is simply copied into the result list. Only elements with
 * duplicates are transferred as [N,E] terms.
 */

tailrec fun <X> encodeModified(
    remaining: List<X>,
    curr: Option<EncodedDirect<X>> = Option.empty(),
    output: List<EncodedDirect<X>> = emptyList()
): List<EncodedDirect<X>> = when (curr) {
    is None ->
        when {
            remaining.isEmpty() -> output
            else -> encodeModified(
                remaining.drop(1),
                curr = Option.just(EncodedDirect.Singleton(remaining.first())),
                output = output
            )
        }
    is Some<EncodedDirect<X>> ->
        when {
            (remaining.isNotEmpty() && curr.t.value == remaining.first()) ->
                encodeModified(
                    remaining.drop(1),
                    curr = curr.map { it.increment() },
                    output = output
                )
            else ->
                encodeModified(
                    remaining,
                    curr = Option.empty(),
                    output = curr.fold({ output }, { output + it })
                )
        }
}

fun main() {
    println(
        encodeModified(
            listOf("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e")
        )
    )
}