package com.github.attemper.java.sdk.common.executor.param.execution;

public class TaskParam<T> {

    protected MetaParam metaParam;

    protected T bizParam;

    public MetaParam getMetaParam() {
        return metaParam;
    }

    public TaskParam<T> setMetaParam(MetaParam metaParam) {
        this.metaParam = metaParam;
        return this;
    }

    public T getBizParam() {
        return bizParam;
    }

    public TaskParam<T> setBizParam(T bizParam) {
        this.bizParam = bizParam;
        return this;
    }
}
