package com.skillbox;

import java.util.Objects;

import java.util.function.Function;

@FunctionalInterface
public interface Function2<T, U, R> {
    R apply(T t, U u);

    default <V> Function2<T, U, V> compose(Function1<? super R, ? extends V> after) {
        return (T t, U u) -> after.apply(apply(t, u));
    }


}
