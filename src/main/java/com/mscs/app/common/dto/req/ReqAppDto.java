package com.mscs.app.common.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class ReqAppDto {

	private String token;

	// @NotBlank(message = "用户ID为空")
	private String uid;

	@NotBlank(message = "应用ID为空")
	private String appId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
