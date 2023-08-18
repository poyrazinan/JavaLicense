package tr.com.poyrazinan.license.exceptions;

/**
 * @since 1.2
 * @author poyrazinan
 */
public class LocalMachineIpCatchException extends Exception {

    /**
     * LocalMachineIpCatchException occur when code can't catch local machine host address
     */
    public LocalMachineIpCatchException() {
        super("Couldn't find ip adress of local machine!");
    }
}
