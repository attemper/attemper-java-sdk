package com.github.attemper.java.sdk.common.web.param.delay;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.util.StringUtils;

import java.util.Date;
import java.util.List;

public class DelayJobExtSaveParam implements BaseParam {

    protected String id;

    protected String jobName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date startTime;

    protected Integer interval;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date endTime;

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
