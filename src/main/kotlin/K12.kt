import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.some
import fancy.types.EncodedDirect
import fancy.types.encodedDirect

/*
 *
 * K12 ($$) Decode a run-length encoded list.
 * Given a run-length code list generated as specified in problem P11. Construct its uncompressed version.
 */

fun <X> uncompressOversimple(list: List<EncodedDirect<X>>) =
    list.joinToString("") {
        it.value.toString().repeat(it.count)
    }

tailrec fun <X> uncompress(
    remaining: List<EncodedDirect<X>>,
    curr: Option<EncodedDirect<X>> = Option.empty<EncodedDirect<X>>(),
    output: String = ""
): String =
    when (curr) {
        is None -> when {
            remaining.isEmpty() -> output
            else -> uncompress(remaining.drop(1), curr = remaining.first().some(), output = output)
        }
        is Some -> uncompress(
            remaining,
            curr = curr.flatMap { it.decrement() },
            output = "$output${curr.orNull()?.value}"
        )
    }

fun main() {
    val encoded =
        listOf(
            encodedDirect(4, "a"),
            encodedDirect("b"),
            encodedDirect(2, "c"),
            encodedDirect(2, "a"),
            encodedDirect("d"),
            encodedDirect(4, "e")
        )
    println(uncompressOversimple(encoded))
    println(uncompress(encoded))
}


