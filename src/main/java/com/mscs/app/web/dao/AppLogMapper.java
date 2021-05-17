package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.dao.pojo.AppLogVo;
import com.mscs.app.web.model.AppLog;

@Repository
@Mapper
public interface AppLogMapper {

	/**
	 * insert
	 * 
	 * @param log
	 */
	void insert(AppLog log);

	/**
	 * query
	 * 
	 * @return
	 */
	List<AppLogVo> queryAppLogCountInfo();

}
