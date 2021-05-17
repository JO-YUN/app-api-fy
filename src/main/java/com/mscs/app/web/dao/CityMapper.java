package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.resp.RespCityDto;
import com.mscs.app.web.model.City;

@Repository
@Mapper
public interface CityMapper {

	List<RespCityDto> fetchSiteInfor(@Param("obj")City obj);

}
