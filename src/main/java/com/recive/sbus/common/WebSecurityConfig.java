package com.recive.sbus.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.recive.sbus.common.config.SystemConfig;
import com.recive.sbus.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录配置
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

	public final static String SESSION_KEY = "username";

	@Autowired
	private SystemConfig systemConfig;

	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

		// 排除配置
		String notLoginUrls = systemConfig.getNotLoginURL();
		String[] arrNotLoginUrl = StringUtil.split(notLoginUrls);
		for (String notLoginUrl : arrNotLoginUrl) {
			addInterceptor.excludePathPatterns(notLoginUrl);
		}

		// 拦截地址
		addInterceptor.addPathPatterns("/**");
	}

	private class SecurityInterceptor extends HandlerInterceptorAdapter {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws IOException {
			HttpSession session = request.getSession();

			// 判断是否已有该用户登录的session
			if (session.getAttribute(SESSION_KEY) != null) {
				return true;
			}

			// 跳转到登录页
			String url = "/sbus/index";
			response.sendRedirect(url);
			return false;
		}
	}
}
