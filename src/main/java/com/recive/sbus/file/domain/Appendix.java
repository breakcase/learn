package com.recive.sbus.file.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sbus_appendix")
public class Appendix {

	@Id
	@Column(name = "appendixId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long appendixId;

	@Column(name = "appFile")
	private String appFile;

	@Column(name = "appFileType")
	private String appFileType;// 附件类型，后期扩展

	@Column(name = "appDesc")
	private String appDesc;

	@Column(name = "srcAppendixId")
	private Long srcAppendixId;

	@Column(name = "createUserId")
	private Long createUserId;

	@Column(name = "createUsername")
	private String createUsername;// 增加该字段是为了减少对用户信息的查询

	@Column(name = "createTime")
	private Date createTime;

	@Column(name = "state")
	private Integer state = 0;// 负数为删除，暂时定义

	public Long getAppendixId() {
		return appendixId;
	}

	public void setAppendixId(Long appendixId) {
		this.appendixId = appendixId;
	}

	public String getAppFile() {
		return appFile;
	}

	public void setAppFile(String appFile) {
		this.appFile = appFile;
	}

	public String getAppFileType() {
		return appFileType;
	}

	public void setAppFileType(String appFileType) {
		this.appFileType = appFileType;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public Long getSrcAppendixId() {
		return srcAppendixId;
	}

	public void setSrcAppendixId(Long srcAppendixId) {
		this.srcAppendixId = srcAppendixId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
