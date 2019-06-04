package com.github.attemper.java.sdk.rest.executor.service;

import com.github.attemper.java.sdk.common.executor.param.execution.TaskParam;
import com.github.attemper.java.sdk.common.executor.param.router.BeanParam;
import com.github.attemper.java.sdk.common.executor.param.router.RouterParam;
import com.github.attemper.java.sdk.rest.spring.SpringContextUtil;
import com.github.attemper.java.sdk.rest.util.BeanUtil;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Service
public class RouterService {

    public Object route(RouterParam routerParam) {
        BeanParam beanParam = routerParam.getBeanParam();
        Object bean = SpringContextUtil.getBean(beanParam.getBeanName());
        Method method = ReflectionUtils.findMethod(bean.getClass(), beanParam.getMethodName(), TaskParam.class);
        TaskParam taskExecutionParam = new TaskParam();
        taskExecutionParam.setMetaParam(routerParam.getMetaParam())
                .setBizParam(BeanUtil.map2Bean(antiProxy(bean, method), routerParam.getBizParamMap()));
        return ReflectionUtils.invokeMethod(method, bean, taskExecutionParam);
    }

    /**
     * get actual method anti proxy
     * @param bean
     * @param method
     * @return
     */
    private Class<?> antiProxy(Object bean, Method method) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, AopUtils.getTargetClass(bean));
        ResolvableType resolvableType = ResolvableType.forMethodParameter(mostSpecificMethod, 0);
        return resolvableType.getGeneric(0).resolve();
    }
}
