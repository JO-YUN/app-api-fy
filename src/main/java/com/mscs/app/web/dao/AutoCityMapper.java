package com.mscs.app.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AutoCityMapper {
	
	String findBzdmByCode(@Param("cityCode") String cityCode);

}