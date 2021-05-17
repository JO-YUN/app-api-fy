package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class RZ extends BIZModel{
	private static final long serialVersionUID = 1L;
	private String rzid;//日志ID
	private String ycdm;//异常代码
	private String ycxx;//异常信息
	private String czsj;//操作时间
	private String czrq;//操作日期
	public String getRzid() {
		return rzid;
	}
	public void setRzid(String rzid) {
		this.rzid = rzid;
	}
	public String getYcdm() {
		return ycdm;
	}
	public void setYcdm(String ycdm) {
		this.ycdm = ycdm;
	}
	public String getYcxx() {
		return ycxx;
	}
	public void setYcxx(String ycxx) {
		this.ycxx = ycxx;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	public String getCzrq() {
		return czrq;
	}
	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}
	
}
