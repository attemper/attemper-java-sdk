package com.github.attemper.java.sdk.microservice.client;

import com.github.attemper.java.sdk.rest.client.RestClient;
import com.netflix.discovery.EurekaClient;

/**
 * @author ldang
 */
public class MicroServiceClient extends RestClient {

	/**
	 * eureka client
	 */
	private EurekaClient eurekaClient;

/*	*//**
	 * 由EurekaClient构造客户端
	 * @param eurekaClient Eureka客户端
     * @param serviceName
	 * @param tenantId 租户编号
	 * @param sign 认证签名
	 *//*
	public MicroServiceClient(EurekaClient eurekaClient, String serviceName, String tenantId, String sign) {
		this.eurekaClient = eurekaClient;
        initServiceName(serviceName);
		this.tenantId = tenantId;
		this.sign = sign;
		this.initHeaders();
	}*/

	/**
	 * 由服务地址列表构造客户端
	 * @param serviceUrls 服务地址列表
     * @param serviceName 服务名
	 * @param tenantId 租户编号
	 * @param sign 认证签名
	 *//*
	public MicroServiceClient(List<String> serviceUrls, String serviceName, String tenantId, String sign) {
		super(serviceUrls, serviceName, tenantId, sign);
	}*/

	protected MicroServiceClient() {

	}

	public MicroServiceClient(MicroServiceClientBuilder builder) {
		super(builder);
		this.eurekaClient = builder.eurekaClient;
	}



	/**
	 * 获取服务地址
	 * @return
	 */
	@Override
	protected String getAddress(){
		if(eurekaClient == null){
			return super.getAddress();
		}
		try {
			String realUrl = eurekaClient.getNextServerFromEureka(serviceName.toUpperCase(), false).getHomePageUrl();
			if(!serviceUrls.contains(realUrl)) {
                serviceUrls.add(realUrl);
            }
			return realUrl;
		} catch (Exception e) {
		    e.printStackTrace();
            return super.getAddress();
		}
	}
}
