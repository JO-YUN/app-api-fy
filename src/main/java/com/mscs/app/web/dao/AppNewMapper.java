package com.mscs.app.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AppNewMapper {
	List<Map<String, String>> listApps(Map<String, String> param);
}
