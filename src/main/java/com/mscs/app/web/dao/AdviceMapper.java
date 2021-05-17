package com.mscs.app.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.Advice;

@Repository
@Mapper
public interface AdviceMapper {

	int insertSelective(Advice record);
}