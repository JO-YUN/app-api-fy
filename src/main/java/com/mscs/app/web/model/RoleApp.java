package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class RoleApp extends BIZModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6951771854486723518L;

	private String appId;

	private String roleId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

}