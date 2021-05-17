package com.mscs.app.web.service;

import java.util.List;

import com.mscs.app.common.dto.req.ReqTzggDto;
import com.mscs.app.common.dto.resp.RespTzggDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;


/**
 * @author LiuGuoHui
 * @date 2018-10-09
 *
 */

public interface TZGGService {	
	
	/**
	 * 获取最新通知公告列表前两条
	 * @return
	 */
	public List<RespTzggDto> getZxTzgg(ReqTzggDto dto);
	/**
	 * 获取全部通知公告列表
	 * @return
	 */
	public Result queryAllTzgg(Page page,ReqTzggDto dto);
	
	/**
	 * 获取通知公告详情
	 * @return
	 */
	public RespTzggDto queryTzggById(ReqTzggDto dto);
	
	/**
	 * 返回带分页的通知公告
	 * @param resmap
	 * @return
	
	public Result gettzggPage(com.hrbwmxx.framework.model.Page page,Map resmap);
 */
}
