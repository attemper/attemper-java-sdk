package com.github.attemper.java.sdk.rest.executor.controller;

import com.github.attemper.java.sdk.common.executor.constant.ExecutorAPIPath;
import com.github.attemper.java.sdk.common.executor.param.router.RouterParam;
import com.github.attemper.java.sdk.rest.executor.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncRouterController {

    @Autowired
    private RouterService service;

    @PostMapping(value = ExecutorAPIPath.RouterPath.ASYNC)
    public void asyncRouter(@RequestBody RouterParam routerParam) {
        service.route(routerParam);
    }
}
