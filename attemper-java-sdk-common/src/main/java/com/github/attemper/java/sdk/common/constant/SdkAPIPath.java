package com.github.attemper.java.sdk.common.constant;

/**
 * @author ldang
 */
public class SdkAPIPath {
    public static final String API_PATH = "/api";

    public static final String SYS = "/sys";

    public static final String DISPATCH = "/dispatch";

    public static final class LoginPath {
        public static final String SUB_PATH = "/login";

        public static final String $ = API_PATH + SYS + SUB_PATH;

        public static final String LOGIN_BY_ENCODED_USERNAME_PWD = $ + "/encoded";
    }
}
