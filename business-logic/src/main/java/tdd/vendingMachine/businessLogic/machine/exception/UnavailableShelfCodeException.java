package tdd.vendingMachine.businessLogic.machine.exception;

public class UnavailableShelfCodeException extends Exception {
    public UnavailableShelfCodeException(String message) {
        super(message);
    }
}
