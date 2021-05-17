package com.mscs.app.common.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.mscs.app.common.dto.BaseDto;


public class ReqUserDto extends BaseDto{
	private static final long serialVersionUID = 653480215507553928L;
	
	@NotBlank(message = "手机号为空")
	private String userId;//手机号
	
	@NotBlank(message = "密码为空")
	private String passWord;//密码
	
	@NotBlank(message = "车牌号为空")
	private String carid;
	
	@NotBlank(message = "验证码为空")
	private String verificationCode;//短信验证码
	
	private String sfzjh;
	
	private String openid;
	
	private String name;
	
	private String code;
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSfzjh() {
		return sfzjh;
	}
	public void setSfzjh(String sfzjh) {
		this.sfzjh = sfzjh;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
    
}
