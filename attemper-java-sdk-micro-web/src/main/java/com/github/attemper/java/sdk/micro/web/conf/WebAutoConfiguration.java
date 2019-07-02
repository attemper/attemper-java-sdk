package com.github.attemper.java.sdk.micro.web.conf;

import com.github.attemper.java.sdk.common.param.sys.login.LoginParam;
import com.github.attemper.java.sdk.micro.properties.TenantProperties;
import com.github.attemper.java.sdk.micro.web.client.WebMicroClient;
import com.github.attemper.java.sdk.micro.web.properties.WebProperties;
import com.github.attemper.java.sdk.rest.conf.RestConfiguration;
import com.github.attemper.java.sdk.rest.web.conf.RestWebConfiguration;
import com.github.attemper.java.sdk.rest.web.service.DelayJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        RestConfiguration.class,
        RestWebConfiguration.class
})
@Configuration
@EnableConfigurationProperties({
        TenantProperties.class,
        WebProperties.class
})
public class WebAutoConfiguration {

    @Autowired(required = false)
    private DiscoveryClient discoveryClient;

    @Autowired
    private TenantProperties tenantProperties;

    @Autowired
    private WebProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public WebMicroClient webMicroClient() {
        WebMicroClient client = new WebMicroClient();
        client.loginParam(new LoginParam()
                .setUserName(tenantProperties.getUserName())
                .setPassword(tenantProperties.getPassword()));
        client.discoveryClient(discoveryClient);
        client.serviceName(properties.getServiceName());
        client.initialize();
        return client;
    }

    @Bean
    @ConditionalOnMissingBean
    public DelayJobService delayJobService(WebMicroClient webMicroClient) {
        DelayJobService delayJobService = new DelayJobService();
        delayJobService.setWebRestClient(webMicroClient);
        return delayJobService;
    }
}
