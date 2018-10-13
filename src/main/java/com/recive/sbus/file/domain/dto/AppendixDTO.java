package com.recive.sbus.file.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AppendixDTO {

	private Long appendixId;

	@NotNull(message = "附件名不能为空")
	private String appFile;

	private String appFileType;// 附件类型，后期扩展

	private String appDesc;

	private Long srcAppendixId;

	private Long createUserId;

	private String createUsername;// 增加该字段是为了减少对用户信息的查询

	private Date createTime;

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
