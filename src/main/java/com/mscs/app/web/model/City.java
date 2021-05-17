package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class City extends BIZModel{

	/**
	 * @author  CHIUCLOUD(云)    
	 * @Fields serialVersionUID : TODO(省市县区,三级联动,表名CODETYPE)   
	 */  
	private static final long serialVersionUID = 1L;

	

	private String dm; //代码
	private String dmlx; //代码类型
	private String mc; //名称
	private String px; //排序
	private String state; //是否使用
	private String fhdm; //返回代码
	private String sjdm; //上级代码
	private String sjz; //上级值
	private String bzdm;//标准代码
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getDmlx() {
		return dmlx;
	}
	public void setDmlx(String dmlx) {
		this.dmlx = dmlx;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
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
	public String getBzdm() {
		return bzdm;
	}
	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
