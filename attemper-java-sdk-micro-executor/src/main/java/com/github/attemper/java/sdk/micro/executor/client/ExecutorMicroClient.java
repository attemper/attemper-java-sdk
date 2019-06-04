package com.github.attemper.java.sdk.micro.executor.client;

import com.github.attemper.java.sdk.common.executor.constant.ExecutorAPIPath;
import com.github.attemper.java.sdk.common.executor.param.log.LogParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.rest.executor.client.ExecutorRestClient;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

public class ExecutorMicroClient extends ExecutorRestClient {

    /**
     * discoveryClient
     */
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

    /**
     * 获取服务地址
     * @return
     */
    @Override
    protected String getAddress(){
        if(discoveryClient == null){
            return super.getAddress();
        }
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName.toUpperCase());
            if (instances.isEmpty()) {
                instances = discoveryClient.getInstances(serviceName);
                if (instances.isEmpty()) {
                    return super.getAddress();
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
            return super.getAddress();
        }
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
