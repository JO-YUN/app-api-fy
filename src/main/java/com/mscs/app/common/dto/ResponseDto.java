package com.mscs.app.common.dto;


public class ResponseDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246283590371827761L;
	public static final String SUCCESS_CODE = "1";
	public static final String SUCCESS_MSG = "SUCCESS";
	
	private String errormsg;
	private String errorcode;
	private Object data;

	public static ResponseDto buildSuccess() {
		ResponseDto resp = new ResponseDto();
		resp.setErrormsg(SUCCESS_MSG);
		resp.setErrorcode(SUCCESS_CODE);
		return resp;
	}

	public static ResponseDto buildFail() {
		ResponseDto resp = new ResponseDto();
		return resp;

	}

	public ResponseDto() {
		super();
	}

	public ResponseDto(String msg, String code, Object data) {
		super();
		this.errormsg = msg;
		this.errorcode = code;
		this.data = data;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public ResponseDto setErrormsg(String errormsg) {
		this.errormsg = errormsg;
		return this;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public ResponseDto setErrorcode(String errorcode) {
		this.errorcode = errorcode;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResponseDto setData(Object data) {
		this.data = data;
		return this;
	}

}
