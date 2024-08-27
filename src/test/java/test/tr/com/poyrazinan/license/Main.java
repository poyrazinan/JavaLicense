package test.tr.com.poyrazinan.license;

import tr.com.poyrazinan.license.License;
import tr.com.poyrazinan.license.exceptions.ConnectionFailureException;
import tr.com.poyrazinan.license.exceptions.ResponseCodeException;

import java.io.IOException;

/**
 * @since 1.2
 * @author poyrazinan
 */
public class Main {

    /**
     * test's main method, program starts at this method
     *
     * @param args for scanner
     */
    public static void main(String[] args) {
        try {
            // License object creation
            License license = new License("dailyreward", "https://geik.xyz/app/api/license2.php?product={product}");
            // License check
            License.LicenseResponse status = license.run();
            // Checks if server has license or not
            if (status.isHasLicense()) {
                // Code block for license found situation
                System.out.println("License found!");
            }
            else
                // Code block for license couldn't be found situation
                System.out.println("License couldn't be found");

        // Exceptions
        } catch (ConnectionFailureException e) {
            // When connection fails, throws this exception
            System.out.println("Connection failed to remote server.");
            // When response not equal to 500, throws this exception
        } catch (ResponseCodeException e) {
            System.out.println("Response code is not correct, check api server!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
