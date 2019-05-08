package com.github.attemper.java.sdk.common.param;

/**
 * @author ldang
 */
public interface CommonParam {

    /**
     * 在hibernate-validator无法校验时，做补充校验
     * @return
     */
    String validate();

    /**
     * 预处理参数
     */
    void preHandle();

}
