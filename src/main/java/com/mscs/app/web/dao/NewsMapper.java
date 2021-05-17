package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.req.ReqNewsDto;
import com.mscs.app.common.dto.resp.RespNewsDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.web.model.News;

@Repository
@Mapper
public interface NewsMapper {
	
  public RespNewsDto queryNewsById(@Param("obj") ReqNewsDto obj);
   public List<RespNewsDto> queryNewsListPage(@Param("page")Page page, @Param("obj") ReqNewsDto obj);
   public int queryNewsForCount(@Param("page")Page page, @Param("obj")  ReqNewsDto obj);
   public List<RespNewsDto> queryNewsList(@Param("obj") ReqNewsDto obj);
   void updateNewsData(News news);
}
