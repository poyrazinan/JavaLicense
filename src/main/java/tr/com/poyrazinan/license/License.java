package tr.com.poyrazinan.license;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import tr.com.poyrazinan.license.exceptions.BuilderException;
import tr.com.poyrazinan.license.exceptions.ConnectionFailureException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

public class License {

    private String licensedProduct, ip, link;

    private License(@NotNull LicenseBuilder builder) {
        this.licensedProduct = builder.licensedProduct;
        this.ip = builder.ip;
        this.link = "curl --insecure -X GET " + builder.link
                .replace("{ip}", builder.ip)
                .replace("{product}", builder.licensedProduct);
    }

    /**
     * Brings licensed product name
     *
     * @return
     */
    public String getProductName() {
        return this.licensedProduct;
    }

    /**
     * Brings ip
     *
     * @return
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Brings builded full link
     *
     * @return
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Returns status of license (boolean)
     *
     * @return
     */
    public boolean run() {
        boolean status = false;
        try {
            Process process = Runtime.getRuntime().exec(this.link);
            InputStream inputStream = process.getInputStream();
            BufferedReader bR = new BufferedReader(  new InputStreamReader(inputStream));
            String line = "";

            StringBuilder responseStrBuilder = new StringBuilder();
            while((line = bR.readLine()) != null)
                responseStrBuilder.append(line);

            inputStream.close();
            process.destroy();

            status = (boolean) new JSONObject(responseStrBuilder.toString()).get("status");
        }
        catch (Exception e) {
            throw new ConnectionFailureException("An error occurred when try to connect license server.");
        }
        finally {
            return status;
        }
    }

    /**
     * Builder Class
     */
    public static class LicenseBuilder {

        private String licensedProduct, ip, link;

        public LicenseBuilder() {
            ip = setIp();
        }

        /**
         * Set product name for check
         *
         * @param name
         * @return
         */
        public LicenseBuilder setProductName(String name) {
            this.licensedProduct = name;
            return this;
        }

        /**
         * Add your domain license checker link (WebAPI)
         *
         * Placeholders:
         * {ip} replacing to ip.
         * {product} replacing to product name
         *
         * @param link
         * @return
         */
        public LicenseBuilder setLink(String link) {
            this.link = link;
            return this;
        }

        /**
         * Builds the License object.
         * Then use License#run() for check status
         *
         * @return
         * @throws BuilderException
         */
        public License build() throws BuilderException {
            License license = new License(this);
            validateLicense(license);
            return license;
        }

        private void validateLicense(@NotNull License license) throws BuilderException {
            if (license.licensedProduct == null || license.ip == null)
                throw new BuilderException("License creation failure. Make sure to set link and product name.");
        }

        /**
         * Catches machine local ip
         *
         * @return ipv4 host adress
         */
        private @Nullable String setIp() {
            try {
                Enumeration en = NetworkInterface.getNetworkInterfaces();
                while (en.hasMoreElements()) {
                    NetworkInterface i = (NetworkInterface) en.nextElement();
                    for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements();) {
                        InetAddress addr = (InetAddress) en2.nextElement();
                        if (!addr.isLoopbackAddress())
                            if (addr instanceof Inet4Address)
                                return addr.getHostAddress();
                            else if (addr instanceof Inet6Address)
                                continue;
                    }
                }
            }

            catch (SocketException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
