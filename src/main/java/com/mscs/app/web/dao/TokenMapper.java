package com.mscs.app.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.Token;

@Repository
@Mapper
public interface TokenMapper {

	/**
	 * 插入token 和用戶ID
	 * 
	 * @param record
	 * @return
	 */
	int insert(Token record);

	/**
	* @MethodName: findInfoByToken 
	* @description : TODO
	* @author：liuweiwei
	* @date： 2019年2月19日 上午9:23:56
	* @param sToken
	* @return Token
	 */
	Token findInfoByToken(@Param("token") String sToken);

	/**
	 * 
	* @MethodName: updateByToken 
	* @description : TODO
	* @author：刘威巍 
	* @date： 2019年2月19日 上午8:44:13
	* @param record void
	 */
	void updateByToken(Token record);

	/**
	 * 
	 * @param token
	 */
	void deleteRecordByToken(@Param("token") String token);

	/**
	 * 
	 * @param userId
	 */
	void deleteRecordByUID(@Param("userId") String userId);

	/**
	 * 
	 * @param username
	 * @return
	 */
	Token findInfoByUserId(@Param("userId") String userId);

}