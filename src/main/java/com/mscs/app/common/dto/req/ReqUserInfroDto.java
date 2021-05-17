package com.mscs.app.common.dto.req;

import com.mscs.app.common.dto.BaseDto;

public class ReqUserInfroDto extends BaseDto{

	/**
	 * @author  Mr丶ZHAO  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = -3477796884776020453L;
	
	private String token;
	/**
	 * 用戶名 用戶名可以由前台传，也可以在数据库中查出来
	 */
	private String userId; //学工号

	private String name;//姓名

	private String gender;//性别 1 男 2 女

	private String email;//电子邮箱

	private String address;//地址
	
	private String sfzjh;// 身份证件号
    private String attachId;//图片
   

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSfzjh() {
		return sfzjh;
	}

	public void setSfzjh(String sfzjh) {
		this.sfzjh = sfzjh;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
