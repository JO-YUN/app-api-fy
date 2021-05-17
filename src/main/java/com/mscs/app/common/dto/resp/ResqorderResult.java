package com.mscs.app.common.dto.resp;

import java.io.Serializable;

public class ResqorderResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1209252586306481692L;
	//00-成功，
	//01-失败

	private String Rcv_StCd = "00";
	private String SYS_RESP_CODE;
	private String Rsp_Inf;
	private String Rcv_Tm;
	public String getRcv_StCd() {
		return Rcv_StCd;
	}
	public void setRcv_StCd(String rcv_StCd) {
		Rcv_StCd = rcv_StCd;
	}
	public String getSYS_RESP_CODE() {
		return SYS_RESP_CODE;
	}
	public void setSYS_RESP_CODE(String sYS_RESP_CODE) {
		SYS_RESP_CODE = sYS_RESP_CODE;
	}
	public String getRsp_Inf() {
		return Rsp_Inf;
	}
	public void setRsp_Inf(String rsp_Inf) {
		Rsp_Inf = rsp_Inf;
	}
	public String getRcv_Tm() {
		return Rcv_Tm;
	}
	public void setRcv_Tm(String rcv_Tm) {
		Rcv_Tm = rcv_Tm;
	}
	
	
}
