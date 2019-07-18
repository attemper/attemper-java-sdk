package com.github.attemper.java.sdk.common.executor.constant;

import com.github.attemper.java.sdk.common.constant.SdkAPIPath;

public class ExecutorAPIPath {

    public static final String SIGNAL = SdkAPIPath.API_PATH + "/signal";

    //public static final String APPEND_LOG = SdkAPIPath.API_PATH + "/log/append";

    public static final class RouterPath {
        private static final String SUB_PATH = "/router";

        public static final String SYNC = SdkAPIPath.API_PATH + SUB_PATH + "/sync";

        public static final String ASYNC = SdkAPIPath.API_PATH + SUB_PATH + "/async";
    }

}
