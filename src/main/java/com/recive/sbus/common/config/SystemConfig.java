package com.recive.sbus.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sbus")
@PropertySource(value = "classpath:config/config.properties")
public class SystemConfig {
	String fileUpload;

	String notLoginURL;

	String blackIPList;

	String whiteIPList;

	String serverHost;

	public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getNotLoginURL() {
		return notLoginURL;
	}

	public void setNotLoginURL(String notLoginURL) {
		this.notLoginURL = notLoginURL;
	}

	public String getBlackIPList() {
		return blackIPList;
	}

	public void setBlackIPList(String blackIPList) {
		this.blackIPList = blackIPList;
	}

	public String getWhiteIPList() {
		return whiteIPList;
	}

	public void setWhiteIPList(String whiteIPList) {
		this.whiteIPList = whiteIPList;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

}
