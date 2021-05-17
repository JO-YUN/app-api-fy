package com.mscs.app.common.util;


public class ResultPage extends Page implements Result{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errormsg ="SUCCESS";
	private String errorcode= "1";
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	
	
	
}
