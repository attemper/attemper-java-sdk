package com.github.attemper.java.sdk.rest.handler;

import com.github.attemper.java.sdk.rest.context.AttemperContext;

/**
 * 回调接口
 * @author ldang
 */
public interface AfterHandler {

    /**
     * 不管什么情况下，都会执行到
     * @param context
     */
    void executeAlways(AttemperContext context);

    /**
     * code=200才执行
     * @param context
     */
    void executeOf200(AttemperContext context);

    /**
     * 非200时才执行
     * @param context
     */
    void executeNot200(AttemperContext context);

}
