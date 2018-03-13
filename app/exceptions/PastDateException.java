package exceptions;

public class PastDateException extends Exception {

    public PastDateException() {
        super("Date cannot be in the past");
    }
}
