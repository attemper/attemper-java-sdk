package com.github.attemper.java.sdk.rest.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.attemper.java.sdk.common.constant.SdkCommonConstants;
import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.common.result.sys.login.LoginResult;
import com.github.attemper.java.sdk.common.util.DateUtil;
import com.github.attemper.java.sdk.rest.client.RestClient;
import com.github.attemper.java.sdk.rest.context.AttemperContext;
import com.github.attemper.java.sdk.rest.context.DefaultContext;
import com.github.attemper.java.sdk.rest.handler.AfterHandler;
import com.github.attemper.java.sdk.rest.handler.PreHandler;
import com.github.attemper.java.sdk.rest.interceptor.RequestInterceptor;
import com.github.attemper.java.sdk.rest.spring.SpringContextUtil;
import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * http util
 */
public class HttpClientSingleton {

    private static volatile HttpClientSingleton singleton;

    private RestClient restClient;

    private List<PreHandler> preHandlers;

    private List<AfterHandler> afterHandlers;

	private static String token;

    private HttpClientSingleton() {}

    public static HttpClientSingleton getInstance() {
        if (singleton == null) {
            synchronized (HttpClientSingleton.class) {
                if (singleton == null) {
                    singleton = new HttpClientSingleton();
                }
            }
        }
        return singleton;
    }

	/**
     * post
	 * @param url
	 * @param paramObj
	 * @param clazz
	 * @return
	 */
	public BaseResult post(String url, Object paramObj, Class<?> clazz){
		HttpPost httpPost = new HttpPost(url);
        String json = BeanUtil.bean2JsonStr(paramObj);
        StringEntity strEntity = new StringEntity(json, Charset.forName(CharEncoding.UTF_8));
        strEntity.setContentType(SdkCommonConstants.APPLICATION_JSON);
        httpPost.setEntity(strEntity);
        AttemperContext context = new DefaultContext();
        context
                .url(url)
                .requestMethod(HttpPost.METHOD_NAME)
                .commonParam(paramObj instanceof BaseParam ? (BaseParam) paramObj : null);
        return execute(httpPost, context, clazz);
	}

    /**
     * post
     *
     * @param url
     * @param paramObj
     * @param clazz
     * @return
     */
    public BaseResult delete(String url, Object paramObj, Class<?> clazz) {
        return getOrDelete(HttpDelete.METHOD_NAME, url, paramObj, clazz);
    }

    /**
     * get
     * @param url
	 * @param clazz
	 * @return
	 */
	public BaseResult get(String url, Object paramObj, Class<?> clazz){
        return getOrDelete(HttpGet.METHOD_NAME, url, paramObj, clazz);
	}

	private BaseResult getOrDelete(String methodName, String url, Object paramObj, Class<?> clazz) {
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setCharset(Charset.forName(CharEncoding.UTF_8));
            if (paramObj != null ) {
                builder.setParameters(createParams(paramObj));
            }
            AttemperContext context = new DefaultContext();
            context
                    .url(url)
                    .requestMethod(methodName)
                    .commonParam(paramObj instanceof BaseParam ? (BaseParam) paramObj : null);
            HttpUriRequest httpUriRequest = HttpDelete.METHOD_NAME.equals(methodName)
                    ? new HttpDelete(builder.build()) : new HttpGet(builder.build());
            return execute(httpUriRequest, context, clazz);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }  catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param httpUriRequest
     * @param clazz
     * @return
     */
	private BaseResult execute(HttpUriRequest httpUriRequest, AttemperContext context, Class<?> clazz) {
        BaseResult baseResult = null;
        CloseableHttpClient httpClient = null;
	    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	    try {
            httpUriRequest.setHeader(new BasicHeader(SdkCommonConstants.token, token));
            httpClientBuilder.addInterceptorFirst(new RequestInterceptor(preHandlers, context));
            httpClient = httpClientBuilder.build();
            HttpResponse response = httpClient.execute(httpUriRequest);
            switch (response.getStatusLine().getStatusCode()) {
                case HttpStatus.SC_OK:
                    String starkResultStr = EntityUtils.toString(response.getEntity());
                    JsonNode jsonNode = SpringContextUtil.getBean(ObjectMapper.class).readTree(starkResultStr);
                    baseResult = toResult(jsonNode);
                    context.commonResult(baseResult);
                    if(jsonNode.has(SdkCommonConstants.result)){
                        Object result = SpringContextUtil.getBean(ObjectMapper.class).readValue(
                                BeanUtil.bean2JsonStr(jsonNode.get(SdkCommonConstants.result)), clazz);
                        baseResult.setResult(result);
                        context.result(result);
                    }
                    if(afterHandlers != null) {
                        for(AfterHandler afterHandler : afterHandlers) {
                            afterHandler.executeAlways(context);
                            if(baseResult != null) {
                                if(baseResult.getCode() == SdkCommonConstants.OK) {
                                    afterHandler.executeOf200(context);
                                }else{
                                    afterHandler.executeNot200(context);
                                }
                            }
                        }
                    }
                    break;
                case HttpStatus.SC_UNAUTHORIZED:
                    BaseResult<LoginResult> tokenResult = restClient.login();
                    token = tokenResult.getResult().getToken();
                    return execute(httpUriRequest, context, clazz);
                default: break;
            }
        } catch (HttpHostConnectException ex){
	        restClient.removeDisconnectAddress();
            throw new RuntimeException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return baseResult;
    }

    /**
     * @param paramObj
     * @return
     */
    private List<NameValuePair> createParams(Object paramObj) throws IllegalAccessException{
        List<NameValuePair> list = new LinkedList<NameValuePair>();
        Class<?> clazz = paramObj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                if(field.get(paramObj) != null) {
                    String value;
                    if (field.getDeclaringClass() == Integer.class) {
                        value = String.valueOf(field.getInt(paramObj));
                    } else if (field.getDeclaringClass() == Long.class) {
                        value = String.valueOf(field.getLong(paramObj));
                    } else if (field.getDeclaringClass() == Boolean.class) {
                        value = String.valueOf(field.getBoolean(paramObj));
                    } else if (field.getDeclaringClass() == Double.class) {
                        value = String.valueOf(field.getDouble(paramObj));
                    } else if (field.getDeclaringClass() == Float.class) {
                        value = String.valueOf(field.getFloat(paramObj));
                    } else if (field.getDeclaringClass() == Character.class) {
                        value = String.valueOf(field.getChar(paramObj));
                    } else if (field.getDeclaringClass() == Short.class) {
                        value = String.valueOf(field.getShort(paramObj));
                    } else {
                        value = field.get(paramObj).toString();
                    }
                    BasicNameValuePair pair = new BasicNameValuePair(field.getName(), value);
                    list.add(pair);
                }
            }
        }
        return list;
    }

    /**
     * json node to result
     * @param jsonNode
     * @return
     */
	private BaseResult toResult(JsonNode jsonNode) {
        BaseResult result = new BaseResult();
        if(jsonNode.has(SdkCommonConstants.code)) {
            result.setCode(jsonNode.get(SdkCommonConstants.code).asInt());
        }
        if(jsonNode.has(SdkCommonConstants.msg)) {
            result.setMsg(jsonNode.get(SdkCommonConstants.msg).asText());
        }
        if(jsonNode.has(SdkCommonConstants.duration)) {
            result.setDuration(jsonNode.get(SdkCommonConstants.duration).asText());
        }
        if(jsonNode.has(SdkCommonConstants.responseTime)) {
            result.setResponseTime(DateUtil.parse(
                    jsonNode.get(SdkCommonConstants.responseTime).asText(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
        }
        return result;
    }

    public void registerPreHandlers(List<PreHandler> preHandlers) {
        this.preHandlers = preHandlers;
    }

    public void registerAfterHandlers(List<AfterHandler> afterHandlers) {
        this.afterHandlers = afterHandlers;
    }

    public void setRestClient(RestClient restClient){
	    this.restClient = restClient;
    }

}
