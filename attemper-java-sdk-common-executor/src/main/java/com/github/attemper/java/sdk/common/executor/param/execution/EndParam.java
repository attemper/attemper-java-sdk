package com.github.attemper.java.sdk.common.executor.param.execution;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.param.execution.BaseExecutionParam;
import com.github.attemper.java.sdk.common.result.execution.TaskResult;

public class EndParam implements BaseParam {

    protected BaseExecutionParam baseExecutionParam;

    protected TaskResult taskResult;

    public BaseExecutionParam getBaseExecutionParam() {
        return baseExecutionParam;
    }

    public EndParam setBaseExecutionParam(BaseExecutionParam baseExecutionParam) {
        this.baseExecutionParam = baseExecutionParam;
        return this;
    }

    public TaskResult getTaskResult() {
        return taskResult;
    }

    public EndParam setTaskResult(TaskResult taskResult) {
        this.taskResult = taskResult;
        return this;
    }

    @Override
    public String validate() {
        if (baseExecutionParam == null) {
            return "500";
        }
        return null;
    }
}
