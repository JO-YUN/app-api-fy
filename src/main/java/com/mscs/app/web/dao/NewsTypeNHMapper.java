package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.resp.RespNewsTypeNHDto;
import com.mscs.app.web.model.NewsTypeNH;

@Repository
@Mapper
public interface NewsTypeNHMapper {
	
	public List<RespNewsTypeNHDto> queryNewsTypeList(@Param ("obj") NewsTypeNH obj);
}
