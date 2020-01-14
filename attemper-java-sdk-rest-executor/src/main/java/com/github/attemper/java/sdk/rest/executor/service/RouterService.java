package com.github.attemper.java.sdk.rest.executor.service;

import com.github.attemper.java.sdk.common.constant.SdkCommonConstants;
import com.github.attemper.java.sdk.common.executor.param.execution.TaskParam;
import com.github.attemper.java.sdk.common.executor.param.router.BeanParam;
import com.github.attemper.java.sdk.common.executor.param.router.RouterParam;
import com.github.attemper.java.sdk.common.result.execution.LogResult;
import com.github.attemper.java.sdk.common.util.ExceptionUtil;
import com.github.attemper.java.sdk.common.util.StringUtils;
import com.github.attemper.java.sdk.rest.executor.template.Executing;
import com.github.attemper.java.sdk.rest.executor.template.ExecutingWithParam;
import com.github.attemper.java.sdk.rest.executor.template.ExecutingWithParamAndResult;
import com.github.attemper.java.sdk.rest.executor.template.ExecutingWithResult;
import com.github.attemper.java.sdk.rest.spring.SpringContextUtil;
import com.github.attemper.java.sdk.rest.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Service
public class RouterService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public Object route(RouterParam routerParam) {
        BeanParam beanParam = routerParam.getBeanParam();
        Object bean;
        LogResult errorLogResult = new LogResult()
                .setSuccess(false)
                .setLogKey(String.valueOf(SdkCommonConstants.INTERNAL_SERVER_ERROR));
        try {
            bean = SpringContextUtil.getBean(beanParam.getBeanName());
        } catch (Exception e) {
            return toLog(errorLogResult, "get bean occurred error:%s\nexception:%s", beanParam.getBeanName(), ExceptionUtil.getStackTrace(e));
        }
        if (bean == null) {
            return toLog(errorLogResult, "bean is null:%s", beanParam.getBeanName());
        }
        String methodName = beanParam.getMethodName();
        if (StringUtils.isBlank(methodName)) {
            if ((bean instanceof ExecutingWithParam || bean instanceof ExecutingWithParamAndResult
                || bean instanceof Executing || bean instanceof ExecutingWithResult)) {
                methodName = SdkCommonConstants.execute;
            } else {
                methodName = routerParam.getMetaParam().getActId();
            }
        }
        Method method;
        try {
            method = ReflectionUtils.findMethod(bean.getClass(), methodName, TaskParam.class);
        } catch (Exception e) {
            return toLog(errorLogResult, "get method(has task param) occurred error:%s\nexception:%s", methodName, ExceptionUtil.getStackTrace(e));
        }
        if (method != null) {
            TaskParam taskExecutionParam = new TaskParam();
            Class<?> resolveClass;
            try {
                Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, AopUtils.getTargetClass(bean));
                if (mostSpecificMethod == null) {
                    return toLog(errorLogResult, "proxied method is null:%s", methodName);
                }
                ResolvableType resolvableType = ResolvableType.forMethodParameter(mostSpecificMethod, 0);
                if (resolvableType == null
                        || resolvableType.getGenerics() == null || resolvableType.getGenerics().length == 0) {
                    return toLog(errorLogResult, "can not find method:%s", methodName);
                }
                resolveClass = resolvableType.getGeneric(0).resolve();
                taskExecutionParam.setMetaParam(routerParam.getMetaParam())
                        .setBizParam(BeanUtil.map2Bean(resolveClass, routerParam.getBizParamMap()));
            } catch (Exception e) {
                return errorLogResult.setLogText(ExceptionUtil.getStackTrace(e));
            }
            try {
                return ReflectionUtils.invokeMethod(method, bean, taskExecutionParam);
            } catch (Exception e) {
                return errorLogResult.setLogText(ExceptionUtil.getStackTrace(e));
            }
        } else {
            try {
                method = ReflectionUtils.findMethod(bean.getClass(), methodName);
            } catch (Exception e) {
                return toLog(errorLogResult, "get method(no task param) occurred error:%s\nexception:%s", methodName, ExceptionUtil.getStackTrace(e));
            }
            if (method == null) {
                return toLog(errorLogResult, "method is null:%s", methodName);
            } else {
                try {
                    return ReflectionUtils.invokeMethod(method, bean);
                } catch (Exception e) {
                    return errorLogResult.setLogText(ExceptionUtil.getStackTrace(e));
                }
            }
        }
    }

    private LogResult toLog(LogResult errorLogResult, String pattern, Object... arguments) {
        String logText = String.format(pattern, arguments);
        log.error(logText);
        return errorLogResult.setLogText(logText);
    }
}
