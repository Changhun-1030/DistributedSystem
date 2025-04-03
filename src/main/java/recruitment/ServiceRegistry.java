package recruitment;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {
    private final Map<String, Object> services = new HashMap<>();
    private JmDNS jmdns;

    public ServiceRegistry() {
        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());
        } catch (IOException e) {
            System.err.println("Failed to initialize jmDNS: " + e.getMessage());
        }
    }

    public void registerService(String serviceName, Object serviceInstance, int port) {
        services.put(serviceName, serviceInstance);
        System.out.println("Service registered locally: " + serviceName);

        // Register on the local network using jmDNS
        if (jmdns != null) {
            try {
                ServiceInfo serviceInfo = ServiceInfo.create("_grpc._tcp.local.", serviceName, port, "service=" + serviceName);
                jmdns.registerService(serviceInfo);
                System.out.println("Service published via jmDNS: " + serviceName + " on port " + port);
            } catch (IOException e) {
                System.err.println("Failed to publish service via jmDNS: " + e.getMessage());
            }
        }
    }

    public Object getService(String serviceName) {
        return services.get(serviceName);
    }

    public Map<String, Object> getAllServices() {
        return services;
    }

    public Object lookupService(String serviceName) {
        return services.get(serviceName);
    }
}
