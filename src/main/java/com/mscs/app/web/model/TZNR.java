package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class TZNR extends BIZModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tzid;
	private String xh;
	private String nr;
	public String getTzid() {
		return tzid;
	}
	public void setTzid(String tzid) {
		this.tzid = tzid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	
}
