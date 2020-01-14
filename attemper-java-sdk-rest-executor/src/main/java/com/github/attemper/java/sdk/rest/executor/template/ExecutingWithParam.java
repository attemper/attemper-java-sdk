package com.github.attemper.java.sdk.rest.executor.template;

import com.github.attemper.java.sdk.common.executor.param.execution.TaskParam;

public interface ExecutingWithParam<T> {

    void execute(TaskParam<T> taskParam) throws Exception;
}
