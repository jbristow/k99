package fancy

import arrow.Kind
import arrow.core.ListK
import arrow.core.Option
import arrow.core.SequenceK
import arrow.core.extensions.listk.foldable.foldable
import arrow.core.extensions.option.foldable.foldable
import arrow.core.extensions.sequencek.foldable.foldable
import arrow.core.k
import arrow.core.some
import arrow.typeclasses.Foldable

fun <F, T> getLast(listKind: Kind<F, T>, FO: Foldable<F>): Option<T> =
    FO.run {
        listKind.foldLeft(Option.empty()) { _, it -> it.some() }
    }

fun main() {
    println(getLast(listOf(1, 2, 3, 4, 5, 6).k(), ListK.foldable()))
    println(getLast("Hello!".asSequence().k(), SequenceK.foldable()))
    println(getLast("Hello".some(), Option.foldable()))
}

