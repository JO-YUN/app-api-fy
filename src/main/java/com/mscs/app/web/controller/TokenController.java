package com.mscs.app.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.TokenService;
import com.mscs.app.web.service.UserService;


/**
 * 对外提供Token接口 通过token 返回个人信息
 * 
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
@Controller
@RequestMapping
public class TokenController extends BIZController {

	@Qualifier("UserServiceImpl")
	@Autowired
	private UserService userService;

	@Qualifier("TokenServiceImpl")
	@Autowired
	private TokenService tokenService;

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/token/user", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto getUserInfo(@RequestParam String token) throws Exception {
		if (StringUtils.isBlank(token))
			throw new AppException(ErrorCode.TOKENNULL_FAIL_CODE,
					ErrorCode.TOKENNULL_FAIL_MSG);
		String uid = this.getUsername(token);
		return ResponseDto.buildSuccess().setData(uid);
	}

}
