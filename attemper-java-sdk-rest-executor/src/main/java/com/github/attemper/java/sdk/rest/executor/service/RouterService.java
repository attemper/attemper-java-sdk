package com.github.attemper.java.sdk.rest.executor.service;

import com.github.attemper.java.sdk.common.constant.SdkCommonConstants;
import com.github.attemper.java.sdk.common.executor.param.execution.TaskParam;
import com.github.attemper.java.sdk.common.executor.param.router.BeanParam;
import com.github.attemper.java.sdk.common.executor.param.router.RouterParam;
import com.github.attemper.java.sdk.common.result.execution.LogResult;
import com.github.attemper.java.sdk.rest.spring.SpringContextUtil;
import com.github.attemper.java.sdk.rest.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.text.MessageFormat;

@Service
public class RouterService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public Object route(RouterParam routerParam) {
        BeanParam beanParam = routerParam.getBeanParam();
        Object bean = SpringContextUtil.getBean(beanParam.getBeanName());
        LogResult errorLogResult = new LogResult()
                .setSuccess(false)
                .setLogKey(String.valueOf(SdkCommonConstants.INTERNAL_SERVER_ERROR));
        if (bean == null) {
            return toLog(errorLogResult, "bean is null:{}", beanParam.getBeanName());
        }
        Method method = ReflectionUtils.findMethod(bean.getClass(), beanParam.getMethodName(), TaskParam.class);
        if (method == null) {
            return toLog(errorLogResult, "method is null:{}", beanParam.getMethodName());
        }
        TaskParam taskExecutionParam = new TaskParam();
        Class<?> resolveClass;
        try {
            Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, AopUtils.getTargetClass(bean));
            if (mostSpecificMethod == null) {
                return toLog(errorLogResult, "proxied method is null:{}", beanParam.getMethodName());
            }
            ResolvableType resolvableType = ResolvableType.forMethodParameter(mostSpecificMethod, 0);
            if (resolvableType == null
                    || resolvableType.getGenerics() == null || resolvableType.getGenerics().length == 0) {
                return toLog(errorLogResult, "can not find method:{}", beanParam.getMethodName());
            }
            resolveClass = resolvableType.getGeneric(0).resolve();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return errorLogResult.setLogText(e.getMessage());
        }
        taskExecutionParam.setMetaParam(routerParam.getMetaParam())
                .setBizParam(BeanUtil.map2Bean(resolveClass, routerParam.getBizParamMap()));
        return ReflectionUtils.invokeMethod(method, bean, taskExecutionParam);
    }

    private LogResult toLog(LogResult errorLogResult, String pattern, Object... arguments) {
        String logText = MessageFormat.format(pattern, arguments);
        log.error(logText);
        return errorLogResult.setLogText(logText);
    }
}
