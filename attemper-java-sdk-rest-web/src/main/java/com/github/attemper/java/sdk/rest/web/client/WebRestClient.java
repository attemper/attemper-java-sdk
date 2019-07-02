package com.github.attemper.java.sdk.rest.web.client;

import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.common.web.constant.WebAPIPath;
import com.github.attemper.java.sdk.common.web.param.delay.DelayJobExtSaveParam;
import com.github.attemper.java.sdk.common.web.param.delay.DelayJobIdsParam;
import com.github.attemper.java.sdk.common.web.result.delay.DelayJobResult;
import com.github.attemper.java.sdk.rest.client.RestClient;
import com.github.attemper.java.sdk.rest.util.HttpClientSingleton;

public class WebRestClient extends RestClient {

    public BaseResult<DelayJobResult> addDelayJob(DelayJobExtSaveParam param) {
        return HttpClientSingleton.getInstance().post(
                getUrl(WebAPIPath.DelayJobPath.EXT),
                param,
                DelayJobResult.class);
    }

    public BaseResult<Void> deleteDelayJob(DelayJobIdsParam param) {
        return HttpClientSingleton.getInstance().delete(
                getUrl(WebAPIPath.DelayJobPath.$),
                param,
                Void.class);
    }
}
