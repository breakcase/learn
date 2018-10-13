package com.recive.sbus.vehicle.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sbus_bus")
public class Bus {

	@Id
	@Column(name = "busid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long busid;

	@Column(name = "busNo")
	@NotNull
	private String busNo;// 车牌号

	@Column(name = "licenseNo")
	@NotNull
	private String licenseNo;// 行驶证编号

	@Column(name = "holder")
	@NotNull
	private String holder;// 车辆所有人

	@Column(name = "holderPhone")
	@NotNull
	private String holderPhone;// 所有人联系方式

	@Column(name = "nuclearLoadNum")
	@NotNull
	private int nuclearLoadNum;// 核载人数

	public long getBusid() {
		return busid;
	}

	public void setBusid(long busid) {
		this.busid = busid;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getHolderPhone() {
		return holderPhone;
	}

	public void setHolderPhone(String holderPhone) {
		this.holderPhone = holderPhone;
	}

	public int getNuclearLoadNum() {
		return nuclearLoadNum;
	}

	public void setNuclearLoadNum(int nuclearLoadNum) {
		this.nuclearLoadNum = nuclearLoadNum;
	}

}
