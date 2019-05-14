package com.github.attemper.java.sdk.common.executor2biz.constant;

import com.github.attemper.java.sdk.common.constant.SdkAPIPath;

public final class Executor2BizAPIPath {

    private static final String BASE_EXECUTOR2BIZ_PATH = SdkAPIPath.API_PATH + "/dispatch";

    private static final String BASE_ROUTER_PATH = "/router";

    public static final String ROUTER_PATH_SYNC = BASE_EXECUTOR2BIZ_PATH + BASE_ROUTER_PATH + "/sync";

    public static final String ROUTER_PATH_ASYNC = BASE_EXECUTOR2BIZ_PATH + BASE_ROUTER_PATH + "/async";

}
