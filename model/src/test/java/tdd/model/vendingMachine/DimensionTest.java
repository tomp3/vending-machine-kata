package tdd.model.vendingMachine;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tdd.model.vendingMachine.common.Dimension;
import tdd.vendingMachine.common.number.NumberUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameters;

@AllArgsConstructor
@RunWith(Parameterized.class)
public class DimensionTest {

    private BigDecimal pLength;
    private BigDecimal pWidth;
    private BigDecimal pHeight;

    @Parameters
    public static Collection<Object[]> dimensionData() {
        return Arrays.asList(new Object[][]{
                {BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE},
                {new BigDecimal(2), new BigDecimal(4), new BigDecimal(8)},
                {BigDecimal.ONE, new BigDecimal(1.5), new BigDecimal(2)},
                {new BigDecimal(0.125), new BigDecimal(4), new BigDecimal(2)}
            }
        );
    }


    @Test
    public void verifyDimension() {
        Dimension dimension = new Dimension(pLength, pWidth, pHeight);
        verifyDimensionAttributeValues(dimension);
        verifyDimensionVolume(dimension);
    }

    private void verifyDimensionAttributeValues(Dimension dimension) {
        assertThat(dimension.getLength()).isEqualTo(pLength);
        assertThat(dimension.getWidth()).isEqualTo(pWidth);
        assertThat(dimension.getHeight()).isEqualTo(pHeight);
    }

    private void verifyDimensionVolume(Dimension dimension) {
        assertThat(NumberUtils.multiply(dimension.getHeight(), dimension.getLength(), dimension.getWidth()))
            .isEqualTo(NumberUtils.multiply(pLength, pWidth, pHeight));
    }


}
