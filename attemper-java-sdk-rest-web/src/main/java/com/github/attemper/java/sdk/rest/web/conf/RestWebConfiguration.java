package com.github.attemper.java.sdk.rest.web.conf;

import com.github.attemper.java.sdk.rest.web.service.DelayJobService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        DelayJobService.class
})
public class RestWebConfiguration {
}
