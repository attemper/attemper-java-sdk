package com.github.attemper.java.sdk.micro.executor.client;

import com.github.attemper.java.sdk.common.executor.constant.ExecutorAPIPath;
import com.github.attemper.java.sdk.common.executor.param.log.LogParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.micro.discovery.DiscoveryClientUtil;
import com.github.attemper.java.sdk.rest.executor.client.ExecutorRestClient;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;
import org.springframework.cloud.client.discovery.DiscoveryClient;

public class ExecutorMicroClient extends ExecutorRestClient {

    protected DiscoveryClient discoveryClient;

    protected String serviceName;

    public ExecutorMicroClient() {

    }

    public BaseResult<Void> appendLog(LogParam logParam) {
        return HttpClientSingleton.getInstance().post(
                getUrl(ExecutorAPIPath.APPEND_LOG),
                logParam,
                Void.class);
    }

    @Override
    protected String getAddress(){
        return DiscoveryClientUtil.getAddress(discoveryClient, serviceName, serviceUrls);
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
