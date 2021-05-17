package com.mscs.app.web.service;

import com.mscs.app.common.dto.req.ReqAlipayUserInfroDto;
import com.mscs.app.common.dto.req.ReqLoginDto;
import com.mscs.app.common.dto.req.ReqUserDto;
import com.mscs.app.common.dto.req.ReqUserInfroDto;
import com.mscs.app.common.dto.req.ReqWeiXin;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.model.AlipayUser;
import com.mscs.app.web.model.User;

/**
 * @remark TODO
 */
public interface UserService {

	// RespLoginDto login(ReqLoginDto dto) throws Exception;

	/**
	 * 登陆
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	String login(ReqLoginDto dto) throws Exception;
	/**
	 * 支付宝授权登陆
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	String loginForAlipay(ReqLoginDto dto) throws Exception ;

	/**
	 * 登出
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	void logout(String token) throws Exception;
	
	/**
	 * 查用户信息
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	User fetchInforByUid(String userId);
	/**
	 * 查用户信息根据支付宝ID
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	User fetchInforByAlipayId(String alipayId);
	/**
	 * 修改用户信息
	 * 
	 * @param token
	 * @return
	 * @throws AppException 
	 * @throws Exception
	 */
	void updateInforByUid(ReqUserInfroDto dto) throws AppException;
	/**
	 * 
	 * 手机号验证是否存在
	 * @return
	 * @throws Exception
	 */
    boolean verificationPhone(String phone);
    /**
	 * 
	 * 手机号注册
	 * @return
	 * @throws Exception
	 */
    public void add(ReqUserDto dto) throws AppException;
    
    /**
	 * 
	 * 公众号注册
	 * @return
	 * @throws Exception
	 */
    public void addWx(ReqUserDto dto) throws AppException;
    public void addWxhg(ReqUserDto dto) throws AppException;
    public void addWxhc(ReqUserDto dto) throws AppException;
    /**
	 * 
	 * 重置密码
	 * @return
	 * @throws Exception
	 */
    void resetPwd(ReqUserDto dto) throws AppException;
    
    
    void updatePic(ReqUserInfroDto dto)throws AppException;

	public  boolean checkOpenId(String openid);
    
    public void updateByUid(ReqWeiXin dto) throws AppException;
    
    public void addWeixin(ReqWeiXin dto) throws AppException;
    
    public String token(String userId) throws Exception;
    public void addAlipayUser(ReqAlipayUserInfroDto dto) throws AppException;
    
    public String findUserId(String openid);
    public void addNewAlipayUser(AlipayUser user) throws AppException;
}
