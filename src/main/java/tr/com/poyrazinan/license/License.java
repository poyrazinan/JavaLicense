package tr.com.poyrazinan.license;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import tr.com.poyrazinan.license.exceptions.ConnectionFailureException;
import tr.com.poyrazinan.license.exceptions.ResponseCodeException;

import java.io.IOException;

/**
 * Main license class
 *
 * @since 1.0
 * @author poyrazinan
 */
@Getter
@Setter
@AllArgsConstructor
public class License {

    /**
     * Inputs for license check
     */
    private String productName, apiLink;

    /**
     * Send request to remote server and gets data with Connect#requestApi(link)
     *
     * @return boolean of license status
     * @throws ConnectionFailureException when connection fails this exception throws
     * @throws ResponseCodeException when response code is not 500 this exception throws
     */
    public LicenseResponse run() throws ConnectionFailureException, ResponseCodeException, IOException {
        boolean status = false;
        JSONObject license = new Connect().requestApi(getApiLink()
            .replace("{product}", getProductName()));
        status = license.getBoolean("status");
        String error =  (license.has("error")) ? license.getString("error") : null;
        String ip = (license.has("ip")) ? license.getString("ip") : null;
        return new LicenseResponse(status, error, ip);
    }

    /**
     * LicenseResponse is the response of request.
     *
     * @author poyraz.inan
     * @since 27.08.2024
     */
    @Getter
    @AllArgsConstructor
    public static class LicenseResponse {
        private boolean hasLicense;
        private String error;
        private String ip;
    }
}
