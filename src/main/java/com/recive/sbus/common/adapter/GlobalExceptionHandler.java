package com.recive.sbus.common.adapter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recive.sbus.common.BaseVo;
import com.recive.sbus.common.exception.JSONException;
import com.recive.sbus.common.exception.WebException;

@ControllerAdvice
class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 直接跳转错误页面
	 * 
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = WebException.class)
	public String webErrorHandler(HttpServletRequest req, WebException e) throws Exception {
		System.out.println("123");
		return "/excerror";
	}

	@ExceptionHandler(value = JSONException.class)
	@ResponseBody
	public BaseVo<Object> jsonErrorHandler(HttpServletRequest req, JSONException e) throws Exception {
		BaseVo<Object> vo = new BaseVo<Object>();
		vo.setFailed("系统错误：" + e.getMessage());
		return vo;
	}

	@ExceptionHandler(value = BindException.class)
	@ResponseBody
	public BaseVo<Object> bindExceptionHandler(HttpServletRequest req, BindException e) throws Exception {
		BaseVo<Object> vo = new BaseVo<Object>();
		List<ObjectError> errorList = e.getAllErrors();
		StringBuffer errormsg = new StringBuffer();
		for (ObjectError error : errorList) {
			errormsg.append(error.getDefaultMessage() + "!");
		}
		vo.setFailed(errormsg.toString());
		return vo;
	}

	@ExceptionHandler(value = Exception.class)
	public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		System.out.println("系统错误：未知错误");
		return "/excerror";
	}

}
