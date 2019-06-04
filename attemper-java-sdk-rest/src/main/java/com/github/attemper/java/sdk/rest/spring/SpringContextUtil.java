package com.github.attemper.java.sdk.rest.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("spring_context_util")
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * get bean by T.class
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> beanClass){
        return context.getBean(beanClass);
    }

    /**
     * get bean by name
     *
     * @param name bean name
     * @return bean of object
     */
    public static Object getBean(String name) {
        return context.getBean(name);
    }

}
