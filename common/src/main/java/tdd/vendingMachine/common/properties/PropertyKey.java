package tdd.vendingMachine.common.properties;

import java.io.Serializable;

public interface PropertyKey extends Serializable {
    String getKey();
    String getDescription();
    Boolean isRequired();
}
