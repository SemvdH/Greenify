package greenify.common;

public class ErrorResponse {
    private String message;

    /**
     * The method creates a new error response with a message.
     * @param message the message you want to response with
     */
    public ErrorResponse(String message) {
        this.message = message;
    }

    /**
     * This method creates an error response without a message.
     */
    public ErrorResponse() { }

    /**
     * This method returns the message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method sets a message.
     * @param message the message you want set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
