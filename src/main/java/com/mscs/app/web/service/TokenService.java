package com.mscs.app.web.service;

import java.text.ParseException;

import com.mscs.app.common.exception.AppException;


/**
 * 对外提供接口
 * 
 * @author hechunyang
 * @date 2018年3月14日
 * @remark TODO
 */
public interface TokenService {

	/**
	 * 獲取UID
	 * 
	 * @param sToken
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	String getUID(String sToken) throws AppException;
}
