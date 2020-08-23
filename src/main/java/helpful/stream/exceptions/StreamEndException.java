package helpful.stream.exceptions;

public class StreamEndException extends StreamException {
    public StreamEndException() {
    }

    public StreamEndException(String message) {
        super(message);
    }

    public StreamEndException(String message, Throwable cause) {
        super(message, cause);
    }

    public StreamEndException(Throwable cause) {
        super(cause);
    }

    public StreamEndException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}