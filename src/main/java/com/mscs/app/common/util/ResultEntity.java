package com.mscs.app.common.util;

import java.util.List;


public class ResultEntity implements Result {
	
	//private String code = "200";
	
	//private String message ="操作成功";
	
	private String errorcode = "1";
	private String errormsg ="SUCCESS";
	private Object obj;
	
	
	private List<?> list;
	

	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	

}
