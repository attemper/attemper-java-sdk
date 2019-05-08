package com.github.attemper.java.sdk.common.executor2biz.param;

import java.util.Map;

public class RouterParam {

    protected BeanParam beanParam;

    protected ExecutionParam executionParam;

    protected Map<String, Object> bizParamMap;

    public BeanParam getBeanParam() {
        return beanParam;
    }

    public RouterParam setBeanParam(BeanParam beanParam) {
        this.beanParam = beanParam;
        return this;
    }

    public ExecutionParam getExecutionParam() {
        return executionParam;
    }

    public RouterParam setExecutionParam(ExecutionParam executionParam) {
        this.executionParam = executionParam;
        return this;
    }

    public Map<String, Object> getBizParamMap() {
        return bizParamMap;
    }

    public RouterParam setBizParamMap(Map<String, Object> bizParamMap) {
        this.bizParamMap = bizParamMap;
        return this;
    }
}
