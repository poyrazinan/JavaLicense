package tr.com.poyrazinan.license.exceptions;

/**
 * @since 1.2
 * @author poyrazinan
 */
public class ConnectionFailureException extends Exception {

    /**
     * ConnectionFailureException occur when connection fails
     */
    public ConnectionFailureException() {
        super("An error occurred when try to connect license server.");
    }
}
