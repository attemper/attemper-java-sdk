package com.github.attemper.java.sdk.rest.handler;

import com.github.attemper.java.sdk.rest.context.AttemperContext;
import org.apache.http.HttpRequest;

/**
 * 调用后执行
 * @author ldang
 */
public interface PreHandler {

    /**
     * 在调用接口前执行
     * @param context
     */
    void execute(HttpRequest httpRequest, AttemperContext context);

}
