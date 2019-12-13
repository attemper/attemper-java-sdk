package com.github.attemper.java.sdk.common.result;

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
	 * response time in timestamp
	 */
	protected Long handleTime;

	/**
	 * time unit is second
	 */
	protected String duration;

	/**
	 * data body
	 */
	protected T result;

	public BaseResult() {
		this.handleTime = System.currentTimeMillis();
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

	public Long getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Long handleTime) {
		this.handleTime = handleTime;
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
				", handleTime='" + handleTime + '\'' +
				", duration='" + duration + '\'' +
				", result=" + result +
				'}';
	}
}
