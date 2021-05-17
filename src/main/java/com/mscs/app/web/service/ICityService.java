package com.mscs.app.web.service;

import java.util.List;

import com.mscs.app.common.dto.req.ReqCityDto;
import com.mscs.app.common.dto.resp.RespCityDto;
import com.mscs.app.common.exception.AppException;


public interface ICityService {

	List<RespCityDto> fetchSiteInfor(ReqCityDto dto) throws AppException;

}
