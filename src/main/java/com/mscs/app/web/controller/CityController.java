package com.mscs.app.web.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqCityDto;
import com.mscs.app.common.dto.resp.RespCityDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.ICityService;


/**
 * 
 * @ClassName:  CityController   
 * @Description:TODO(省市县三级联动)   
 * @date:   2018年10月16日 
 * @author: CHIUCLOUD(云)    
 * @Copyright: Mr丶ZHAO. All rights reserved. 
 *
 */
@Controller
@RequestMapping
public class CityController extends BIZController{
	
	
	@Qualifier("CityServiceImpl")
	@Autowired
	private ICityService cityService;
	
	
	@RequestMapping(value = "infor/fetchSiteInfor", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResponseDto fetchSiteInfor(ReqCityDto dto) throws ParseException, AppException {
		List<RespCityDto> dtos=cityService.fetchSiteInfor(dto);
		return ResponseDto.buildSuccess().setData(dtos);
	}
	
	
}
