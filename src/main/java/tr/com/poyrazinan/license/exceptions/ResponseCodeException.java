package tr.com.poyrazinan.license.exceptions;

/**
 * @since 1.2
 * @author poyrazinan
 */
public class ResponseCodeException extends Exception {

    /**
     * ResponseCodeException occur when response code is not equal to OK
     *
     * @param message of exception
     */
    public ResponseCodeException(String message) {
        super(message);
    }
}
