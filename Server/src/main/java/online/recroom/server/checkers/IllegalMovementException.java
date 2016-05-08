package online.recroom.server.checkers;

/**
 * Created by theje on 5/8/2016.
 */
public class IllegalMovementException extends Exception {
    public IllegalMovementException() {
    }

    public IllegalMovementException(String message) {
        super(message);
    }
}
