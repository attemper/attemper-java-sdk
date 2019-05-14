package com.github.attemper.java.sdk.common.executor2biz.param;

import com.github.attemper.java.sdk.common.param.execution.BaseExecutionParam;

public class ExecutionParam extends BaseExecutionParam {

    protected String parentActInstId;

    protected String executionId;

    protected String procInstId;

    protected String jobName;

    protected String actId;

    protected String actName;

    public String getParentActInstId() {
        return parentActInstId;
    }

    public ExecutionParam setParentActInstId(String parentActInstId) {
        this.parentActInstId = parentActInstId;
        return this;
    }

    public String getExecutionId() {
        return executionId;
    }

    public ExecutionParam setExecutionId(String executionId) {
        this.executionId = executionId;
        return this;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public ExecutionParam setProcInstId(String procInstId) {
        this.procInstId = procInstId;
        return this;
    }

    public String getJobName() {
        return jobName;
    }

    public ExecutionParam setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public String getActId() {
        return actId;
    }

    public ExecutionParam setActId(String actId) {
        this.actId = actId;
        return this;
    }

    public String getActName() {
        return actName;
    }

    public ExecutionParam setActName(String actName) {
        this.actName = actName;
        return this;
    }
}
