package com.skillbox;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface Function1<T, R> {
    R apply(T t);
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

}
