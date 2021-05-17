package com.mscs.app.web.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqUserInfroDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.model.User;
import com.mscs.app.web.service.UserService;


/**
 * 
 * @ClassName:  UserInforController   
 * @Description:TODO(用户信息)   
 * @date:   2018年10月9日 
 * @author: Mr丶ZHAO  
 * @Copyright: Mr丶ZHAO. All rights reserved. 
 *
 */
@Controller
@RequestMapping
public class UserInforController extends BIZController{

	
	@Qualifier("UserServiceImpl")
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @Title: fetchInfor   
	 * @Description: TODO(查询用户信息)   
	 * @param: @param wid
	 * @param: @return
	 * @param: @throws ParseException
	 * @param: @throws AppException      
	 * @return: ResponseDto
	 * @author: Mr丶ZHAO      
	 * @throws
	 */
	@RequestMapping(value = "infor/fetchInfor", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto fetchInforByUid(@RequestParam String token) throws ParseException, AppException {
		User infors=userService.fetchInforByUid(this.getUsername(token));
		System.err.println(infors.getAlipayheadurl());
		System.err.println(infors.getUserId());
		System.err.println(infors.getGender());
		return ResponseDto.buildSuccess().setData(infors);
	}
	/**
	 * 
	 * @Title: updateInfor   
	 * @Description: TODO(修改用户信息)   
	 * @param: @param infor
	 * @param: @return
	 * @param: @throws AppException      
	 * @return: ResponseDto
	 * @author: Mr丶ZHAO      
	 * @throws
	 */
	@RequestMapping(value = "infor/updateInfor", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResponseDto updateInforByUid( ReqUserInfroDto dto) throws AppException {
		dto.setUserId(getUsername(dto.getToken()));
		userService.updateInforByUid(dto);
		return ResponseDto.buildSuccess();
	}
	/**
	 * 
	 * @Title: updatePic   
	 * @Description: TODO(上传图片)   
	 * @param: @throws AppException      
	 * @return: ResponseDto
	 * @author: LiuGuoHui      
	 * @throws
	 */
	@RequestMapping(value = "infor/updatePic", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResponseDto updatePic( ReqUserInfroDto dto) throws AppException {
		dto.setUserId(getUsername(dto.getToken()));//找到登录的用户ID
		userService.updatePic(dto);//根据用户ID修改该用户的PIC和附件表的PIC状态
		return ResponseDto.buildSuccess();
	}
	
}
