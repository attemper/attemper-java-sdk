package com.github.attemper.java.sdk.micro.biz2executor.client;

import com.github.attemper.java.sdk.common.biz2executor.constant.Biz2ExecutorAPIPath;
import com.github.attemper.java.sdk.common.biz2executor.param.log.LogParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.rest.biz2executor.client.Biz2ExecutorRestClient;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

public class Biz2ExecutorMicroClient extends Biz2ExecutorRestClient {

    /**
     * discoveryClient
     */
    protected DiscoveryClient discoveryClient;

    protected String serviceName;

    public Biz2ExecutorMicroClient() {

    }

    public BaseResult<Void> appendLog(LogParam logParam) {
        return HttpClientSingleton.getInstance().post(
                getUrl(Biz2ExecutorAPIPath.APPEND_LOG),
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
