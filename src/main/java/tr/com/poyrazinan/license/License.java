package tr.com.poyrazinan.license;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import tr.com.poyrazinan.license.exceptions.ConnectionFailureException;
import tr.com.poyrazinan.license.exceptions.LocalMachineIpCatchException;
import tr.com.poyrazinan.license.exceptions.ResponseCodeException;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Main license class
 *
 * @since 1.0
 * @author poyrazinan
 */
@Getter
@Setter
@Builder
public class License {

    /**
     * Inputs for license check
     */
    private String productName, licenseIp, apiLink;

    /**
     * License object which checks license with http request.
     * <p><b>You should run it with License#run() to license check.</b></p>
     *
     * @param productName name of license (it should be dataname of query)
     * @param apiLink api link to connect
     * @throws LocalMachineIpCatchException if something goes wrong when try to find ip, this exception occurs
     */
    public License(String productName, String apiLink) throws LocalMachineIpCatchException {
        this.productName = productName;
        this.licenseIp = catchLocalHostIp();
        this.apiLink = apiLink;
    }

    /**
     * License object which checks license with http request.
     * <p><b>You should run it with License#run() to license check.</b></p>
     *
     * @param productName name of license (it should be dataname of query)
     * @param licenseIp license ip to check
     * @param apiLink api link to connect
     */
    public License(String productName, String licenseIp, String apiLink) {
        this.productName = productName;
        this.licenseIp = licenseIp;
        this.apiLink = apiLink;
    }

    /**
     * Send request to remote server and gets data with Connect#requestApi(link)
     *
     * @return boolean of license status
     * @throws ConnectionFailureException when connection fails this exception throws
     * @throws ResponseCodeException when response code is not 500 this exception throws
     */
    public boolean run() throws ConnectionFailureException, ResponseCodeException, IOException {
        boolean status = false;
        JSONObject license = new Connect().requestApi(getApiLink()
            .replace("{ip}", getLicenseIp())
            .replace("{product}", getProductName()));
        status = license.getBoolean("status");
        return status;
    }

    /**
     * Catch local host ip address used for license check
     *
     * @return host ip address as string
     * @throws LocalMachineIpCatchException when something goes wrong
     */
    private String catchLocalHostIp() throws LocalMachineIpCatchException {
        try {
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = (NetworkInterface) interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }
                Enumeration addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = (InetAddress) addresses.nextElement();
                    if (addr.isLinkLocalAddress() || addr.isLoopbackAddress() || addr.isMulticastAddress()) {
                        continue;
                    }
                    return addr.getHostAddress();
                }
            }
        }
        catch (SocketException e) {
            throw new LocalMachineIpCatchException();
        }
        throw new LocalMachineIpCatchException();
    }
}
