package com.github.attemper.java.sdk.rest.context;

import com.github.attemper.java.sdk.common.param.CommonParam;
import com.github.attemper.java.sdk.common.result.CommonResult;
import org.apache.http.Header;

import java.util.List;

/**
 * @author ldang
 */
public interface AttemperContext<T> {

    AttemperContext url(String url);

    AttemperContext requestMethod(String requestMethod);

    AttemperContext headers(List<Header> headers);

    AttemperContext commonParam(CommonParam commonParam);

    AttemperContext commonResult(CommonResult commonResult);

    AttemperContext result(T result);

    String getUrl();

    String getRequestMethod();

    List<Header> getHeaders();

    CommonParam getCommonParam();

    CommonResult getCommonResult();

    T getResult();
}
