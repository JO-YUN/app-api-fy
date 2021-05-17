package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.resp.RespNewsTypeDto;
import com.mscs.app.web.model.NewsType;

@Repository
@Mapper
public interface NewsTypeMapper {
	
	public List<RespNewsTypeDto> queryNewsTypeList(@Param ("obj") NewsType obj);
}
