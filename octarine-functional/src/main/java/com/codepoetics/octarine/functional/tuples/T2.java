package com.codepoetics.octarine.functional.tuples;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class T2<A, B> {

    private final A a;
    private final B b;

    private T2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <S, A, B> Function<S, T2<A, B>> unpacker(Function<? super S, ? extends A> first,
                                                           Function<? super S, ? extends B> second) {
        return s -> T2.of(first.apply(s), second.apply(s));
    }

    public static <A, B> TupleLens<T2<A, B>, A> first() {
        return TupleLens.of(
                0,
                T2::getFirst,
                T2::withFirst
        );
    }

    public static <A, B> TupleLens<T2<A, B>, B> second() {
        return TupleLens.of(
                1,
                T2::getSecond,
                T2::withSecond
        );
    }

    public static <A, B> T2<A, B> of(A a, B b) {
        return new T2<>(a, b);
    }

    public A getFirst() {
        return a;
    }

    public <A2> T2<A2, B> withFirst(A2 a2) {
        return T2.of(a2, b);
    }

    public B getSecond() {
        return b;
    }

    public <B2> T2<A, B2> withSecond(B2 b2) {
        return T2.of(a, b2);
    }

    public <R> R pack(BiFunction<? super A, ? super B, ? extends R> f) {
        return f.apply(a, b);
    }

    public <C> T3<A, B, C> push(C c) {
        return Tuple.of(a, b, c);
    }

    public T2<B, T1<A>> pop() {
        return Tuple.of(b, Tuple.of(a));
    }

    public T2<A, T1<B>> shift() {
        return Tuple.of(a, Tuple.of(b));
    }

    public T2<B, A> swap() {
        return T2.of(b, a);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof T2)) {
            return false;
        }
        T2 other = (T2) o;
        return Objects.equals(a, other.a) &&
                Objects.equals(b, other.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", a, b);
    }
}
