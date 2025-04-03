package recruitment;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

public class ClientServiceDiscovery {
    public static void discoverServices() {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo[] infos = jmdns.list("_grpc._tcp.local.");

            for (ServiceInfo info : infos) {
                System.out.println("[DISCOVERY] Found service: " + info.getName() +
                        " at " + info.getInetAddresses()[0].getHostAddress() + ":" + info.getPort());
            }

            jmdns.close();
        } catch (IOException e) {
            System.err.println("[DISCOVERY] jmDNS service discovery failed: " + e.getMessage());
        }
    }
}
