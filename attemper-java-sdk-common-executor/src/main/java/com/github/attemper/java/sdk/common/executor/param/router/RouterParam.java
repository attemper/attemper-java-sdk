package com.github.attemper.java.sdk.common.executor.param.router;

import com.github.attemper.java.sdk.common.executor.param.execution.MetaParam;

import java.util.Map;

public class RouterParam {

    protected BeanParam beanParam;

    protected MetaParam metaParam;

    protected Map<String, Object> bizParamMap;

    public BeanParam getBeanParam() {
        return beanParam;
    }

    public RouterParam setBeanParam(BeanParam beanParam) {
        this.beanParam = beanParam;
        return this;
    }

    public MetaParam getMetaParam() {
        return metaParam;
    }

    public RouterParam setMetaParam(MetaParam metaParam) {
        this.metaParam = metaParam;
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
