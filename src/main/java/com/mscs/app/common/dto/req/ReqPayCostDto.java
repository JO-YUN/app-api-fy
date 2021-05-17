package com.mscs.app.common.dto.req;

import com.mscs.app.common.dto.BaseDto;

public class ReqPayCostDto extends BaseDto{
	private static final long serialVersionUID = 653480215507553928L;

	private String token;
	private String paytype;//缴费类型
	private String xzqydm;//行政区域代码
	private String IsMobile;// 手机端标志，0-PC端，1-手机APP，2-微信公众号
	private String cityCode;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getXzqydm() {
		return xzqydm;
	}
	public void setXzqydm(String xzqydm) {
		this.xzqydm = xzqydm;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getIsMobile() {
		return IsMobile;
	}
	public void setIsMobile(String isMobile) {
		IsMobile = isMobile;
	}
	

}
