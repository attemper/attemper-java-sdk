package com.github.attemper.java.sdk.common.executor2biz.param;

public class BeanParam {

    protected String beanName;

    protected String methodName;

    public String getBeanName() {
        return beanName;
    }

    public BeanParam setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public BeanParam setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }
}
