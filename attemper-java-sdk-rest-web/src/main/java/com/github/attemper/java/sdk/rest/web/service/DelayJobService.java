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
                .setRepeatCount(0)
                .setRepeatInterval(1)
                .setMisfireInstruction(2);
        return addDelayJob(delayJobParam);
    }

    /**
     * Run between startTime and endTime with interval seconds
     * @param jobName the name of job you fires
     * @param startTime startTime to mill seconds
     * @param interval which seconds
     * @param endTime endTime to mill seconds
     * @return
     */
    public BaseResult<DelayJobResult> startWithIntervalToEnd(String jobName, Long startTime, int interval, Long endTime) {
        DelayJobParam delayJobParam = new DelayJobParam()
                .setJobName(jobName)
                .setStartTime(startTime)
                .setRepeatInterval(interval)
                .setEndTime(endTime)
                .setMisfireInstruction(2);
        return addDelayJob(delayJobParam);
    }

    /**
     *  Just run once at startTime of a biz id
     * @param id you can initialize bizId yourself as id(binding your biz)
     * @param jobName the name of job you fires
     * @param startTime startTime to mill seconds
     * @return
     */
    public BaseResult<DelayJobResult> startThenEndOfBizId(String id, String jobName, Long startTime) {
        DelayJobParam delayJobParam = new DelayJobParam()
                .setId(id)
                .setJobName(jobName)
                .setStartTime(startTime)
                .setRepeatCount(0)
                .setRepeatInterval(1)
                .setMisfireInstruction(2);
        return addDelayJob(delayJobParam);
    }

    /**
     * Run between startTime and endTime with interval seconds of a biz id
     * @param id you can initialize bizId yourself as id(binding your biz)
     * @param jobName the name of job you fires
     * @param startTime startTime to mill seconds
     * @param interval which seconds
     * @param endTime endTime to mill seconds
     * @return
     */
    public BaseResult<DelayJobResult> startWithIntervalToEndOfBizId(String id, String jobName, Long startTime, int interval, Long endTime) {
        DelayJobParam delayJobParam = new DelayJobParam()
                .setId(id)
                .setJobName(jobName)
                .setStartTime(startTime)
                .setRepeatInterval(interval)
                .setEndTime(endTime)
                .setMisfireInstruction(2);
        return addDelayJob(delayJobParam);
    }

    /**
     * Diy for all fields of delayJobParam
     * @param delayJobParam the pram of delay job
     * @return
     */
    public BaseResult<DelayJobResult> addDelayJob(DelayJobParam delayJobParam) {
        return webRestClient.addDelayJob(delayJobParam);
    }

    /**
     * Remove the job you delayed by id
     * @param id delay id
     * @return
     */
    public BaseResult<Void> delete(String id) {
        return delete(Arrays.asList(id));
    }
    /**
     * Remove the job you delayed by batch ids
     * @param ids delay ids
     * @return
     */
    public BaseResult<Void> delete(List<String> ids) {
        DelayJobIdsParam delayJobIdsParam = new DelayJobIdsParam().setIds(ids);
        return webRestClient.deleteDelayJob(delayJobIdsParam);
    }

    @Autowired
    public void setWebRestClient(WebRestClient webRestClient) {
        this.webRestClient = webRestClient;
    }
}
