package br.dev.pedrolamarao.structure2;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Algorithms on linear structures.
 */
class Linear
{
    /**
     * Accumulate elements.
     * @param first
     * @param initial
     * @param accumulator
     * @return
     * @param <T>
     * @param <U>
     */
    static <T,U> U accumulate (UniLinearCursor<T> first, U initial, BiFunction<U,T,U> accumulator)
    {
        var tmp = initial;
        for (var i = first; i != null; i = i.next()) {
            tmp = accumulator.apply(tmp,i.value());
        }
        return tmp;
    }

    /**
     * True if any element matches a predicate.
     * @param first
     * @param predicate
     * @return
     * @param <T>
     */
    static <T> boolean any (UniLinearCursor<T> first, Predicate<T> predicate)
    {
        for (var i = first; i != null; i = i.next()) {
            if (predicate.test(i.value()))
                return true;
        }
        return false;
    }

    /**
     * Count occurrences of an element.
     * @param first
     * @param value
     * @return
     * @param <T>
     */
    static <T> long count (UniLinearCursor<T> first, T value)
    {
        long counter = 0;
        for (var i = first; i != null; i = i.next()) {
            if (Objects.equals(value,i.value()))
                ++counter;
        }
        return counter;
    }

    /**
     * Count occurrences of elements that match a predicate.
     * @param first
     * @param predicate
     * @return
     * @param <T>
     */
    static <T> long countIf (UniLinearCursor<T> first, Predicate<T> predicate)
    {
        long counter = 0;
        for (var i = first; i != null; i = i.next()) {
            if (predicate.test(i.value()))
                ++counter;
        }
        return counter;
    }

    /**
     * Count the distance between two cursors.
     * @param first
     * @param last
     * @return
     * @param <T>
     */
    static <T> long distance (UniLinearCursor<T> first, UniLinearCursor<T> last)
    {
        long counter = 0;
        for (var i = first; i != last; i = i.next()) {
            ++counter;
        }
        return counter;
    }

    /**
     * True if every element matches a predicate.
     * @param first
     * @param predicate
     * @return
     * @param <T>
     */
    static <T> boolean every (UniLinearCursor<T> first, Predicate<T> predicate)
    {
        for (var i = first; i != null; i = i.next()) {
            if (! predicate.test(i.value()))
                return false;
        }
        return true;
    }

    /**
     * Find an element.
     * @param first
     * @param value
     * @return
     * @param <T>
     */
    static <T> UniLinearCursor<T> find (UniLinearCursor<T> first, T value)
    {
        for (var i = first; i != null; i = i.next()) {
            if (Objects.equals(i.value(),value))
                return i;
        }
        return null;
    }

    /**
     * Find an element that matches a predicate.
     * @param first
     * @param predicate
     * @return
     * @param <T>
     */
    static <T> UniLinearCursor<T> findIf (UniLinearCursor<T> first, Predicate<T> predicate)
    {
        for (var i = first; i != null; i = i.next()) {
            if (predicate.test(i.value()))
                return i;
        }
        return null;
    }

    static <T> T maximum (UniLinearCursor<T> first, Comparator<T> comparator)
    {
        if (first == null) return null;
        T tmp = first.value();
        for (var i = first.next(); i != null; i = i.next()) {
            if (comparator.compare(tmp,i.value()) < 0)
                tmp = i.value();
        }
        return tmp;
    }

    static <T extends Comparable<T>> T maximum (UniLinearCursor<T> first)
    {
        return maximum(first,Comparator.naturalOrder());
    }

    static <T> T minimum (UniLinearCursor<T> first, Comparator<T> comparator)
    {
        if (first == null) return null;
        T tmp = first.value();
        for (var i = first.next(); i != null; i = i.next()) {
            if (comparator.compare(i.value(),tmp) < 0)
                tmp = i.value();
        }
        return tmp;
    }

    static <T extends Comparable<T>> T minimum (UniLinearCursor<T> first)
    {
        return minimum(first,Comparator.naturalOrder());
    }

    /**
     * True if the structure is sorted by a comparator.
     * @param first
     * @param comparator
     * @return
     * @param <T>
     */
    static <T> boolean sorted (UniLinearCursor<T> first, Comparator<T> comparator)
    {
        if (first == null) return true;
        for (UniLinearCursor<T> i = first, j = i.next(); j != null; i = i.next(), j = j.next()) {
            if (comparator.compare(i.value(),j.value()) > 0)
                return false;
        }
        return true;
    }

    static <T extends Comparable<T>> boolean sorted (UniLinearCursor<T> first)
    {
        return sorted(first,Comparator.naturalOrder());
    }

    /**
     * Visit every element.
     * @param first
     * @param visitor
     * @param <T>
     */
    static <T> void visit (UniLinearCursor<T> first, Consumer<T> visitor)
    {
        for (var i = first; i != null; i = i.next()) {
            visitor.accept(i.value());
        }
    }
}
