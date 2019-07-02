package com.github.attemper.java.sdk.common.web.param.delay;

import java.util.Date;

public class DelayJobExtSaveParam {

    protected String id;

    protected String jobName;

    protected Date startTime;

    protected Integer interval;

    protected Date endTime;

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

    public Date getStartTime() {
        return startTime;
    }

    public DelayJobExtSaveParam setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Integer getInterval() {
        return interval;
    }

    public DelayJobExtSaveParam setInterval(Integer interval) {
        this.interval = interval;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public DelayJobExtSaveParam setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }
}
