package com.recive.sbus.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recive.sbus.common.BaseVo;
import com.recive.sbus.common.WebSecurityConfig;
import com.recive.sbus.common.util.EncryptionAndDecryptUtil;
import com.recive.sbus.common.util.StringUtil;
import com.recive.sbus.login.service.LoginService;
import com.recive.sbus.user.domain.User;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/")
	public String defaultpage() {
		return "index";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changepwd", produces = { "application/json;charset=UTF-8" })
	public BaseVo<Object> changePassword(String username, String password, String newPwd, String conNewPwd,
			HttpSession session) {

		BaseVo<Object> vo = new BaseVo<Object>();
		// 1校验参数
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password) || StringUtil.isEmpty(newPwd)
				|| StringUtil.isEmpty(conNewPwd)) {
			vo.setFailed("用户名/原密码/新密码都不能为空！");
			return vo;
		}
		if (!newPwd.equals(conNewPwd)) {
			vo.setFailed("新密码和确认新密码不同！");
			return vo;
		}

		// 2校验原密码是否正确
		User user = new User();
		user.setUsername(username);
		String pwd = EncryptionAndDecryptUtil.getMD5(password);
		user.setPassword(pwd);

		boolean verify = loginService.verifyLogin(user);
		if (!verify) {
			vo.setFailed("原密码不正确，不能修改密码！");
			return vo;
		}

		// 3修改密码
		boolean isChanged = loginService.changePwd(username, EncryptionAndDecryptUtil.getMD5(newPwd));

		if (!isChanged) {
			vo.setFailed("更改密码出错！");
		} else {// 修改成功 清空session中的用户信息
			session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		}
		return vo;
	}

}
