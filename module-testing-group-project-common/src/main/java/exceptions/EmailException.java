package exceptions;

/**
 * @author Alexey Druzik on 5/19/2020
 */
public class EmailException extends Exception {
    public EmailException(String message) {

        super(message);
    }

    public EmailException(Throwable cause) {

        super(cause);
    }
}
