package com.github.attemper.java.sdk.common.executor.constant;

import com.github.attemper.java.sdk.common.constant.SdkAPIPath;

public class ExecutorAPIPath {

    public static final String SIGNAL = SdkAPIPath.API_PATH + "/signal";

    public static final String APPEND_LOG = SdkAPIPath.API_PATH + "/log/append";

    private static final String BASE_ROUTER_PATH = "/router";

    public static final String ROUTER_PATH_SYNC = SdkAPIPath.API_PATH + BASE_ROUTER_PATH + "/sync";

    public static final String ROUTER_PATH_ASYNC = SdkAPIPath.API_PATH + BASE_ROUTER_PATH + "/async";
}
