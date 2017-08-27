package tdd.vendingMachine.businessLogic.cash.exception;

public class ChangeImpossibleException extends Exception {
    private static final String DEFAULT_MESSAGE = "Not enough coins to give change!";

    public ChangeImpossibleException() {
        super(DEFAULT_MESSAGE);
    }
}
