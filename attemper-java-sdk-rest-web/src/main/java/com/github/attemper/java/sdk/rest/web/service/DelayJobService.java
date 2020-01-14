package com.github.attemper.java.sdk.rest.web.service;

import com.github.attemper.java.sdk.common.result.BaseResult;
import com.github.attemper.java.sdk.common.web.param.delay.DelayJobParam;
import com.github.attemper.java.sdk.common.web.param.delay.DelayJobIdsParam;
import com.github.attemper.java.sdk.common.web.result.delay.DelayJobResult;
import com.github.attemper.java.sdk.rest.web.client.WebRestClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class DelayJobService {

    private WebRestClient webRestClient;

    /**
     * Just run once at startTime
     * @param jobName the name of job you fires
     * @param startTime startTime to mill seconds
     * @return
     */
    public BaseResult<DelayJobResult> startThenEnd(String jobName, Long startTime) {
        DelayJobParam delayJobParam = new DelayJobParam()
                .setJobName(jobName)
                .setStartTime(startTime)
                .setEndTime(startTime)
                .setInterval(1)
                .setMisfireInstruction(2);
        return webRestClient.addDelayJob(delayJobExtSaveParam);
    }

    public BaseResult<DelayJobResult> startWithIntervalToEnd(String jobName, Date startTime, int interval, Date endTime) {
        DelayJobExtSaveParam delayJobExtSaveParam = new DelayJobExtSaveParam()
                .setJobName(jobName)
                .setStartTime(startTime)
                .setInterval(interval)
                .setEndTime(endTime)
                .setMisfireInstruction(2);
        return webRestClient.addDelayJob(delayJobExtSaveParam);
    }

    public BaseResult<DelayJobResult> startThenEndOfBizId(String id, String jobName, Date startTime) {
        DelayJobExtSaveParam delayJobExtSaveParam = new DelayJobExtSaveParam()
                .setId(id)
                .setJobName(jobName)
                .setStartTime(startTime)
                .setEndTime(startTime)
                .setInterval(1)
                .setMisfireInstruction(2);
        return webRestClient.addDelayJob(delayJobExtSaveParam);
    }

    public BaseResult<DelayJobResult> startWithIntervalToEndOfBizId(String id, String jobName, Date startTime, int interval, Date endTime) {
        DelayJobExtSaveParam delayJobExtSaveParam = new DelayJobExtSaveParam()
                .setId(id)
                .setJobName(jobName)
                .setStartTime(startTime)
                .setInterval(interval)
                .setEndTime(endTime)
                .setMisfireInstruction(2);
        return webRestClient.addDelayJob(delayJobExtSaveParam);
    }

    public BaseResult<Void> delete(List<String> ids) {
        DelayJobIdsParam delayJobIdsParam = new DelayJobIdsParam().setIds(ids);
        return webRestClient.deleteDelayJob(delayJobIdsParam);
    }

    @Autowired
    public void setWebRestClient(WebRestClient webRestClient) {
        this.webRestClient = webRestClient;
    }
}
