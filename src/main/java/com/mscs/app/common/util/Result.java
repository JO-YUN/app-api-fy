package com.mscs.app.common.util;

public interface Result {

	//以下是系统错误返回数据和响应信息
	
	public String getErrorcode();
	
	public void setErrorcode(String errcode);
	
	public String getErrormsg();
	
	public void setErrormsg(String errmsg);
	
}
