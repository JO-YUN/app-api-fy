package com.mscs.app.common.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.mscs.app.common.dto.BaseDto;


/**
 * 登陆账户
 * 
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
public class ReqLoginDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7980300719073405184L;

	@NotBlank(message = "用户名为空")
	private String username;

	@NotBlank(message = "密码为空")
	private String password;

	@NotBlank(message = "验证码为空")
	private String code;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
