package com.github.attemper.java.sdk.common.result;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BaseResult<T> {

	/**
	 * app code
	 */
	protected int code;

	/**
	 * success/tip/error msg
	 */
	protected String msg;

	/**
	 * response time,format is yyyy-MM-dd HH:mm:ss
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	protected Date responseTime;

	/**
	 * time unit is second
	 */
	protected String duration;

	/**
	 * data body
	 */
	protected T result;

	public BaseResult() {
		this.responseTime = new Date();
	}

	/**
	 * @param code
	 * @param msg
	 * @return
	 */
	public static BaseResult put(int code, String msg) {
		BaseResult r = new BaseResult();
		r.code = code;
		r.msg = msg;
		return r;
	}

	/**
	 * @param code
	 * @param replaceMsg
	 * @return
	 */
	public static BaseResult putWith(int code, String replaceMsg){
		return put(code, replaceMsg);
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
		return "BaseResult{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", responseTime='" + responseTime + '\'' +
				", duration='" + duration + '\'' +
				", result=" + result +
				'}';
	}
}
