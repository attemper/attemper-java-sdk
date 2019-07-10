package com.github.attemper.java.sdk.common.param.execution;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.util.StringUtils;

public class BaseExecutionParam implements BaseParam {

    protected String requestPath;

    protected String executionId;

    protected String actInstId;

    public String getRequestPath() {
        return requestPath;
    }

    public BaseExecutionParam setRequestPath(String requestPath) {
        this.requestPath = requestPath;
        return this;
    }

    public String getExecutionId() {
        return executionId;
    }

    public BaseExecutionParam setExecutionId(String executionId) {
        this.executionId = executionId;
        return this;
    }

    public String getActInstId() {
        return actInstId;
    }

    public BaseExecutionParam setActInstId(String actInstId) {
        this.actInstId = actInstId;
        return this;
    }

    @Override
    public String validate() {
        if (StringUtils.isBlank(actInstId)) {
            return "500";
        }
        return null;
    }
}
