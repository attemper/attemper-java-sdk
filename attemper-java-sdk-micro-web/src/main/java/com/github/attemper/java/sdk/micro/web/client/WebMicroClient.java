package com.github.attemper.java.sdk.micro.web.client;

import com.github.attemper.java.sdk.micro.discovery.DiscoveryClientUtil;
import com.github.attemper.java.sdk.rest.web.client.WebRestClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;

public class WebMicroClient extends WebRestClient {

    protected DiscoveryClient discoveryClient;

    protected String serviceName;

    public WebMicroClient() {

    }

    @Override
    protected String getAddress(){
        return DiscoveryClientUtil.getAddress(discoveryClient, super.getAddress(), serviceName, serviceUrls);
    }

    public void discoveryClient(DiscoveryClient discoveryClient) {
        if(discoveryClient == null){
            throw new RuntimeException("discoveryClient can not be null");
        }
        this.discoveryClient = discoveryClient;
    }

    public void serviceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
