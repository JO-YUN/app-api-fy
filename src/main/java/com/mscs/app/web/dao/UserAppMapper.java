package com.mscs.app.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.UserApp;

@Repository
@Mapper
public interface UserAppMapper {

	/**
	 * 
	 * @param userId
	 * @return
	 */
	// List<UserApp findAppIdByUserId(@Param("userId") String userId);

	UserApp findByUserIdAndAppId(@Param("userId") String userId,
			@Param("appId") String appId);

	/**
	 * 查询
	 * 
	 * @param ua
	 */
	void insert(UserApp ua);

	/**
	 * 彻底删除
	 * 
	 * @param userId
	 * @param appId
	 */
	void deleteRecord(@Param("userId") String userId,
			@Param("appId") String appId);

}