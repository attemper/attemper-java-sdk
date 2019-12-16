package com.github.attemper.java.sdk.common.web.param.delay;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.util.StringUtils;

import java.util.List;

public class DelayJobExtSaveParam implements BaseParam {

    protected String id;

    protected String jobName;

    protected Long startTime;

    protected Integer repeatInterval;

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

    public DelayJobExtSaveParam setId(String id) {
        this.id = id;
        return this;
    }

    public String getJobName() {
        return jobName;
    }

    public DelayJobExtSaveParam setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public DelayJobExtSaveParam setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public DelayJobExtSaveParam setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public DelayJobExtSaveParam setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getMisfireInstruction() {
        return misfireInstruction;
    }

    public DelayJobExtSaveParam setMisfireInstruction(Integer misfireInstruction) {
        this.misfireInstruction = misfireInstruction;
        return this;
    }

    public List<String> getCalendarNames() {
        return calendarNames;
    }

    public DelayJobExtSaveParam setCalendarNames(List<String> calendarNames) {
        this.calendarNames = calendarNames;
        return this;
    }

}
