package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

/**
 * token表对应
 * 
 * @author hechunyang
 * @date 2018年3月13日
 * @remark TODO
 */
public class Token extends BIZModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3304363722100613253L;

	/**
	 * 学工号
	 */
	private String userId;

	/**
	 * 关联token
	 */
	private String token;

	/**
	 * 修改日期
	 */
	private String modifyDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}


	public Token(String userId, String token, String modifyDate) {
		super();
		this.userId = userId;
		this.token = token;
		this.modifyDate = modifyDate;
	}

	public Token() {
		super();
	}

}