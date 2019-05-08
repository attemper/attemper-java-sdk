package com.github.attemper.java.sdk.rest.client;

import com.github.attemper.java.sdk.common.constant.SdkCommonConstants;
import com.github.attemper.java.sdk.common.constant.SdkGlobalConstants;
import com.github.attemper.java.sdk.common.exception.RTException;
import com.github.attemper.java.sdk.rest.handler.AfterHandler;
import com.github.attemper.java.sdk.rest.handler.PreHandler;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

public class RestClient {

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

	private volatile String realAddress;
	
	protected RestClient() {
		
	}

	public RestClient(RestClientBuilder builder){
		this.serviceUrls = builder.serviceUrls;
		this.serviceName = builder.serviceName;
		this.tenantId = builder.tenantId;
		this.sign = builder.sign;
		this.preHandlers = builder.preHandlers;
		this.afterHandlers = builder.afterHandlers;

		init();
	}

	protected void init() {
		initServiceName(serviceName);
		initHeaders();
		HttpClientSingleton.getInstance().setRestClient(this);
		HttpClientSingleton.getInstance().registerPreHandlers(preHandlers);
		HttpClientSingleton.getInstance().registerAfterHandlers(afterHandlers);
	}

	protected void initHeaders() {
		List<Header> headers = new ArrayList<Header>(5);
		headers.add(new BasicHeader(SdkCommonConstants.tenantId, this.tenantId));
		headers.add(new BasicHeader(SdkCommonConstants.sign, this.sign));
        HttpClientSingleton.getInstance().setHeaders(headers);
	}

	/**
	 * 登录接口
	 * @param loginParam
	 * @return
	 */
	/*public CommonResult<LoginResult> login(LoginParam loginParam) {
		return HttpClientSingleton.getInstance().post(getUrl(APIPath.Login.LOGIN_BY_USERNAME_PWD), loginParam, LoginResult.class);
	}*/

	/**
	 * 获取用户信息
	 * @return
	 */
	/*public CommonResult<UserInfo> getUserInfo() {
	    return HttpClientSingleton.getInstance().get(getUrl(APIPath.User.INFO), null, UserInfo.class);
    }*/

	/**
	 * 获取用户和其管理的租户的信息
	 * @return
	 */
	/*public CommonResult<AdminInfo> getAdminInfo() {
		return HttpClientSingleton.getInstance().get(getUrl(APIPath.User.ADMIN_INFO), null, AdminInfo.class);
	}
*/
	/*
    public CommonResult<TokenResult> refreshToken() {
		return HttpClientSingleton.getInstance().get(getUrl(APIPath.Token.REFRESH), null, TokenResult.class);
	}*/
	
	/**
     * 计算url
	 * @param apiPath 拼接具体接口地址
	 * @return
	 */
	private String getUrl(String apiPath) {
		return getAddress() + serviceName + apiPath;
	}
	
	/**
	 * 获取服务地址
	 * @return
	 */
	protected String getAddress(){
	    int size = serviceUrls.size();
	    if(size == 0){
            throw new RTException(801);
        }
        int randomInt = (size == 1) ? (int) (Math.random() * size) : 0;
        realAddress = serviceUrls.get(randomInt);
        return realAddress;
	}

	protected void initServiceName(String serviceName){
        this.serviceName = (serviceName == null ? SdkGlobalConstants.defaultContextPath : serviceName);
    }

    /**
     * 移除连接出现异常的地址，一般是url错误
     * 仅对非eureka有效，eureka取出的连接down掉时，由eureka自己清除
     */
    public void removeDisconnectAdress() {
        if(this.serviceUrls != null){
            this.serviceUrls.remove(realAddress);
        }
    }
}
