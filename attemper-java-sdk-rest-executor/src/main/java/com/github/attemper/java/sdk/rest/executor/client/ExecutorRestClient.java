package com.github.attemper.java.sdk.rest.executor.client;

import com.github.attemper.java.sdk.common.executor.constant.ExecutorAPIPath;
import com.github.attemper.java.sdk.common.executor.param.execution.EndParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.common.util.StringUtils;
import com.github.attemper.java.sdk.rest.client.RestClient;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;
import org.apache.http.client.methods.HttpPost;

public class ExecutorRestClient extends RestClient {
    /**
     * After async http task executes completed, call this method to signal
     * @param endParam
     * @return
     */
    public BaseResult<Void> signal(EndParam endParam) {
        return HttpClientSingleton.getInstance().antiGet(
                HttpPost.METHOD_NAME,
                getUrlByRequestPath(endParam.getBaseExecutionParam().getRequestPath(), ExecutorAPIPath.SIGNAL),
                endParam,
                Void.class);
    }

    private String getUrlByRequestPath(String requestPath, String apiPath) {
        return requestPath + StringUtils.trimToEmpty(contextPath) + apiPath;
    }
}
