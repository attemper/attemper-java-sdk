package com.github.attemper.java.sdk.micro.executor.conf;

import com.github.attemper.java.sdk.common.param.sys.login.LoginParam;
import com.github.attemper.java.sdk.micro.executor.client.ExecutorMicroClient;
import com.github.attemper.java.sdk.micro.executor.properties.ExecutorProperties;
import com.github.attemper.java.sdk.micro.properties.TenantProperties;
import com.github.attemper.java.sdk.rest.conf.RestConfiguration;
import com.github.attemper.java.sdk.rest.executor.conf.RestExecutorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        RestConfiguration.class,
        RestExecutorConfiguration.class
})
@Configuration
@EnableConfigurationProperties({
        TenantProperties.class,
        ExecutorProperties.class
})
public class ExecutorAutoConfiguration {

    @Autowired(required = false)
    private DiscoveryClient discoveryClient;

    @Autowired
    private TenantProperties tenantProperties;

    @Autowired
    private ExecutorProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public ExecutorMicroClient executorMicroClient() {
        ExecutorMicroClient client = new ExecutorMicroClient();
        client.loginParam(new LoginParam()
                .setUserName(tenantProperties.getUserName())
                .setPassword(tenantProperties.getPassword()));
        client.discoveryClient(discoveryClient);
        client.serviceName(properties.getServiceName());
        client.initialize();
        return client;
    }

}
