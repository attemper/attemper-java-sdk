package com.github.attemper.java.sdk.rest.executor.conf;

import com.github.attemper.java.sdk.rest.executor.controller.AsyncRouterController;
import com.github.attemper.java.sdk.rest.executor.controller.SyncRouterController;
import com.github.attemper.java.sdk.rest.executor.service.RouterService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        SyncRouterController.class,
        AsyncRouterController.class,
        RouterService.class
})
public class RestExecutorConfiguration {
}
