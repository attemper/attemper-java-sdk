package com.github.attemper.java.sdk.common.result;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 返回数据封装类
 * @auth ldang
 */
public class BaseResult<T> implements Serializable {
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
	 * 响应时刻，字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	protected Date responseTime;

	/**
	 * 处理耗时，单位秒
	 */
	protected String duration;

	/**
	 * 数据实体
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
