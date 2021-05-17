package com.mscs.app.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.req.ReqAlipayUserInfroDto;
import com.mscs.app.common.dto.req.ReqWeiXin;
import com.mscs.app.web.dao.pojo.UserAppCountVo;
import com.mscs.app.web.model.User;

@Repository
@Mapper
public interface UserMapper {

	/**
	 * 获取用户信息通过学工号
	 * 
	 * @param xgh
	 * @return
	 */
	User findUserByUserId(@Param("userId") String userId);
	User fetchInforByAlipayId(@Param("alipayId") String alipayId);
	/**
	 * 
	 * @return
	 */
	UserAppCountVo countAppNum();

	User Verifit(@Param("userId") String userId,@Param("passWord") String passWord);

	void updateInforByUid(User infor);

    void add(User user);
    void resetPwd(User user);


	String VerifitPass(@Param("userId") String userId);
	 
	int UpdatePassWord(@Param("userId") String userId,@Param("passWord") String passWord);
	
	String UserIdByToken(@Param("token") String token);
	
	String TokenByUserId(@Param("userId") String userId);
	void updatePic(User user);
	
	User checkOpenId(@Param("openid") String openid);
	User checkOpenIdAndS(@Param("openid") String openid);
	
	int updateByUid(ReqWeiXin wx);
	
	void addWeixin(ReqWeiXin wx);
	void updateAlipayUser(ReqAlipayUserInfroDto dto);
	void addAlipayUser(ReqAlipayUserInfroDto dto);
	void deleteUserByAlipayId(@Param("alipayId") String alipayId);
	void addWx(User user);
	void addWxhg(User user);
	void addWxhc(User user);
	User checkOpenIdAndSHG(@Param("openid") String openid);
	User checkOpenIdAndShc(@Param("openid") String openid);
	void addUserHistory(User userInfo);
}