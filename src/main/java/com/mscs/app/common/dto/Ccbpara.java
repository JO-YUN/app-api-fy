package com.mscs.app.common.dto;

public class Ccbpara {
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTprt_Mode() {
		return tprt_Mode;
	}
	public void setTprt_Mode(String tprt_Mode) {
		this.tprt_Mode = tprt_Mode;
	}
	public String getTprt_Parm() {
		return tprt_Parm;
	}
	public void setTprt_Parm(String tprt_Parm) {
		this.tprt_Parm = tprt_Parm;
	}

	public String getBsn_Data() {
		return bsn_Data;
	}
	public void setBsn_Data(String bsn_Data) {
		this.bsn_Data = bsn_Data;
	}

	private String tprt_Mode;
	private String tprt_Parm;
	private String bsn_Data;
	
}
