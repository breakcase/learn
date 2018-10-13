package com.recive.sbus.user.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recive.sbus.common.BaseVo;
import com.recive.sbus.common.WebSecurityConfig;
import com.recive.sbus.common.config.SystemConfig;
import com.recive.sbus.common.convert.UserDOConvertDTO;
import com.recive.sbus.common.convert.UserDTOConvertDO;
import com.recive.sbus.common.util.FileUtil;
import com.recive.sbus.file.controller.FileController;
import com.recive.sbus.file.domain.Appendix;
import com.recive.sbus.file.service.AppendixService;
import com.recive.sbus.user.domain.User;
import com.recive.sbus.user.domain.dto.UserDTO;
import com.recive.sbus.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SystemConfig systemConfig;

	@Autowired
	private AppendixService appendixService;

	@Autowired
	private UserDOConvertDTO userDOConvertDTO;

	@Autowired
	private UserDTOConvertDO userDTOConvertDO;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/error")
	public String error() {
		return "error";
	}

	@ResponseBody
	@RequestMapping(value = "/getUser", produces = { "application/json;charset=UTF-8" })
	public BaseVo<User> regist(HttpSession session) {
		BaseVo<User> vo = new BaseVo<User>();
		// 从session中获取当前登录用户
		Object username = session.getAttribute(WebSecurityConfig.SESSION_KEY);
		if (null == username) {
			vo.setFailed("用户未登录！");
		} else {
			User oUser = userService.getUser((String) username);
			if (null == oUser) {
				vo.setFailed("获取用户信息失败");
			}
			vo.setDatas(oUser);
		}

		return vo;
	}

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userid
	 * @param model
	 * @return
	 */
	@GetMapping("/getUserInfo")
	public String getUserInfo(long userid, Model model) {
		User oUser = userService.getUser(userid);
		// 处理用户头像
		Long headpic = oUser.getHeadpic();
		String sHeadPic = systemConfig.getServerHost() + File.separator + "images/default_head.jpg";
		if (null != headpic) {
			Appendix oAppendix = appendixService.getAppendix(headpic);
			sHeadPic = systemConfig.getServerHost() + File.separator
					+ FileUtil.getFilePathByFileName(oAppendix.getAppFile());
		}
		UserDTO userDTO = userDOConvertDTO.convert(oUser);
		userDTO.setSrcHeadPic(sHeadPic);
		model.addAttribute(userDTO);
		return "user/userinfo";
	}

	/**
	 * 更新用户信息
	 * 
	 * @param oUserDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/updateUserInfo")
	public BaseVo<User> updateUserInfo(UserDTO oUserDTO) {
		BaseVo<User> vo = new BaseVo<User>();
		// User oUser = userDTOConvertDO.convert(oUserDTO);
		// 由于spring-data的机制 这里得先从数据库中查出对象，再进行更新，否者DTO中没有值的属性，在数据库都会被置空
		User oUser = userService.getUser(oUserDTO.getUserid());
		try {
			oUser.updateProperties(oUserDTO, oUser);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("DTO属性传递给DO出错！");
		}

		vo.setDatas(userService.updateUser(oUser));
		return vo;
	}

	@GetMapping("test")
	public String test() {
		return "file/uploadDemo";
	}

}
