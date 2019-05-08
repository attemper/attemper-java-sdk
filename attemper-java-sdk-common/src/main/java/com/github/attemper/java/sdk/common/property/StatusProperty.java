package com.github.attemper.java.sdk.common.property;

import com.github.attemper.java.sdk.common.constant.SdkGlobalConstants;

import java.util.ResourceBundle;

public class StatusProperty {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(
            SdkGlobalConstants.statusPropertiesName, SdkGlobalConstants.locale);

    public static String getValue(int key) {
        return getValue(String.valueOf(key));
    }

    /**
     * 没有value就返回key
     * @param key
     * @return
     */
    public static String getValue(String key) {
        try{
            return resourceBundle.getString(key);
        }catch (RuntimeException e) {
            return key;
        }
    }
}
