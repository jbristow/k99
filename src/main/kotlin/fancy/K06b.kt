package fancy

import fancy.types.Listable
import fancy.types.listListableOf
import reverse

/*
 * P06 (*) Find out whether a list is a palindrome.
     A palindrome can be read forward or backward; e.g. [x,a,m,a,x].
 */

private tailrec fun <T> isPalindromeInner(a: Listable<T>, b: Listable<T>): Boolean = when {
    a is Listable.Empty && b is Listable.Empty -> true
    a.head() != b.head() -> false
    else -> isPalindromeInner(a.tail(), b.tail())
}

fun <T> isPalindrome(xs: Listable<T>) = isPalindromeInner(reverse(xs), xs)

fun main() {
    println(isPalindrome(listListableOf("X", "A", "X", "A", "X")))
    println(isPalindrome(listListableOf(1, 2, 3, 4, 5)))
}