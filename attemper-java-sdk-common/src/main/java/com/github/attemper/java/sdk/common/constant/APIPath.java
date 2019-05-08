package com.github.attemper.java.sdk.common.constant;

/**
 * @author ldang
 */
public class APIPath {
    public static final String API_PATH = "/api";

    private static final String SYS = "/sys";

    public static final String _SAVE = "";

    public static final String _ADD = "";

    public static final String _UPDATE = "";

    public static final String _LIST = "";

    public static final String _REMOVE = "";

    public static final String _GET = "/get";

    public static final class Login {
        public static final String LOGIN = "/login";

        public static final String LOGIN_BY_USERNAME_PWD = API_PATH + SYS + LOGIN;
    }

    public static final class Tenant {
        public static final String TENANT = "/tenant";

        public static final String LIST = API_PATH + SYS + TENANT + _LIST;

        public static final String ADD = API_PATH + SYS + TENANT + _ADD;

        public static final String UPDATE = API_PATH + SYS + TENANT + _UPDATE;

        public static final String REMOVE = API_PATH + SYS + TENANT + _REMOVE;

        public static final String GET = API_PATH + SYS + TENANT + _GET;
    }

    public static final class User {
        public static final String USER = "/user";

        public static final String LIST = API_PATH + SYS + USER + _LIST;

        public static final String ADD = API_PATH + SYS + USER + _ADD;

        public static final String UPDATE = API_PATH + SYS + USER + _UPDATE;

        public static final String REMOVE = API_PATH + SYS + USER + _REMOVE;

        public static final String GET = API_PATH + SYS + USER + _GET;

        public static final String TAG_LIST = API_PATH + SYS + USER + Tag.TAG + _LIST;

        public static final String TAG_UPDATE = API_PATH + SYS + USER + Tag.TAG + _UPDATE;

        public static final String INFO = API_PATH + SYS + USER + "/info";

        public static final String ADMIN_INFO = API_PATH + SYS + USER + "/adminInfo";
    }

    public static final class Tag {
        public static final String TAG = "/tag";

        public static final String LIST = API_PATH + SYS + TAG + _LIST;

        public static final String ADD = API_PATH + SYS + TAG + _ADD;

        public static final String UPDATE = API_PATH + SYS + TAG + _UPDATE;

        public static final String REMOVE = API_PATH + SYS + TAG + _REMOVE;

        public static final String GET = API_PATH + SYS + TAG + _GET;

        public static final String USER_LIST = API_PATH + SYS + TAG + User.USER + _LIST;

        public static final String RESOURCE_LIST = API_PATH + SYS + TAG + Resource.RESOURCE + _LIST;

        public static final String USER_UPDATE = API_PATH + SYS + TAG + User.USER + _UPDATE;

        public static final String RESOURCE_UPDATE = API_PATH + SYS + TAG + Resource.RESOURCE + _UPDATE;
    }

    public static final class Resource {
        public static final String RESOURCE = "/resource";

        public static final String LIST = API_PATH + SYS + RESOURCE + _LIST;

        public static final String ADD = API_PATH + SYS + RESOURCE + _ADD;

        public static final String UPDATE = API_PATH + SYS + RESOURCE + _UPDATE;

        public static final String SAVE = API_PATH + SYS + RESOURCE + _SAVE;

        public static final String REMOVE = API_PATH + SYS + RESOURCE + _REMOVE;

        public static final String GET = API_PATH + SYS + RESOURCE + _GET;

        public static final String TREE_LIST = API_PATH + SYS + RESOURCE + "/treeList";
    }

    public static final class Token {
        public static final String TOKEN = "/token";

        public static final String REFRESH = API_PATH + SYS + TOKEN + "/refresh";
    }

}
