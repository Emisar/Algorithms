package exceptions;

public class EpsilonIsNotSetException extends Exception {
    private static final long serialVersionUID = 1L;

    public EpsilonIsNotSetException(String message) {
        super(message);
    }
    
    public EpsilonIsNotSetException() {
        super("Epsilon is not set!");
    }
}
