package com.github.attemper.java.sdk.rest.biz2executor.client;

import com.github.attemper.java.sdk.common.biz2executor.constant.Biz2ExecutorAPIPath;
import com.github.attemper.java.sdk.common.biz2executor.param.end.EndExecutionParam;
import com.github.attemper.java.sdk.common.biz2executor.param.log.LogParam;
import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.common.util.StringUtils;
import com.github.attemper.java.sdk.rest.client.RestClient;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;

public class Biz2ExecutorRestClient extends RestClient {

    public BaseResult<Void> appendLog(LogParam logParam) {
        return HttpClientSingleton.getInstance().post(
                getUrlByRequestPath(logParam.getBaseExecutionParam().getRequestPath(), Biz2ExecutorAPIPath.APPEND_LOG),
                logParam,
                Void.class);
    }

    public BaseResult<Void> signal(EndExecutionParam endExecutionParam) {
        return HttpClientSingleton.getInstance().post(
                getUrlByRequestPath(endExecutionParam.getBaseExecutionParam().getRequestPath(), Biz2ExecutorAPIPath.SIGNAL),
                endExecutionParam,
                Void.class);
    }

    private String getUrlByRequestPath(String requestPath, String apiPath) {
        return requestPath + StringUtils.trimToEmpty(contextPath) + apiPath;
    }
}
