package com.mscs.app.web.controller;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqLoginDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.help.VerificationCodeHelper;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.TokenService;
import com.mscs.app.web.service.UserService;

/**
 * 登陆接口
 * 
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
@Controller
@RequestMapping
public class LoginController extends BIZController {

	public static Logger logger = LoggerFactory
			.getLogger(LoginController.class);

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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto signIn(@RequestBody ReqLoginDto loDto) throws Exception {
		if (this.valid(loDto.getCode()) == 0)
			throw new AppException(ErrorCode.CHECKCODE_ERROR_CODE,
					ErrorCode.CHECKCODE_ERROR_MSG);
		return ResponseDto.buildSuccess().setData(userService.login(loDto));
	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto signOut(@RequestParam String token) throws Exception {
		if (StringUtils.isBlank(token))
			throw new AppException(ErrorCode.TOKENNULL_FAIL_CODE,
					ErrorCode.TOKENNULL_FAIL_MSG);
		userService.logout(token);
		return ResponseDto.buildSuccess();
	}

	/**
	 * 查询登陆状态 即 判断登陆是否失效
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/state", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto showLoginState(@RequestParam String token)
			throws Exception {
		if (StringUtils.isBlank(token))
			throw new AppException(ErrorCode.TOKENNULL_FAIL_CODE,
					ErrorCode.TOKENNULL_FAIL_MSG);
		tokenService.getUID(token);
		return ResponseDto.buildSuccess();
	}

	/**
	 * 1 ok 0 not ok
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	private int valid(String code) throws IOException {
		String sessionCheckCode = (String) getSession().getAttribute(
				"LOGIN_CHECKCODE_");
		int flag = VerificationCodeHelper.checkIdentifyingCode(
				sessionCheckCode, code);
		getSession().removeAttribute("LOGIN_CHECKCODE_");
		logger.warn("SESSION ID: {}删除SESSION中保存到验证码", getSession().getId());
		return flag;
	}

}
