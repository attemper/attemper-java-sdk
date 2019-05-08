package com.github.attemper.java.sdk.common.executor2biz.param;

public class TaskExecutionParam<T> {

    protected ExecutionParam executionParam;

    protected T bizParam;

    public ExecutionParam getExecutionParam() {
        return executionParam;
    }

    public TaskExecutionParam<T> setExecutionParam(ExecutionParam executionParam) {
        this.executionParam = executionParam;
        return this;
    }

    public T getBizParam() {
        return bizParam;
    }

    public TaskExecutionParam<T> setBizParam(T bizParam) {
        this.bizParam = bizParam;
        return this;
    }
}
