package com.github.attemper.java.sdk.common.result;

import com.github.attemper.java.sdk.common.constant.SdkCommonConstants;
import com.github.attemper.java.sdk.common.exception.RTException;
import com.github.attemper.java.sdk.common.property.StatusProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 返回数据封装类
 * @auth ldang
 */
public class CommonResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	protected int code;

	/**
	 * 状态信息
	 */
	protected String msg;

	/**
	 * 响应时刻，字符串格式 yyyy-MM-dd HH:mm:ss SSS
	 */
	protected Date responseTime;

	/**
	 * 处理耗时，单位秒
	 */
	protected String duration;

	/**
	 * 数据实体
	 */
	protected T result;
	
	public CommonResult() {
		this.responseTime = new Date();
	}

    /**
     * 使用code和msg构造Res，此方法私有，通过定义ResStatus来处理
     * @param code
     * @param msg
     * @return
     */
    public static CommonResult put(int code, String msg) {
        CommonResult r = new CommonResult();
        r.code = code;
        r.msg = msg;
        return r;
    }

    /**
     * @param code
     * @return
     */
	public static CommonResult put(int code) {
		return put(code,
				StatusProperty.getValue(code));
	}

    /**
     * @param code
     * @param replaceMsg
     * @return
     */
	public static CommonResult putWith(int code, String replaceMsg){
	    return put(code, replaceMsg);
    }

    /**
     * 将额外信息附加在msg中
     * @param code
     * @param extraMsg
     * @return
     */
	public static CommonResult putAdd(int code, String extraMsg){
	    return put(code,
				StatusProperty.getValue(code)
						+ ":" +
						StatusProperty.getValue(extraMsg));
    }

    /**
     * 最简单的调用成功Res
     * @return
     */
	public static CommonResult ok() {
		return put(SdkCommonConstants.OK);
	}

    /**
     * 替换"调用成功"
     * @param msg
     * @return
     */
	public static CommonResult ok(String msg) {
	    return putWith(SdkCommonConstants.OK, msg);
	}

    /**
     * 调用成功且附带返回数据
     * @param t
     * @param <T>
     * @return
     */
	public static <T> CommonResult<T> putResult(T t) {
		CommonResult r = ok();
		r.result = t;
		return r;
	}

    /**
     * code=500
     * @param msg
     * @return
     */
	public static CommonResult error(String msg){
        return CommonResult.putAdd(SdkCommonConstants.INTERNAL_SERVER_ERROR, msg);
    }

    /**
     * RTException -> CommonResult
     * @param rte
     * @return
     */
	public static CommonResult error(RTException rte){
	    return CommonResult.put(rte.getCode(), rte.getMsg());
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "CommonResult{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", responseTime='" + responseTime + '\'' +
				", duration='" + duration + '\'' +
				", result=" + result +
				'}';
	}
}
