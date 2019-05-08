package com.github.attemper.java.sdk.microservice.client;

import com.github.attemper.java.sdk.common.exception.RTException;
import com.github.attemper.java.sdk.rest.client.RestClientBuilder;
import com.netflix.discovery.EurekaClient;

/**
 * @author ldang
 */
public class MicroServiceClientBuilder extends RestClientBuilder<MicroServiceClientBuilder> {

    protected EurekaClient eurekaClient;

    public MicroServiceClientBuilder() {

    }

    public static MicroServiceClientBuilder create() {
        return new MicroServiceClientBuilder();
    }

    public MicroServiceClient build() {
        super.build();
        return new MicroServiceClient(this);
    }

    public MicroServiceClientBuilder eurekaClient(EurekaClient eurekaClient){
        if(eurekaClient == null){
            throw new RTException(800);
        }
        this.eurekaClient = eurekaClient;
        return this;
    }
}
