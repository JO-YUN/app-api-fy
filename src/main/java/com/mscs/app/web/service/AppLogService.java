package com.mscs.app.web.service;

import java.util.List;

import com.mscs.app.common.dto.resp.RespAppLogDto;
import com.mscs.app.common.dto.resp.RespUserAppCountDto;
import com.mscs.app.common.exception.AppException;


public interface AppLogService {

	/**
	 * 
	 * @param appId
	 * @throws AppException
	 */
	void addAppLog(String appId) throws AppException;

	/**
	 * 
	 * @return
	 */
	List<RespAppLogDto> getAppLogCountInfo();

	/**
	 * 查詢是否已經註冊了
	 * 
	 * @return
	 */
	RespUserAppCountDto getUserAppCountInfo();

}
