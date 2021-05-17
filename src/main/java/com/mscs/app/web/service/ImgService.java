package com.mscs.app.web.service;

import java.util.List;

import com.mscs.app.common.dto.resp.RespGuideImgDto;
import com.mscs.app.common.dto.resp.RespImgDto;


/**
 * 
 * @author hechunyang
 * @date 2018年3月12日
 * @remark TODO
 */
public interface ImgService {

	/**
	 * 获取新闻的标题图
	 * 
	 * @return
	 */
	List<RespImgDto> fetchImgs();

	/**
	 * 获取引导图片
	 * 
	 * @return
	 */
	List<RespGuideImgDto> fetchGuideImgs();

}
