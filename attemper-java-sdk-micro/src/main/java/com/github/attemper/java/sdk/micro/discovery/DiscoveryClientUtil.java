package com.github.attemper.java.sdk.micro.discovery;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

public class DiscoveryClientUtil {

    public static String getAddress(DiscoveryClient discoveryClient, String serviceName, List<String> serviceUrls) {
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName.toUpperCase());
            if (instances.isEmpty()) {
                instances = discoveryClient.getInstances(serviceName);
                if (instances.isEmpty()) {
                    throw new RuntimeException("instances is empty:" + serviceName);
                }
            }
            int randomIndex = (int) (Math.random() * instances.size());
            ServiceInstance serviceInstance = instances.get(randomIndex);
            String realUrl = serviceInstance.getUri().toString();
            if(!serviceUrls.contains(realUrl)) {
                serviceUrls.add(realUrl);
            }
            return realUrl;
        } catch (Exception e) {
            throw new RuntimeException("get instances occurred exception:" + serviceName + "\n" + e.getMessage());
        }
    }
}
