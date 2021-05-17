package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.TZNR;

@Repository
@Mapper
public interface TZNRMapper {
	public List<TZNR> getTZNRListByTZID(@Param("tzid") String tzid);
	

}
