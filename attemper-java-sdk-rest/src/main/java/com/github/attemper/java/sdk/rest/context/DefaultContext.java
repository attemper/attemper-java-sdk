package com.github.attemper.java.sdk.rest.context;

import com.github.attemper.java.sdk.common.param.CommonParam;
import com.github.attemper.java.sdk.common.result.CommonResult;
import org.apache.http.Header;

import java.util.List;

/**
 * @author ldang
 */
public class DefaultContext<T> implements AttemperContext<T> {

    private String url;

    private String requestMethod;

    private List<Header> headers;

    private CommonParam commonParam;

    private CommonResult commonResult;

    private T result;

    public DefaultContext url(String url) {
        this.url = url;
        return this;
    }

    public DefaultContext requestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public DefaultContext headers(List<Header> headers) {
        this.headers = headers;
        return this;
    }

    public DefaultContext commonParam(CommonParam commonParam) {
        this.commonParam = commonParam;
        return this;
    }

    public DefaultContext commonResult(CommonResult commonResult) {
        this.commonResult = commonResult;
        return this;
    }

    public DefaultContext result(T result) {
        this.result = result;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public CommonParam getCommonParam() {
        return commonParam;
    }

    public CommonResult getCommonResult() {
        return commonResult;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "DefaultContext{" +
                "url='" + url + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", headers=" + headers +
                ", commonParam=" + commonParam +
                ", commonResult=" + commonResult +
                ", result=" + result +
                '}';
    }
}
