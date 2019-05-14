package com.github.attemper.java.sdk.rest.context;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import org.apache.http.Header;

import java.util.List;

/**
 * @author ldang
 */
public interface AttemperContext<T> {

    AttemperContext url(String url);

    AttemperContext requestMethod(String requestMethod);

    AttemperContext headers(List<Header> headers);

    AttemperContext commonParam(BaseParam baseParam);

    AttemperContext commonResult(BaseResult baseResult);

    AttemperContext result(T result);

    String getUrl();

    String getRequestMethod();

    List<Header> getHeaders();

    BaseParam getBaseParam();

    BaseResult getBaseResult();

    T getResult();
}
