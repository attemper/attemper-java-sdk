package com.github.attemper.java.sdk.common.biz2executor.param.end;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.param.execution.BaseExecutionParam;
import com.github.attemper.java.sdk.common.result.execution.TaskResult;

public class EndExecutionParam implements BaseParam {

    protected BaseExecutionParam baseExecutionParam;

    protected TaskResult taskResult;

    public BaseExecutionParam getBaseExecutionParam() {
        return baseExecutionParam;
    }

    public EndExecutionParam setBaseExecutionParam(BaseExecutionParam baseExecutionParam) {
        this.baseExecutionParam = baseExecutionParam;
        return this;
    }

    public TaskResult getTaskResult() {
        return taskResult;
    }

    public EndExecutionParam setTaskResult(TaskResult taskResult) {
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
