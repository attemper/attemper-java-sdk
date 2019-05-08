package com.github.attemper.java.sdk.rest.interceptor;

import com.github.attemper.java.sdk.rest.context.AttemperContext;
import com.github.attemper.java.sdk.rest.handler.PreHandler;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author ldang
 */
public class RequestInterceptor implements HttpRequestInterceptor {

    private List<PreHandler> preHandlers;

    private AttemperContext context;

    public RequestInterceptor(List<PreHandler> preHandlers, AttemperContext context) {
        this.preHandlers = preHandlers;
        this.context = context;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        context.headers(Arrays.asList(httpRequest.getAllHeaders()));
        if(preHandlers != null){
            for(PreHandler preHandler : preHandlers){
                preHandler.execute(httpRequest, context);
            }
        }
    }
}
