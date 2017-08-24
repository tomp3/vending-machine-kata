package tdd.model.vendingMachine;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import tdd.model.vendingMachine.common.Dimension;
import tdd.model.vendingMachine.product.Container;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
@RunWith(Parameterized.class)
public class ContainerTest {

    private String pName;
    private Dimension pDimension;

    @Parameters
    public static Collection<Object[]> dimensionData() {
        return Arrays.asList(new Object[][]{
                {"Chocolate bar", new Dimension(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE)},
                {"Small can", new Dimension(new BigDecimal(2), new BigDecimal(4), new BigDecimal(8))},
                {"Medium can", new Dimension(BigDecimal.ONE, new BigDecimal(1.5), new BigDecimal(2))},
                {"0.5L bottle", new Dimension(new BigDecimal(0.125), new BigDecimal(4), new BigDecimal(2))}
            }
        );
    }

    @Test
    public void verifyContainerHasRequiredAttributes() {
        Container container = new Container(pName, pDimension);
        verifyContainerHasName(container);
        verifyContainerHasDimension(container);
    }

    private void verifyContainerHasName(Container container) {
        assertThat(StringUtils.isEmpty(container.getName())).isFalse();
    }

    private void verifyContainerHasDimension(Container container) {
        assertThat(container.getDimension()).isNotNull();
    }
}
