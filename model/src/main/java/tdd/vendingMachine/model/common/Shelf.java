package tdd.vendingMachine.model.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * General shelf representation.
 * Shelf's attributes are:
 * <ul><li>shelf's {@link #size}</li></ul>
 */
@RequiredArgsConstructor
public class Shelf implements Serializable {
    @Getter
    protected final int size;
}
