package com.mscs.app.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.convert.AdviceConverter;
import com.mscs.app.common.dto.req.ReqAdviceDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.dao.AdviceMapper;
import com.mscs.app.web.dao.TokenMapper;
import com.mscs.app.web.model.Advice;
import com.mscs.app.web.service.AdviceService;

@Service("AdviceServiceImpl")
public class AdviceServiceImpl implements AdviceService {
	public static final Logger logger = LoggerFactory
			.getLogger(AdviceServiceImpl.class);

	@Autowired
	private AdviceMapper adviceMapper;

	@Autowired
	private TokenMapper tokenMapper;

	@Transactional
	public void addAdvice(ReqAdviceDto dto) throws AppException {
		try {
			Advice ad = AdviceConverter.buildAdviceObj(dto);
			adviceMapper.insertSelective(ad);
		} catch (Exception e) {
			logger.error("新增意见征集异常", e);
			throw new AppException(ErrorCode.ADVICE_FAIL_CODE, ErrorCode.ADVICE_FAIL_MSG);
		}
	}
}
