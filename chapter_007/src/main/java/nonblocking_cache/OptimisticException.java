package nonblocking_cache;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }

    public OptimisticException() {
    }
}
