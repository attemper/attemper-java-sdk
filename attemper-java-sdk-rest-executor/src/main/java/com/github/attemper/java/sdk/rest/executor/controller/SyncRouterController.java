package com.github.attemper.java.sdk.rest.executor.controller;

import com.github.attemper.java.sdk.common.executor.constant.ExecutorAPIPath;
import com.github.attemper.java.sdk.common.executor.param.router.RouterParam;
import com.github.attemper.java.sdk.common.result.execution.TaskResult;
import com.github.attemper.java.sdk.rest.executor.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncRouterController {

    @Autowired
    private RouterService service;

    @PostMapping(value = ExecutorAPIPath.RouterPath.SYNC)
    public TaskResult syncRouter(@RequestBody RouterParam routerParam) {
        return (TaskResult) service.route(routerParam);
    }
}
