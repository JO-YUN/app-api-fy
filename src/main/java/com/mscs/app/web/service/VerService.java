package com.mscs.app.web.service;

import java.util.List;

import com.mscs.app.common.dto.req.ReqVerDto;
import com.mscs.app.common.dto.resp.RespVerDto;


/**
 * 版本信息接口
 * 
 * @author hechunyang
 * @date 2018年3月12日
 * @remark TODO
 */
public interface VerService {

	/**
	 * 获取版本信息
	 * 
	 * @return
	 */
	List<RespVerDto> getLocalVer();

	/**
	 * 
	 * @param dto
	 * @return
	 */
	List<RespVerDto> getLocalVer(ReqVerDto dto);

}
