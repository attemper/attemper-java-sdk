package com.github.attemper.java.sdk.common.constant;

/**
 * @author ldang
 */
public interface SdkCommonConstants {

    int OK = 200;

    int INTERNAL_SERVER_ERROR = 500;

    String APPLICATION_JSON = "application/json";

    String page = "page";

    String list = "list";

    String userName = "userName";

    String tenantId = "tenantId";

    String sign = "sign";

    String header = "header";

    String token = "token";

    String code = "code";

    String msg = "msg";

    String duration = "duration";

    String responseTime = "responseTime";

    String result = "result";

    int DEF_CURRENT_PAGE = 1;

    int DEF_PAGE_SIZE = 10;

    int MAX_PAGE_SIZE = 1000;

    String REGEX_EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
}
