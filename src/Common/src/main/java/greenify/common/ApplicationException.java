package greenify.common;

public class ApplicationException extends RuntimeException {
    /**
     * This method sends an application exception message.
     * @param message the exception message
     */
    public ApplicationException(String message) {
        super(message);
    }
}
