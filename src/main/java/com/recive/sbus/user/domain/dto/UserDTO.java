package com.recive.sbus.user.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

	private long userid;

	@NotNull(message = "用户名不能为空")
	private String username;

	@NotNull(message = "密码不能为空")
	private String password;

	@NotNull(message = "再次输入密码不能为空")
	private String confirmpassword;

	private String nickname;

	@NotNull(message = "电话号码不能为空")
	@Size(max = 11, min = 11, message = "电话号码只能是11位")
	private String phoneNumber;

	@Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "请输入正确的邮箱")
	private String email;

	private Date lastLoginTime;

	private Date lastChangePWTIme;

	private Integer loginCount;
	
	private Long headpic;
	
	private String srcHeadPic;
	
	/**
	 * 角色id，为系统以后做扩展用
	 */
	private int roleId;

	/**
	 * 部门id，为系统以后做扩展用
	 */
	private int partId;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastChangePWTIme() {
		return lastChangePWTIme;
	}

	public void setLastChangePWTIme(Date lastChangePWTIme) {
		this.lastChangePWTIme = lastChangePWTIme;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Long getHeadpic() {
		return headpic;
	}

	public void setHeadpic(Long headpic) {
		this.headpic = headpic;
	}

	public String getSrcHeadPic() {
		return srcHeadPic;
	}

	public void setSrcHeadPic(String srcHeadPic) {
		this.srcHeadPic = srcHeadPic;
	}

}
