package com.mscs.app.web.service;

import com.mscs.app.common.dto.req.ReqAdviceDto;
import com.mscs.app.common.exception.AppException;

public interface AdviceService {

	/**
	 * 新增意见
	 * 
	 * @throws AppException
	 */
	void addAdvice(ReqAdviceDto dto) throws AppException;

}
