package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.GuideImg;

@Repository
@Mapper
public interface GuideImgMapper {

	/**
	 * 
	 * @return
	 */
	List<GuideImg> queryGuideImg();
}