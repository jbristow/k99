/*
 * P06 (*) Find out whether a list is a palindrome.
     A palindrome can be read forward or backward; e.g. [x,a,m,a,x].
 */

private tailrec fun <T> isPalindromeInner(a: List<T>, b: List<T>): Boolean =
    when {
        a.first() == b.first() -> true
        else -> isPalindromeInner(a.drop(1), b.drop(1))
    }

fun <T> isPalindrome(xs: List<T>) = isPalindromeInner(reverse(xs), xs)
