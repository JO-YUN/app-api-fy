package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.req.ReqTzggDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.web.model.TZGG;


/*import com.hrbwmxx.framework.model.Page;*/



/**
 * @author LiuGuoHui
 * @date 2018-10-09
 *
 */
@Repository
@Mapper
public interface TZGGMapper {
	
	//获取最新通知公告列表前两条
	public List<TZGG> queryZxTZGG(@Param("obj")ReqTzggDto dto);
    // 获取全部通知公告列表
	public List<TZGG> queryAllTzgg(@Param("page") Page page,@Param("obj")ReqTzggDto dto);
	public TZGG queryTzggById(@Param("tzid") String id);
	//返回带分页的所有通知公告信息
	//public List<TZGG> queryTZGGPage(@Param("page") Page page);
	//返回通知公告信息的总数目
	public int queryTZGGPageCount(@Param("page") Page page,@Param("obj")ReqTzggDto dto);
	public void updateYDRS(@Param("tzid") String id);

}
