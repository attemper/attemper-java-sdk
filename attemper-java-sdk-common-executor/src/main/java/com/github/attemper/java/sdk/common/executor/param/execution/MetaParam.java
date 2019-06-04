package com.github.attemper.java.sdk.common.executor.param.execution;

import com.github.attemper.java.sdk.common.param.execution.BaseExecutionParam;

public class MetaParam extends BaseExecutionParam {

    protected String parentActInstId;

    protected String executionId;

    protected String procInstId;

    protected String jobName;

    protected String actId;

    protected String actName;

    public String getParentActInstId() {
        return parentActInstId;
    }

    public MetaParam setParentActInstId(String parentActInstId) {
        this.parentActInstId = parentActInstId;
        return this;
    }

    public String getExecutionId() {
        return executionId;
    }

    public MetaParam setExecutionId(String executionId) {
        this.executionId = executionId;
        return this;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public MetaParam setProcInstId(String procInstId) {
        this.procInstId = procInstId;
        return this;
    }

    public String getJobName() {
        return jobName;
    }

    public MetaParam setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public String getActId() {
        return actId;
    }

    public MetaParam setActId(String actId) {
        this.actId = actId;
        return this;
    }

    public String getActName() {
        return actName;
    }

    public MetaParam setActName(String actName) {
        this.actName = actName;
        return this;
    }
}
