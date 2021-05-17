package com.mscs.app.web.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.req.ReqNewsColumn;
import com.mscs.app.common.dto.resp.RespNewsColumn;

@Repository
@Mapper
public interface NewsColumnMapper {
	public List<RespNewsColumn> queryNewsColumnList(@Param("obj")  ReqNewsColumn obj);
}
