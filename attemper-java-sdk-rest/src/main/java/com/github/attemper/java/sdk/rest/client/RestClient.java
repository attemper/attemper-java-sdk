package com.github.attemper.java.sdk.rest.client;

import com.github.attemper.java.sdk.common.constant.SdkAPIPath;
import com.github.attemper.java.sdk.common.param.sys.login.LoginParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.common.result.sys.login.LoginResult;
import com.github.attemper.java.sdk.common.util.StringUtils;
import com.github.attemper.java.sdk.rest.handler.AfterHandler;
import com.github.attemper.java.sdk.rest.handler.PreHandler;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestClient {

    /**
     * service urls
     */
    protected List<String> serviceUrls = new ArrayList<String>(4);

	/**
	 * service name
	 */
	protected String contextPath;

	protected LoginParam loginParam;

	protected List<PreHandler> preHandlers;

	protected List<AfterHandler> afterHandlers;

	private volatile String realAddress;

	public RestClient() {}

	public void initialize() {
		HttpClientSingleton.getInstance().setRestClient(this);
		HttpClientSingleton.getInstance().registerPreHandlers(preHandlers);
		HttpClientSingleton.getInstance().registerAfterHandlers(afterHandlers);
	}

    public BaseResult<LoginResult> login() {
		return HttpClientSingleton.getInstance().post(getUrl(SdkAPIPath.LoginPath.LOGIN_BY_ENCODED_USERNAME_PWD), loginParam, LoginResult.class);
	}
	
	/**
     * execute url
	 * @param apiPath api path
	 * @return
	 */
	protected String getUrl(String apiPath) {
		StringBuilder sb = new StringBuilder();
		sb.append(getAddress()).append(StringUtils.trimToEmpty(contextPath)).append(apiPath);
		return sb.toString();
	}
	
	/**
	 * service address
	 * @return
	 */
	protected String getAddress(){
	    int size = serviceUrls.size();
	    if(size == 0){
            throw new RuntimeException("Services are all abnormal");
        }
        int randomInt = (size == 1) ? (int) (Math.random() * size) : 0;
        realAddress = serviceUrls.get(randomInt);
        return realAddress;
	}

    /**
     * 移除连接出现异常的地址，一般是url错误
     * 仅对非eureka有效，eureka取出的连接down掉时，由eureka自己清除
     */
    public void removeDisconnectAddress() {
        if(this.serviceUrls != null){
            this.serviceUrls.remove(realAddress);
        }
    }

	public void serviceUrls(List<String> serviceUrls){
		if(serviceUrls == null || serviceUrls.isEmpty()){
			throw new RuntimeException("Services are all abnormal");
		}
		this.serviceUrls = serviceUrls;
	}

	public void addServiceUrl(String serviceUrl){
		if (serviceUrl != null) {
			if(serviceUrls == null){
				serviceUrls = new LinkedList<String>();
				serviceUrls.add(serviceUrl);
			}else if(!this.serviceUrls.contains(serviceUrl)) {
				this.serviceUrls.add(serviceUrl);
			}
		}
	}

	public void contextPath(String contextPath){
		this.contextPath = contextPath;
	}

	public void loginParam(LoginParam loginParam){
		this.loginParam = loginParam;
	}

	public void addPreHandler(PreHandler preHandler){
		if(preHandler != null){
			if(preHandlers == null) {
				preHandlers = new LinkedList<PreHandler>();
			}
			preHandlers.add(preHandler);
		}
	}

	public void addAfterHandler(AfterHandler afterHandler){
		if(afterHandler != null){
			if(afterHandlers == null) {
				afterHandlers = new LinkedList<AfterHandler>();
			}
			afterHandlers.add(afterHandler);
		}
	}

	public void preHandlers(List<PreHandler> preHandlers){
		this.preHandlers = preHandlers;
	}

	public void afterHandlers(List<AfterHandler> afterHandlers){
		this.afterHandlers = afterHandlers;
	}
}
