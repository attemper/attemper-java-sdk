package com.github.attemper.java.sdk.rest.web.client;

import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.common.web.constant.WebAPIPath;
import com.github.attemper.java.sdk.common.web.param.delay.DelayJobParam;
import com.github.attemper.java.sdk.common.web.param.delay.DelayJobIdsParam;
import com.github.attemper.java.sdk.common.web.result.delay.DelayJobResult;
import com.github.attemper.java.sdk.rest.client.RestClient;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;

public class WebRestClient extends RestClient {

    public BaseResult<DelayJobResult> addDelayJob(DelayJobParam param) {
        return HttpClientSingleton.getInstance().antiGet(
                HttpPost.METHOD_NAME,
                getUrl(WebAPIPath.DelayJobPath.$),
                param,
                DelayJobResult.class);
    }

    public BaseResult<Void> deleteDelayJob(DelayJobIdsParam param) {
        return HttpClientSingleton.getInstance().antiGet(
                HttpDelete.METHOD_NAME,
                getUrl(WebAPIPath.DelayJobPath.$),
                param,
                Void.class);
    }
}
