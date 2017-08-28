package tdd.vendingMachine.common.factory;


/**
 * Factory interface. Creates instances of {@code K} type based on {@code T} type instance.
 *
 * @param <T> {@link #create(Object)} method parameter.
 * @param <K> type of created instances by this factory.
 */
public interface Factory<T, K> {
    /**
     * Creates {@link K} object according to the given parameter.
     *
     * @param t method parameter.
     * @return {@link K} object according to the given parameter.
     */
    K create(T t);
}
