package com.mscs.app.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqRealNameDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.help.VerificationCodeHelper;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.RealNameService;



/**
 * 实名认证
 */

@Controller
@RequestMapping("realName")
public class RealNameController  extends BIZController{

	
	@Autowired
	private RealNameService nameService;
	
	/**
	 * 保存
	 * @Title: saveRealName 
	 * @param dto
	 * @param httpSession
	 * @return
	 * @throws Exception  
	 * @author: Mr.Zhao
	 */
	@RequestMapping(value = "/saveRealName", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto saveRealName(ReqRealNameDto dto,HttpSession httpSession) throws Exception {
		int falg=this.valid(dto.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}
		/*Object ido = httpSession.getAttribute("openidnh");
		if(ido != null) {
			String openid = ido.toString();
			 dto.setOpenid(openid);
			
		}*/
		nameService.saveRealName(dto);
		return ResponseDto.buildSuccess();
	}
	
	@RequestMapping(value = "/checkOpenidFy", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto checkOpenidFy(ReqRealNameDto dto,HttpSession httpSession) throws Exception {
		int i = nameService.checkOpenidFy(dto);
		if(i > 0) {
			return ResponseDto.buildSuccess();
		}else {
			return ResponseDto.buildFail().setErrorcode("0");
		}
	}
	
	@RequestMapping(value = "/checkOpenidNh", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto checkOpenidNh(ReqRealNameDto dto,HttpSession httpSession) throws Exception {
		int i = nameService.checkOpenidNh(dto);
		if(i > 0) {
			return ResponseDto.buildSuccess();
		}else {
			return ResponseDto.buildFail().setErrorcode("0");
		}
	}
	
	@RequestMapping(value = "/saveRealNameNh", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto saveRealNameNh(ReqRealNameDto dto,HttpSession httpSession) throws Exception {
		int falg=this.valid(dto.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}
		/*Object ido = httpSession.getAttribute("openidnh");
		if(ido != null) {
			String openid = ido.toString();
			 dto.setOpenid(openid);
			
		}*/
		nameService.saveRealNameNh(dto);
		return ResponseDto.buildSuccess();
	}
	
	@RequestMapping(value = "/updateRealNameNh", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto updateRealNameNh(ReqRealNameDto dto,HttpSession httpSession) throws Exception {
		int falg=this.valid(dto.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}
		/*Object ido = httpSession.getAttribute("openidnh");
		if(ido != null) {
			String openid = ido.toString();
			 dto.setOpenid(openid);
			
		}*/
		nameService.updateRealNameNh(dto);
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
		//System.out.println(new Date());
		String sessionCheckCode = (String) getSession().getAttribute("LOGIN_CHECKCODE_");
		//System.out.println("sessionCheckCode"+sessionCheckCode);
		//System.out.println("sessionTime"+this.getSession().getMaxInactiveInterval());
		int flag = VerificationCodeHelper.checkIdentifyingCode(
				sessionCheckCode, code);
		/*if(flag!=-1) {
			System.out.println("删除session数据");
			getSession().removeAttribute("code");
			logger.warn("SESSION ID: {}删除SESSION中保存到验证码", getSession().getId());
		}*/
		return flag;
	}
}
