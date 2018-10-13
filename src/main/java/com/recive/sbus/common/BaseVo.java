package com.recive.sbus.common;

public class BaseVo<T> {
	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 备注信息
	 */
	private String msg;

	/**
	 * 数据域
	 */
	private T datas;

	public BaseVo() {
		this.code = CommonConstants.SUCCESSED_CODE;
		this.msg = CommonConstants.SUCCESSED_MSG;
	}

	public BaseVo(String code, String msg, T datas) {
		this.code = code;
		this.msg = msg;
		this.datas = datas;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}

	/**
	 * 快速设置接口调用错误信息
	 * 
	 * @param msg
	 */
	public void setFailed(String msg) {
		this.code = CommonConstants.FAILED_CODE;
		this.msg = msg;
	}

	/**
	 * 快速设置接口调用正确信息
	 * 
	 * @param msg
	 */
	public void setSuccessed(String msg) {
		this.code = CommonConstants.SUCCESSED_CODE;
		this.msg = msg;
	}

	/**
	 * 是否成功
	 * 
	 * @return
	 */
	public boolean isSuccessed() {
		if (this.getCode().equals(CommonConstants.SUCCESSED_CODE)) {
			return true;
		}
		return false;
	}

}
