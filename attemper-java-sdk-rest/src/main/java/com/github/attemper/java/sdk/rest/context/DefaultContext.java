package com.github.attemper.java.sdk.rest.context;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import org.apache.http.Header;

import java.util.List;

/**
 * @author ldang
 */
public class DefaultContext<T> implements AttemperContext<T> {

    private String url;

    private String requestMethod;

    private List<Header> headers;

    private BaseParam baseParam;

    private BaseResult baseResult;

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

    public DefaultContext commonParam(BaseParam baseParam) {
        this.baseParam = baseParam;
        return this;
    }

    public DefaultContext commonResult(BaseResult baseResult) {
        this.baseResult = baseResult;
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

    public BaseParam getBaseParam() {
        return baseParam;
    }

    public BaseResult getBaseResult() {
        return baseResult;
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
                ", baseParam=" + baseParam +
                ", baseResult=" + baseResult +
                ", result=" + result +
                '}';
    }
}
