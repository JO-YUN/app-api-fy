package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.Img;

@Repository
@Mapper
public interface ImgMapper {

	/**
	 * 查找所有标题图
	 * 
	 * @return
	 */
	List<Img> queryImg();
}