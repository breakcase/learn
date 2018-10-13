package com.recive.sbus.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.recive.sbus.common.BaseDO;

@Entity
@Table(name = "sbus_user")
public class User extends BaseDO{

	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userid;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "lastLoginTime")
	private Date lastLoginTime;

	@Column(name = "lastChangePWTIme")
	private Date lastChangePWTIme;

	@Column(name = "loginCount")
	private Integer loginCount = 0;// 给默认值相当于是给数据库字段设置默认值

	@Column(name = "headpic")
	private Long headpic;// 关联的是附件的主键

	/**
	 * 角色id，为系统以后做扩展用
	 */
	@Column(name = "roleId")
	private int roleId;

	/**
	 * 部门id，为系统以后做扩展用
	 */
	@Column(name = "partId")
	private int partId;

	public long getId() {
		return userid;
	}

	public void setId(long id) {
		this.userid = id;
	}

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

}
