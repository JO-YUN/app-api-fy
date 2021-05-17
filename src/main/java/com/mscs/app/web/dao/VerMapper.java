package com.mscs.app.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.Ver;

@Repository
@Mapper
public interface VerMapper {

	/**
	 * 
	 * @return
	 */
	List<Ver> queryVer();

	/**
	 * 
	 * @param condtion
	 * @return
	 */
	List<Ver> queryVerByCondition(Map<String, Object> condtion);
}