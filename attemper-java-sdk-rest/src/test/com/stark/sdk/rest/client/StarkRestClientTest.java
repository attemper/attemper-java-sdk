/*
package com.stark.sdk.rest.client;

import com.stark.sdk.common.param.login.LoginParam;
import com.stark.sdk.common.result.CommonResult;
import com.stark.sdk.common.result.login.LoginResult;
import com.stark.sdk.common.result.user.UserInfo;
import com.stark.sdk.rest.context.StarkContext;
import com.stark.sdk.rest.handler.adapter.AfterHandlerAdapter;
import com.stark.sdk.rest.handler.adapter.PreHandlerAdapter;
import org.apache.http.HttpRequest;
import org.junit.Test;


*/
/**
 * @author ldang
 *//*

public class StarkRestClientTest {

    private static final String TENANT_ID = "stark";

    private static final String SIGN = "2fbb71b04b02738300427866d6e3181a";

    private static final String SERVICE_URL = "http://localhost:5200/";

    private static StarkRestClient starkRestClient;

    static{
        starkRestClient = StarkRestClientBuilder
                .create()
                .addServiceUrl(SERVICE_URL)
                //.serviceName(null)
                .tenantId(TENANT_ID)
                .sign(SIGN)
                .addPreHandler(new PreTestHandler())
                .addAfterHandler(new AfterTestHandler())
                .build();
    }

    @Test
    public void testLogin() {
        LoginParam param = LoginParam.builder().userName("natasha").password("1").build();
        CommonResult<LoginResult> commonResult = starkRestClient.login(param);
        System.out.println(commonResult);
    }

    @Test
    public void testUserInfo() {
        CommonResult<UserInfo> commonResult = starkRestClient.getUserInfo();
        System.out.println(commonResult);
    }

    private static class PreTestHandler extends PreHandlerAdapter {
        @Override
        public void execute(HttpRequest httpRequest, StarkContext context) {
            System.out.println("pre:" + context.getUrl());
            //System.out.println(context);
        }
    }

    private static class AfterTestHandler extends AfterHandlerAdapter {
        @Override
        public void executeOf200(StarkContext context) {
            System.out.println("after executeOf200:" + context.getUrl());
            //System.out.println(context);
        }
    }
}
*/
