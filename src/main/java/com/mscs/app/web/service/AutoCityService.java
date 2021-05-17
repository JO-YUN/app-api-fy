package com.mscs.app.web.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.mscs.app.common.dto.resp.RespAutoCityDto;


/**
* @title: AutoCityService 
* @description：TODO
* @author： 刘威巍
* @date： 2018年10月16日 下午7:16:11
 */
public interface AutoCityService {

	/**
	* @MethodName: getAddresses 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年10月18日 下午4:48:22
	* @return
	* @throws AppException ResponseDto
	 */
	RespAutoCityDto getAddresses(HttpServletRequest request) throws IOException;
}
