package com.github.attemper.java.sdk.rest.conf;

import com.github.attemper.java.sdk.rest.spring.SpringContextUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        SpringContextUtil.class
})
public class RestConfiguration {
}
