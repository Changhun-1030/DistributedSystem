package recruitment;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {
    private final Map<String, Object> services = new HashMap<>();

    public void registerService(String serviceName, Object serviceInstance) {
        services.put(serviceName, serviceInstance);
        System.out.println("Service registered: " + serviceName);
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
