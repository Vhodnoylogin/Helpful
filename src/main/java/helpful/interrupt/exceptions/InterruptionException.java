package helpful.interrupt.exceptions;

public class InterruptionException extends Exception {
    public InterruptionException() {
    }

    public InterruptionException(String message) {
        super(message);
    }

    public InterruptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterruptionException(Throwable cause) {
        super(cause);
    }

    public InterruptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}