package com.github.attemper.java.sdk.rest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.attemper.java.sdk.common.constant.SdkCommonConstants;
import com.github.attemper.java.sdk.common.exception.RTException;
import com.github.attemper.java.sdk.common.param.CommonParam;
import com.github.attemper.java.sdk.common.result.CommonResult;
import com.github.attemper.java.sdk.common.util.DateUtil;
import com.github.attemper.java.sdk.rest.client.RestClient;
import com.github.attemper.java.sdk.rest.context.AttemperContext;
import com.github.attemper.java.sdk.rest.context.DefaultContext;
import com.github.attemper.java.sdk.rest.handler.AfterHandler;
import com.github.attemper.java.sdk.rest.handler.PreHandler;
import com.github.attemper.java.sdk.rest.interceptor.RequestInterceptor;
import org.apache.commons.codec.CharEncoding;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * http客户端工具
 * @auth ldang
 */
public class HttpClientSingleton {

    private static volatile HttpClientSingleton singleton;

    private RestClient restClient;

    private List<PreHandler> preHandlers;

    private List<AfterHandler> afterHandlers;

	private List<Header> headers;
	
	private static ObjectMapper mapper = new ObjectMapper();

	private static String token;
	
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
	}

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
	 * HttpClient发送POST请求
	 * @param url
	 * @param paramObj
	 * @param clazz
	 * @return
	 */
	public CommonResult post(String url, Object paramObj, Class<?> clazz){
		HttpPost httpPost = new HttpPost(url);
        try{
            String json = mapper.writeValueAsString(paramObj);
            StringEntity strEntity = new StringEntity(json, Charset.forName(CharEncoding.UTF_8));
            strEntity.setContentType(SdkCommonConstants.APPLICATION_JSON);
            httpPost.setEntity(strEntity);
            AttemperContext context = new DefaultContext();
            context
                    .url(url)
                    .requestMethod(HttpPost.METHOD_NAME)
                    .commonParam(paramObj instanceof CommonParam ? (CommonParam) paramObj : null);
            return execute(httpPost, context, clazz);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
	}

	/**
	 * HttpClient发送GET请求
	 * @param url 地址(自行拼接参数)
	 * @param clazz
	 * @return
	 */
	public CommonResult get(String url, Object paramObj, Class<?> clazz){
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setCharset(Charset.forName(CharEncoding.UTF_8));
            if (paramObj != null ) {
                builder.setParameters(createParams(paramObj));
            }
            AttemperContext context = new DefaultContext();
            context
                    .url(url)
                    .requestMethod(HttpGet.METHOD_NAME)
                    .commonParam(paramObj instanceof CommonParam ? (CommonParam) paramObj : null);
            return execute(new HttpGet(builder.build()), context, clazz);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
	}

    /**
     * 执行请求
     * @param httpUriRequest
     * @param clazz
     * @return
     */
	private CommonResult execute(HttpUriRequest httpUriRequest, AttemperContext context, Class<?> clazz) {
        CommonResult commonResult = null;
        CloseableHttpClient httpClient = null;
	    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	    try {
            httpUriRequest.setHeaders(headers.toArray(new Header[]{}));
            /*if(token != null) {
                httpUriRequest.addHeader(new BasicHeader(SdkCommonConstants.token, token));
            }*/
            httpClientBuilder.addInterceptorFirst(new RequestInterceptor(preHandlers, context));
            httpClient = httpClientBuilder.build();
            HttpResponse response = httpClient.execute(httpUriRequest);
            if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
                String starkResultStr = EntityUtils.toString(response.getEntity());
                JsonNode jsonNode = mapper.readTree(starkResultStr);
                commonResult = toResult(jsonNode);
                /*if(commonResult.getCode() == 1000 || commonResult.getCode() == 1002) { //token为空或过期
                    CommonResult<TokenResult> tokenResult = restClient.refreshToken();
                    token = tokenResult.getResult().getToken();
                    return execute(httpUriRequest, context, clazz);
                }*/
                context.commonResult(commonResult);
                if(jsonNode.has(SdkCommonConstants.result)){
                    Object result = mapper.readValue(
                            mapper.writeValueAsString(jsonNode.get(SdkCommonConstants.result)), clazz);
                    commonResult.setResult(result);
                    context.result(result);
                }
                if(afterHandlers != null) {
                    for(AfterHandler afterHandler : afterHandlers) {
                        afterHandler.executeAlways(context);
                        if(commonResult != null) {
                            if(commonResult.getCode() == SdkCommonConstants.OK) {
                                afterHandler.executeOf200(context);
                            }else{
                                afterHandler.executeNot200(context);
                            }
                        }
                    }
                }
            }
        } catch (RTException rte){
	      throw rte;
        } catch (HttpHostConnectException ex){
	        restClient.removeDisconnectAdress();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return commonResult;
    }

    /**
     * 参数转化
     * @param paramObj
     * @return
     */
    private List<NameValuePair> createParams(Object paramObj) throws IllegalAccessException{
        List<NameValuePair> list = new LinkedList<NameValuePair>();
        Class<?> clazz = paramObj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null) {
            for (Field field : fields) {
                if(field.get(paramObj) != null) {
                    field.setAccessible(true);
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
     * json node转result
     * @param jsonNode
     * @return
     */
	private CommonResult toResult(JsonNode jsonNode) {
        CommonResult result = new CommonResult();
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
            result.setResponseTime(DateUtil.parseDateStrToYYYYMMDDHHMMSSSSS(
                    jsonNode.get(SdkCommonConstants.responseTime).asText()));
        }
        return result;
    }

    public void setHeaders(List<Header> headers) {
		this.headers = headers;
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
