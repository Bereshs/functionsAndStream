package com.skillbox;

import java.util.Objects;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);

    static final boolean ALWAYS_TRUE = true;
    static final boolean ALWAYS_FALSE = false;

    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t)->test(t) || other.test(t);
    }

    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t)->test(t) && other.test(t);
    }

    default Predicate<T> not() {
        return (t)->!test(t);
    }

}
