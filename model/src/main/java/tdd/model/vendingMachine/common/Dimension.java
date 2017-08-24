package tdd.model.vendingMachine.common;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Dimension (3D) for objects in the project.
 * All objects' dimensions are assumed to be cuboids with 3 attributes:
 * <ul><li>length</li><li>width (depth)</li><li>height</li></ul>
 */
@Getter
@Setter
@RequiredArgsConstructor
public class Dimension {

    /**
     * Length.
     */
    @NonNull
    private BigDecimal length;
    /**
     * Width.
     */
    @NonNull
    private BigDecimal width;
    /**
     * Height.
     */
    @NonNull
    private BigDecimal height;
}
