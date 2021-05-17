package com.mscs.app.web.service.impl;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.dao.TokenMapper;
import com.mscs.app.web.model.Token;
import com.mscs.app.web.service.TokenService;


@Service("TokenServiceImpl")
public class TokenServiceImpl implements TokenService {

	public static Logger logger = LoggerFactory
			.getLogger(TokenServiceImpl.class);

	/**
	 * token的失效时间
	 */
	public static final long LOST_TIME = 86400000l;

	@Autowired
	private TokenMapper tokenMapper;

	/**
	 * 
	 * @param token
	 * @return
	 * @throws AppException
	 * @throws ParseException
	 */
	@Transactional
	public String getUID(String sToken) throws AppException {
	
		Token token = tokenMapper.findInfoByToken(sToken);
		/*
		 * if ((System.currentTimeMillis() - DateTimeUtils.parseDateTime(
		 * token.getModifyDate()).getTimeInMillis()) > LOST_TIME) {
		 * logger.warn("Token时间已经过期"); throw new
		 * AppException(Code.TOKEN_FAIL_CODE, Code.TOKEN_FAIL_MSG); }
		 * token.setModifyDate(DateTimeUtils.now().toDateTimeString());
		 * tokenMapper.updateByToken(token);
		 */
		if (token == null)
			throw new AppException(ErrorCode.TOKEN_FAIL_CODE, ErrorCode.TOKEN_FAIL_MSG);
		String userId = token.getUserId();
		if (StringUtils.isBlank(userId))
			throw new AppException(ErrorCode.UID_FAIL_CODE, ErrorCode.UID_FAIL_MSG);
		return userId;
	}
}

