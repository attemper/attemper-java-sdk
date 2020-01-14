package com.github.attemper.java.sdk.common.web.param.delay;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.util.StringUtils;

import java.util.List;

public class DelayJobParam implements BaseParam {

    protected String id;

    protected String jobName;

    protected Long startTime;

    protected Integer repeatInterval;

    protected int repeatCount = -1;

    protected Long endTime;

    protected Integer misfireInstruction;

    protected List<String> calendarNames;

    @Override
    public String validate() {
        if (StringUtils.isBlank(jobName)) {
            return "6000";
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public DelayJobParam setId(String id) {
        this.id = id;
        return this;
    }

    public String getJobName() {
        return jobName;
    }

    public DelayJobParam setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public DelayJobParam setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public DelayJobParam setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
        return this;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public DelayJobParam setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public DelayJobParam setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getMisfireInstruction() {
        return misfireInstruction;
    }

    public DelayJobParam setMisfireInstruction(Integer misfireInstruction) {
        this.misfireInstruction = misfireInstruction;
        return this;
    }

    public List<String> getCalendarNames() {
        return calendarNames;
    }

    public DelayJobParam setCalendarNames(List<String> calendarNames) {
        this.calendarNames = calendarNames;
        return this;
    }

}
