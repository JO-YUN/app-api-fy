package com.mscs.app.web.controller.base;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.service.TokenService;


/**
 * 基类
 * 
 * @author hechunyang
 *
 */
@Controller
public class BIZController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	@SuppressWarnings("unused")
	private String sessionId;

	public String getSessionId() {
		return this.getSession().getId();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 获取session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return this.getRequest().getSession();
	}

	/**
	 * 获取session
	 * 
	 * @param flag
	 * @return
	 */
	public HttpSession getSession(boolean flag) {
		return this.getRequest().getSession(flag);
	}

	/**
	 * 
	 * @param token
	 * @return
	 * @throws AppException
	 * @throws ParseException
	 */
	public String getUsername(String token) throws AppException{
		return tokenService.getUID(token);
	}

}
