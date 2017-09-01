package tdd.vendingMachine.common.comparator;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link NaturalOrderComparator} tests.
 */
public class NaturalOrderComparatorTest {

    /**
     * {@link NaturalOrderComparator#compare(Object, Object)} test.
     */
    @Test
    public void testCompare() {
        String[] strings = new String[]{"ABC", "ABC23", "ABC10", "ABC02", "ABC1"};
        Arrays.sort(strings, NaturalOrderComparator.getInstance());
        assertThat(strings).containsExactly("ABC", "ABC1", "ABC02", "ABC10", "ABC23");
    }
}
