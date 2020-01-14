package com.github.attemper.java.sdk.rest.executor.template;

import com.github.attemper.java.sdk.common.result.execution.LogResult;

public interface ExecutingWithResult {

    LogResult execute() throws Exception;
}
