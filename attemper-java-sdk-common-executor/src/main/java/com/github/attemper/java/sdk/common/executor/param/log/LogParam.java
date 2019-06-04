package com.github.attemper.java.sdk.common.executor.param.log;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.param.execution.BaseExecutionParam;
import com.github.attemper.java.sdk.common.result.execution.LogResult;
import com.github.attemper.java.sdk.common.util.StringUtils;

public class LogParam implements BaseParam {

    protected BaseExecutionParam baseExecutionParam;

    protected LogResult logResult;

    public BaseExecutionParam getBaseExecutionParam() {
        return baseExecutionParam;
    }

    public LogParam setBaseExecutionParam(BaseExecutionParam baseExecutionParam) {
        this.baseExecutionParam = baseExecutionParam;
        return this;
    }

    public LogResult getLogResult() {
        return logResult;
    }

    public LogParam setLogResult(LogResult logResult) {
        this.logResult = logResult;
        return this;
    }

    @Override
    public String validate() {
        if (baseExecutionParam == null) {
            return "500";
        }
        if (logResult == null || StringUtils.isBlank(logResult.getLogText())) {
            return "500";
        }
        return null;
    }
}
