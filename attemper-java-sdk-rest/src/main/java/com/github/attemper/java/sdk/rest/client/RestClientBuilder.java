package com.github.attemper.java.sdk.rest.client;

import com.github.attemper.java.sdk.common.exception.RTException;
import com.github.attemper.java.sdk.rest.handler.AfterHandler;
import com.github.attemper.java.sdk.rest.handler.PreHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ldang
 */
public class RestClientBuilder<T extends RestClientBuilder<T>> {

    /**
     * 服务地址列表
     */
    protected List<String> serviceUrls;

    /**
     * 服务名
     */
    protected String serviceName;

    /**
     * 租户ID
     */
    protected String tenantId;

    /**
     * 认证签名<br>
     * 一个租户分配一个
     */
    protected String sign;

    protected List<PreHandler> preHandlers;

    protected List<AfterHandler> afterHandlers;

    public RestClientBuilder() {

    }

    public static RestClientBuilder create() {
        return new RestClientBuilder();
    }

    public RestClient build() {
        return new RestClient(this);
    }

    public T serviceUrls(List<String> serviceUrls){
        if(serviceUrls == null || serviceUrls.isEmpty()){
            throw new RTException(801);
        }
        this.serviceUrls = serviceUrls;
        return (T) this;
    }

    public RestClientBuilder addServiceUrl(String serviceUrl){
        if (serviceUrl != null) {
            if(serviceUrls == null){
                serviceUrls = new LinkedList<String>();
                serviceUrls.add(serviceUrl);
            }else if(!this.serviceUrls.contains(serviceUrl)) {
                this.serviceUrls.add(serviceUrl);
            }
        }
        return this;
    }

    public T serviceName(String serviceName){
        this.serviceName = serviceName;
        return (T) this;
    }

    public T tenantId(String tenantId){
        this.tenantId = tenantId;
        return (T) this;
    }

    public T sign(String sign){
        this.sign = sign;
        return (T) this;
    }

    public T addPreHandler(PreHandler preHandler){
        if(preHandler != null){
            if(preHandlers == null) {
                preHandlers = new LinkedList<PreHandler>();
            }
            preHandlers.add(preHandler);
        }
        return (T) this;
    }

    public T addAfterHandler(AfterHandler afterHandler){
        if(afterHandler != null){
            if(afterHandlers == null) {
                afterHandlers = new LinkedList<AfterHandler>();
            }
            afterHandlers.add(afterHandler);
        }
        return (T) this;
    }

    public T preHandlers(List<PreHandler> preHandlers){
        this.preHandlers = preHandlers;
        return (T) this;
    }

    public T afterHandlers(List<AfterHandler> afterHandlers){
        this.afterHandlers = afterHandlers;
        return (T) this;
    }

}
