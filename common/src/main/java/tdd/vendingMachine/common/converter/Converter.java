package tdd.vendingMachine.common.converter;

import java.util.function.Function;

/**
 * Converter interface.
 *
 * @param <T> type of converted object.
 * @param <K> type of converted object.
 */
public interface Converter<T, K> {
    /**
     * Converts from {@code <T>} type object to {@code <K>}.
     *
     * @param t object to be converted.
     * @return conversion result.
     */
    K from(T t);

    /**
     * Converts from {@code <K>} type object to {@code <T>}.
     *
     * @param k object to be converted.
     * @return conversion result.
     */
    T to(K k);

    /**
     * Creates new instance of converter. Takes conversion functions as parameters.
     *
     * @param from function converting from {@code <T>} type to {@code <K>} type.
     * @param to   function converting from {@code <K>} type to {@code <T>} type.
     * @param <T>  type of converted object.
     * @param <K>  type of converted object.
     * @return new converter instance.
     */
    static <T, K> Converter<T, K> of(
        Function<? super T, ? extends K> from,
        Function<? super K, ? extends T> to
    ) {
        return new Converter<T, K>() {
            @Override
            public K from(T t) {
                return from.apply(t);
            }

            @Override
            public T to(K k) {
                return to.apply(k);
            }
        };
    }

    /**
     * Creates new instance of converter. Takes conversion functions as parameters.
     * Allows {@code null} conversion parameters.
     *
     * @param from function converting from {@code <T>} type to {@code <K>} type.
     * @param to   function converting from {@code <K>} type to {@code <T>} type.
     * @param <T>  type of converted object.
     * @param <K>  type of converted object.
     * @return new converter instance.
     */
    static <T, K> Converter<T, K> ofNullable(
        Function<? super T, ? extends K> from,
        Function<? super K, ? extends T> to
    ) {
        return of(
            (t) -> t != null ? from.apply(t) : null,
            (k) -> k != null ? to.apply(k) : null
        );
    }
}
