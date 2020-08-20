package helpfull.stream.exceptions;

public class StreamInMiddleException extends StreamException {
    public StreamInMiddleException() {
    }

    public StreamInMiddleException(String message) {
        super(message);
    }

    public StreamInMiddleException(String message, Throwable cause) {
        super(message, cause);
    }

    public StreamInMiddleException(Throwable cause) {
        super(cause);
    }

    public StreamInMiddleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}