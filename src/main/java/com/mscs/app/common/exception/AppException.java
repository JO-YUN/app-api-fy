package com.mscs.app.common.exception;


public class AppException  extends Exception {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1646677747052408129L;

	private String code;

	/**
	 * 
	 */

	public AppException () {
		super();
	}

	public AppException (String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AppException (String message, Throwable cause) {
		super(message, cause);
	}

	public AppException (String message) {
		super(message);
	}

	public AppException (Throwable cause) {
		super(cause);
	}

	/**
	 * 新增构造方法
	 * 
	 * @param code
	 * @param message
	 */
	public AppException (String code, String message) {
		super(message);
		this.code = code;
		
	}

	/**
	 * 新增构造方法2
	 * 
	 * @param code
	 * @param message
	 * @param cause
	 */
	public AppException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
