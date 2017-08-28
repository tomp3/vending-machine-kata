package tdd.vendingMachine.common.factory;


/**
 * Factory interface. Creates instances of {@code K} type based on {@coe T} type instance.
 *
 * @param <T> {@link #create(Object)} method parameter.
 * @param <K> type of created instances by this factory.
 */
public interface Factory<T, K> {
    K create(T t);
}
