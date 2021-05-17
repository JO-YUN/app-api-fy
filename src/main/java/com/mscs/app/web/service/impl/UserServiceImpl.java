package com.mscs.app.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.convert.UserInforConverter;
import com.mscs.app.common.dto.req.ReqAlipayUserInfroDto;
import com.mscs.app.common.dto.req.ReqLoginDto;
import com.mscs.app.common.dto.req.ReqUserDto;
import com.mscs.app.common.dto.req.ReqUserInfroDto;
import com.mscs.app.common.dto.req.ReqWeiXin;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.help.EntityValidateHelper;
import com.mscs.app.common.job.CleanDataJob;
import com.mscs.app.common.util.DateTimeUtils;
import com.mscs.app.common.util.MD5Utils;
import com.mscs.app.common.util.TimeUtil;
import com.mscs.app.web.dao.StudentMapper;
import com.mscs.app.web.dao.TeacherMapper;
import com.mscs.app.web.dao.TokenMapper;
import com.mscs.app.web.dao.UserMapper;
import com.mscs.app.web.model.AlipayUser;
import com.mscs.app.web.model.Token;
import com.mscs.app.web.model.User;
import com.mscs.app.web.service.FileService;
import com.mscs.app.web.service.UserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

	public static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TokenMapper tokenMapper;

	@Autowired
	private TeacherMapper tMapper;

	@Autowired
	private StudentMapper sMapper;
	@Autowired
	private FileService fileService;
	/**
	 * 登陆--》认证--》认证成功--》返回Token 更新token 登陆--
	 * 
	 * 
	 * 》认证--》认证失败--》返回空
	 * 
	 * @throws Exception
	 */
	@Transactional
	@Override
	public String login(ReqLoginDto dto) throws Exception {
		EntityValidateHelper.checkEntity(dto);
		
		if (!Verification(dto.getUsername(), dto.getPassword()))
			throw new AppException(ErrorCode.AUTH_FAIL_CODE,
					ErrorCode.AUTH_FAIL_MSG);
		
		this.setLoginAppState(dto.getUsername());
		
		String token = userMapper.TokenByUserId(dto.getUsername());
		
		if(StringUtils.isBlank(token))
		{
			token = UUID.randomUUID().toString().replaceAll("-", "");
			Token userToken = new Token(dto.getUsername(), token, DateTimeUtils
					.now().toDateTimeString());
			tokenMapper.insert(userToken);
		}
		
		return token;
	}
	
	@Override
	 public String token(String userId) throws Exception {
		if(StringUtils.isBlank(userId))
		{
			throw new AppException(ErrorCode.ERROR_CODE,
					ErrorCode.ERROR_MSG);
		}
        String token = userMapper.TokenByUserId(userId);
		
		if(StringUtils.isBlank(token))
		{
			token = UUID.randomUUID().toString().replaceAll("-", "");
			Token userToken = new Token(userId, token, DateTimeUtils
					.now().toDateTimeString());
			tokenMapper.insert(userToken);
		}
		return token;
	}
	
	
	
	
	public boolean Verification(String userId, String passWord) throws AppException {
		boolean flag = false;
		try {
			System.out.println(passWord);
			String pass = MD5Utils.getMD5Token32(passWord);
			System.out.println(pass);
			String realPass = userMapper.VerifitPass(userId);
			System.out.println(realPass);
			if(pass.equals(realPass))
			{
				flag = true;
			}
			else
			{
				flag = false;
				throw new AppException(ErrorCode.ERROR_CODE,
						ErrorCode.ERROR_MSG);
			}
		} catch (Exception e) {
			logger.error("用户名密码错误", e);
			throw new AppException(ErrorCode.AUTH_FAIL_CODE,
					ErrorCode.AUTH_FAIL_MSG);
		}
		return flag;
	}
	@Override
	public void logout(String token) throws AppException {
		try {
			tokenMapper.deleteRecordByToken(token);
		} catch (Exception e) {
			logger.error("删除Token执行错误", e);
			throw new AppException(ErrorCode.LOGOUT_ERROR_CODE,
					ErrorCode.LOGOUT_ERROR_MSG);
		}
	}


	/**
	 * appState 1 已经在APP登陆 0 未在app登陆
	 * 
	 * @param userId
	 */
	@Transactional
	private void setLoginAppState(String userId) {
		logger.info("插入用户登陆APP情况信息:{}", userId);
		User u = userMapper.findUserByUserId(userId);
		if (u.getAppState() == 1)
			return;
		else {
			tMapper.updateByUid(userId);
		}
		/*if (u.getType() == 1) {
			sMapper.updateByUid(userId);
		} */
	}
	/**
	 * 查询用户信息
	 * <p>Title: fetchInfor</p>   
	 * <p>Description: </p>   
	 * @param userid
	 * @return   
	 * @see com.hrbwmxx.app.service.UserService#fetchInfor(java.lang.String)
	 */
	@Override
	public User fetchInforByUid(String userid) {
		User infors =userMapper.findUserByUserId(userid);
		//加入图片的附件信息
		if (infors!=null){
			Map<String, String>  map_request=new HashMap<String, String>();
			if (infors.getPic()!=null){
			map_request.put("attachId",infors.getPic());
			List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
			infors.setList_file_pic(list_file_pic);
			}
		}
	  return infors;
	}
	/**
	 * 
	 * <p>Title: updateInfor</p>   
	 * <p>Description: </p>   
	 * @param infor   
	 * @throws AppException 
	 * @see com.hrbwmxx.app.service.UserService#updateInfor(com.hrbwmxx.app.model.PersonalInfor)
	 */
	@Transactional
	public void updateInforByUid(ReqUserInfroDto dto) throws AppException  {
		
		try {
			User user =UserInforConverter.buildUserInforObj(dto);
			userMapper.updateInforByUid(user);
		} catch (Exception e) {
			logger.error("修改信息异常", e);
			throw new AppException(ErrorCode.ADVICE_FAIL_CODE, ErrorCode.ADVICE_FAIL_MSG);
		}
		
	}
	/**
	 * 
	 * 手机号验证是否存在
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean verificationPhone(String phone) {
		boolean falg=true;
		User user=userMapper.findUserByUserId(phone);
		if(user!=null) {
			falg=false;
		}
		return falg;
	}
	/**
	 * 
	 * 手机号注册
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void add(ReqUserDto dto) throws AppException {
		//1.增加操作
		User newUser=new User();
		newUser.setUserId(dto.getUserId());
		String pass = MD5Utils.getMD5Token32(dto.getPassWord());
		newUser.setPassWord(pass);
		newUser.setGender("1");
		newUser.setState(1);
		newUser.setAppState(0);
		newUser.setWid(TimeUtil.getTime());
	
		userMapper.add(newUser);
	}
	
	/**
	 * 
	 * 手机号注册
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void addWx(ReqUserDto dto) throws AppException {
		//1.增加操作
		User newUser=new User();
		newUser.setUserId(dto.getUserId());
		newUser.setGender("1");
		newUser.setState(1);
		newUser.setAppState(0);
		newUser.setWid(TimeUtil.getTime());
		newUser.setName(dto.getName());
		newUser.setOpenid(dto.getOpenid());
		newUser.setSfzjh(dto.getSfzjh());
		userMapper.addWx(newUser);
	}
	@Transactional
	@Override
	public void addWxhg(ReqUserDto dto) throws AppException {
		//1.增加操作
		User newUser=new User();
		newUser.setUserId(dto.getUserId());
		newUser.setGender("1");
		newUser.setState(1);
		newUser.setAppState(0);
		newUser.setWid(TimeUtil.getTime());
		newUser.setName(dto.getName());
		newUser.setOpenid(dto.getOpenid());
		newUser.setSfzjh(dto.getSfzjh());
		newUser.setCarid(dto.getCarid());
		userMapper.addWxhg(newUser);
	}
	@Transactional
	@Override
	public void addWxhc(ReqUserDto dto) throws AppException {
		//1.增加操作
		User newUser=new User();
		newUser.setUserId(dto.getUserId());
		newUser.setGender("1");
		newUser.setState(1);
		newUser.setAppState(0);
		newUser.setWid(TimeUtil.getTime());
		newUser.setName(dto.getName());
		newUser.setOpenid(dto.getOpenid());
		newUser.setSfzjh(dto.getSfzjh());
		userMapper.addWxhc(newUser);
	}
	/**
	 * 
	 * 重置密码
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void resetPwd(ReqUserDto dto) throws AppException {
		User user=new User();
		user.setUserId(dto.getUserId());
		String pass = MD5Utils.getMD5Token32(dto.getPassWord());
		user.setPassWord(pass);	
		userMapper.resetPwd(user);
	}
	/**
	 * 
	 * 修改该用户的pic
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void updatePic(ReqUserInfroDto dto) throws AppException {
		User user=new User();
		user.setUserId(dto.getUserId());
		user.setPic(dto.getAttachId());
		//检查之前是否有图片
		User oldUser=userMapper.findUserByUserId(dto.getUserId());
		List<Map<String, String>> list_file_pic=new ArrayList<Map<String, String>>();
		if (oldUser!=null){
			Map<String, String>  map_request=new HashMap<String, String>();
			if (oldUser.getPic()!=null){
			map_request.put("attachId",oldUser.getPic());
			list_file_pic = fileService.queryAttrList(map_request);
			oldUser.setList_file_pic(list_file_pic);
			}
		}
		for(Map<String, String> pics:list_file_pic) {
			if(pics.get("attachId")!=null) {//说明原来有图片
				//首先删除原图片
				try{
					 fileService.deleteStateInvalidByAttachId(oldUser.getList_file_pic().get(0).get("attachId"));
					 //清除文件
					 CleanDataJob.deleteFile(oldUser.getList_file_pic().get(0).get("physicalPath"));	
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (dto.getAttachId()!=null){
			//1修改该用户的图片
			userMapper.updatePic(user);
			//2修改附件表的状态
			fileService.updateFileStateByIds(dto.getAttachId(), "1");
		}
	}

	@Override
	public User fetchInforByAlipayId(String alipayId) {
		User infors =userMapper.fetchInforByAlipayId(alipayId);
		System.out.println("kkkkkkkkkkkk");
		//加入图片的附件信息
		if (infors!=null){
			Map<String, String>  map_request=new HashMap<String, String>();
			if (infors.getPic()!=null){
			map_request.put("attachId",infors.getPic());
			List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
			infors.setList_file_pic(list_file_pic);
			}
		}
	  return infors;
	}
	@Transactional
	@Override
	public String loginForAlipay(ReqLoginDto dto) throws Exception {
		
		this.setLoginAppState(dto.getUsername());//设置登录状态
		String token = userMapper.TokenByUserId(dto.getUsername());
		if(StringUtils.isBlank(token))
		{
			token = UUID.randomUUID().toString().replaceAll("-", "");
			Token userToken = new Token(dto.getUsername(), token, DateTimeUtils
					.now().toDateTimeString());
			tokenMapper.insert(userToken);
		}
		
		return token;
	}

	
	/**
	 * 
	 * openid 是否存在
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkOpenId(String openid) {
		boolean falg=true;
		User user=userMapper.checkOpenId(openid);
		if(user!=null) {
			falg=false;
		}
		return falg;
	}

	public String findUserId(String openid) {
		String userId="";
		User user=userMapper.checkOpenId(openid);
		if(user!=null) {
			userId = user.getUserId();
		}
		
		return userId;
	}
	
	@Override
	public void updateByUid(ReqWeiXin dto) throws AppException {
		try {
			dto.setXbdm(dto.getSex());
		    userMapper.updateByUid(dto);
		 } catch (Exception e) {
		logger.error("根据手机号更新数据库微信获取信息", e);
		throw new AppException(ErrorCode.ADVICE_FAIL_CODE, ErrorCode.ADVICE_FAIL_MSG);
	     }
	}

	@Override
	public void addWeixin(ReqWeiXin dto) throws AppException {
		String wid = TimeUtil.getTime();
		dto.setXbdm(dto.getSex());
		dto.setWid(wid);
		dto.setState("1");
		dto.setPassWord(MD5Utils.getMD5Token32("111111"));//默认密码
		userMapper.addWeixin(dto);
	}
	@Transactional
	@Override
	public void addAlipayUser(ReqAlipayUserInfroDto dto) throws AppException {
		//判断当前绑定的手机号是否已经在数据库里面绑定了微信
		//如果当前手机号已经绑定了微信则走update，如果什么都没有绑定则走insert
		//根据传过来的alipayId得到数据库里面的支付宝数据
		User user=userMapper.fetchInforByAlipayId(dto.getAlipayId());
		userMapper.deleteUserByAlipayId(dto.getAlipayId());
		if(!this.verificationPhone(dto.getUserId())) {
			//已经存在的手机号
			dto.setAddress(user.getAlipayadress());
			dto.setAlipayheadurl(user.getAlipayheadurl());
			dto.setAlipayId(user.getAlipayid());
			dto.setCity(user.getCity());
			dto.setDegree(user.getAlipaydegree());
			dto.setEmail(user.getAlipayemail());
			dto.setMobile(user.getAlipaymobile());
			dto.setNickName(user.getAlipaynickname());
			dto.setProvince(user.getProvince());
			dto.setUserName(user.getAlipayusername());
			dto.setXbdm(user.getGender());
			userMapper.updateAlipayUser(dto);
		}else {
			//不存在的手机号
			String wid = TimeUtil.getTime();
			dto.setXbdm(user.getGender());
			dto.setWid(wid);
			dto.setState("1");
			dto.setAddress(user.getAlipayadress());
			dto.setAlipayheadurl(user.getAlipayheadurl());
			dto.setAlipayId(user.getAlipayid());
			dto.setCity(user.getCity());
			dto.setDegree(user.getAlipaydegree());
			dto.setEmail(user.getAlipayemail());
			dto.setMobile(user.getAlipaymobile());
			dto.setNickName(user.getAlipaynickname());
			dto.setProvince(user.getProvince());
			dto.setUserName(user.getAlipayusername());
			dto.setPassWord(MD5Utils.getMD5Token32("111111"));//默认密码
		    userMapper.addAlipayUser(dto);
		}
		
	}		
	@Transactional
	@Override
	public void addNewAlipayUser(AlipayUser user) throws AppException {
		ReqAlipayUserInfroDto dto=new ReqAlipayUserInfroDto();
		dto.setAddress(user.getAddress());
		dto.setAlipayheadurl(user.getAlipayheadurl());
		dto.setAlipayId(user.getAlipayId());
		dto.setCity(user.getCity());
		dto.setDegree(user.getDegree());
		dto.setEmail(user.getEmail());
		dto.setMobile(user.getMobile());
		dto.setNickName(user.getNickName());
		dto.setProvince(user.getProvince());
		dto.setUserName(user.getUserName());
		String wid = TimeUtil.getTime();
		dto.setXbdm(user.getSex());
		dto.setWid(wid);
		dto.setUserId("-1");
		dto.setState("0");
		userMapper.addAlipayUser(dto);
	}
}
