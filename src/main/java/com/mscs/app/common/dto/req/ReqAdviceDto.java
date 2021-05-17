package com.mscs.app.common.dto.req;

import com.mscs.app.common.dto.BaseDto;

/**
 * 意见反馈
 * 
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
public class ReqAdviceDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 653480215507553928L;

	private String token;

	/**
	 * 用戶名 用戶名可以由前台传，也可以在数据库中查出来
	 */
	private String username;

	private String advice;

	private String phone;

	private String mail;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
