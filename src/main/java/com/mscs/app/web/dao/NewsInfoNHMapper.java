package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.resp.RespNewsInfoNHDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.web.model.NewsInfo;
import com.mscs.app.web.model.NewsInfoNH;

@Repository
@Mapper
public interface NewsInfoNHMapper {

	public List<RespNewsInfoNHDto> queryNewsInfoListPage(@Param("page")Page page, @Param("obj")NewsInfoNH obj);
	
	public int queryNewsInfoForCount(@Param("page")Page page, @Param("obj")  NewsInfoNH obj);
	
	public RespNewsInfoNHDto queryNewsInfoById(@Param("obj") NewsInfoNH obj);
	
	public List<RespNewsInfoNHDto> queryNewsInfoList(@Param("obj") NewsInfoNH obj);
	
	void updateNewsInfoData(NewsInfo news);

	
}
