package com.github.attemper.java.sdk.rest.executor.client;

import com.github.attemper.java.sdk.common.executor.constant.ExecutorAPIPath;
import com.github.attemper.java.sdk.common.executor.param.execution.EndParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.common.util.StringUtils;
import com.github.attemper.java.sdk.rest.client.RestClient;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;

public class ExecutorRestClient extends RestClient {

/*
    public BaseResult<Void> appendLog(LogParam logParam) {
        return HttpClientSingleton.getInstance().post(
                getUrlByRequestPath(logParam.getBaseExecutionParam().getRequestPath(), ExecutorAPIPath.APPEND_LOG),
                logParam,
                Void.class);
    }
*/

    public BaseResult<Void> signal(EndParam endParam) {
        return HttpClientSingleton.getInstance().post(
                getUrlByRequestPath(endParam.getBaseExecutionParam().getRequestPath(), ExecutorAPIPath.SIGNAL),
                endParam,
                Void.class);
    }

    private String getUrlByRequestPath(String requestPath, String apiPath) {
        return requestPath + StringUtils.trimToEmpty(contextPath) + apiPath;
    }
}
