package com.github.attemper.java.sdk.micro.discovery;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

public class DiscoveryClientUtil {

    public static String getAddress(DiscoveryClient discoveryClient, String defAddress, String serviceName, List<String> serviceUrls) {
        if(discoveryClient == null){
            return defAddress;
        }
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName.toUpperCase());
            if (instances.isEmpty()) {
                instances = discoveryClient.getInstances(serviceName);
                if (instances.isEmpty()) {
                    return defAddress;
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
            e.printStackTrace();
            return defAddress;
        }
    }
}
