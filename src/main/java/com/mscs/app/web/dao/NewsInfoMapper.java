package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.req.ReqNewsInfoDot;
import com.mscs.app.common.dto.resp.RespNewsInfoDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.web.model.NewsInfo;

@Repository
@Mapper
public interface NewsInfoMapper {

	public List<RespNewsInfoDto> queryNewsInfoListPage(@Param("page")Page page, @Param("obj")ReqNewsInfoDot obj);
	
	public int queryNewsInfoForCount(@Param("page")Page page, @Param("obj")  ReqNewsInfoDot obj);
	
	public RespNewsInfoDto queryNewsInfoById(@Param("obj") ReqNewsInfoDot obj);
	
	public List<RespNewsInfoDto> queryNewsInfoList(@Param("obj") ReqNewsInfoDot obj);
	
	void updateNewsInfoData(NewsInfo news);

	
}
