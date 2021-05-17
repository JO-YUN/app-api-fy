package com.mscs.app.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.convert.CityConverter;
import com.mscs.app.common.dto.req.ReqCityDto;
import com.mscs.app.common.dto.resp.RespCityDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.controller.CityController;
import com.mscs.app.web.dao.CityMapper;
import com.mscs.app.web.model.City;
import com.mscs.app.web.service.ICityService;


@Service("CityServiceImpl")
public class CityServiceImpl implements ICityService{
	
	
	public static Logger logger = LoggerFactory.getLogger(CityController.class);
	
	
	@Autowired
	private CityMapper cityMapper;
	
	
	@Override
	public List<RespCityDto> fetchSiteInfor(ReqCityDto dto) throws AppException {
			City city =CityConverter.buildCityInforObj(dto);
			List<RespCityDto> dtos =cityMapper.fetchSiteInfor(city);
	   return dtos;
	}

}
