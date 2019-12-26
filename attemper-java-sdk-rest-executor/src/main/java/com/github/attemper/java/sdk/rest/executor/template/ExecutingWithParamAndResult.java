package com.github.attemper.java.sdk.rest.executor.template;

import com.github.attemper.java.sdk.common.executor.param.execution.TaskParam;
import com.github.attemper.java.sdk.common.result.execution.LogResult;

public interface ExecutingWithParamAndResult<T> {

    LogResult execute(TaskParam<T> taskParam) throws Exception;
}
