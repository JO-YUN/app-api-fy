package com.mscs.app.common.dto.req;

import com.mscs.app.common.dto.BaseDto;

/**
 * 登陆账户
 * 
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
public class ReqVerDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7379901136910394277L;

	/**
	 * 安卓 苹果 1--安卓 2--苹果
	 */
	private Integer os;

	/**
	 * 类型 1--青春版 2--稳重
	 */
	private Integer type;

	public Integer getOs() {
		return os;
	}

	public void setOs(Integer os) {
		this.os = os;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
