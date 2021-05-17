package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class UserApp extends BIZModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6951771854486723518L;

	private String appId;

	private String userId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserApp(String appId, String userId) {
		super();
		this.appId = appId;
		this.userId = userId;
	}

	public UserApp() {
		super();
	}

}