package tdd.vendingMachine.common.builder;

/**
 * Builder interface.
 *
 * @param <T> type of the built object.
 */
public interface Builder<T> {
    /**
     * Builds an object.
     *
     * @return built object.
     */
    T build();
}
