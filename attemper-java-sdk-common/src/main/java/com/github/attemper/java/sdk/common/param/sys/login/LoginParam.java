package com.github.attemper.java.sdk.common.param.sys.login;

import com.github.attemper.java.sdk.common.param.BaseParam;
import com.github.attemper.java.sdk.common.util.StringUtils;

public class LoginParam implements BaseParam {

    protected String userName;

    protected String password;

    public String validate() {
        if(StringUtils.isBlank(userName)){
            return "5000";
        }

        if(StringUtils.isBlank(password)) {
            return "5003";
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public LoginParam setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginParam setPassword(String password) {
        this.password = password;
        return this;
    }
}
