package com.mscs.app.common.dto.req;

import com.mscs.app.common.dto.BaseDto;

public class ReqCityDto extends BaseDto{

	/**
	 * @author  CHIUCLOUD(云)    
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = -5696786486869848592L;
	
	
	private String fhdm; //返回代码
	private String sjdm; //上级代码
	private String sjz; //上级值
	
	public String getFhdm() {
		return fhdm;
	}
	public void setFhdm(String fhdm) {
		this.fhdm = fhdm;
	}
	public String getSjdm() {
		return sjdm;
	}
	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}
	public String getSjz() {
		return sjz;
	}
	public void setSjz(String sjz) {
		this.sjz = sjz;
	}
}
