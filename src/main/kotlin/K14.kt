/*
 * K14 ($) Duplicate the elements of a list.
 * Example:
 * ?- dupli(listOf(a,b,c,c,d))
 * [a,a,b,b,c,c,c,c,d,d]
 */

tailrec fun <X> dupli(x: List<X>, output: List<X> = emptyList()): List<X> {
    return when {
        x.isEmpty() -> output
        else -> dupli(x.drop(1), output + x.first() + x.first())
    }
}

fun main() {
    println(dupli("duplicative!".toList()))
    println(dupli(listOf("a", "b", "c", "c", "d")))
    println(dupli(listOf(4, 3, 2, 2, 1)))
}