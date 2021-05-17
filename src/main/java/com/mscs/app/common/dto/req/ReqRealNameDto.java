package com.mscs.app.common.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.mscs.app.web.model.RealName;


public class ReqRealNameDto extends RealName{

	
	/** serialVersionUID*/ 
	private static final long serialVersionUID = 1L;
	
	
	@NotBlank(message = "验证码为空")
	private String verificationCode;//短信验证码


	public String getVerificationCode() {
		return verificationCode;
	}


	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
}
