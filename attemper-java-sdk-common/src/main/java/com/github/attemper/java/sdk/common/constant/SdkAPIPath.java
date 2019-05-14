package com.github.attemper.java.sdk.common.constant;

/**
 * @author ldang
 */
public class SdkAPIPath {
    public static final String API_PATH = "/api";

    public static final String SYS = "/sys";

    public static final String _SAVE = "";

    public static final String _ADD = "";

    public static final String _UPDATE = "";

    public static final String _LIST = "";

    public static final String _REMOVE = "";

    public static final String _GET = "/get";

    public static final class LoginPath {
        public static final String SUB_PATH = "/login";

        public static final String LOGIN_BY_USERNAME_PWD = API_PATH + SYS + SUB_PATH;
    }
}
