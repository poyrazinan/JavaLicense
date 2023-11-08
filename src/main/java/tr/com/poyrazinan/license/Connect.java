package tr.com.poyrazinan.license;

import org.json.JSONObject;
import tr.com.poyrazinan.license.exceptions.ConnectionFailureException;
import tr.com.poyrazinan.license.exceptions.ResponseCodeException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Connect class which request data from webserver
 *
 * @since 1.2
 * @author poyrazinan
 */
public class Connect {

    /**
     * Sends request to remote host and gets data as JSONObject
     *
     * @param apiUrl url of request
     * @return JSONObject of status
     */
    public JSONObject requestApi(String apiUrl) throws ConnectionFailureException, ResponseCodeException {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return new JSONObject(response.toString());
            }
            else {
                throw new ResponseCodeException("Response: " + connection.getResponseCode());
            }
        } catch (Exception e) {
            throw new ConnectionFailureException();
        }
    }
}
