package com.mscs.app.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.resp.RespAutoCityDto;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.AutoCityService;

/**
* @title: AutoCityController 
* @description：TODO
* @author： 刘威巍
* @date： 2018年10月16日 下午7:15:07
 */
@Controller
@RequestMapping
public class AutoCityController extends BIZController {
	@Qualifier("AutoCityServiceImpl")
	@Autowired
	private AutoCityService autoCityService;
	/**
	* @MethodName: getAddresses 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2018年10月18日 下午4:56:06
	* @param request
	* @return
	* @throws Exception ResponseDto
	 */
	@RequestMapping(value = "/autocity/getAddresses", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto getAddresses(HttpServletRequest request) throws Exception {
		RespAutoCityDto dto = autoCityService.getAddresses(request);
		return ResponseDto.buildSuccess().setData(dto);
	}

}
