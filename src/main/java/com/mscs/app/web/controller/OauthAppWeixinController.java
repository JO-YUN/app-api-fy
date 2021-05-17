package com.mscs.app.web.controller;

import java.io.IOException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqWeiXin;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.help.VerificationCodeHelper;
import com.mscs.app.common.util.Constant;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.UserService;

@Controller
@RequestMapping("/OauthAppweixin")
public class OauthAppWeixinController extends BIZController {
    
    private static Logger log = LoggerFactory.getLogger(OauthAppWeixinController.class);
    
	@Qualifier("UserServiceImpl")
	@Autowired
	private UserService userService;
    /**
    * @MethodName: checkOpenid 
    * @description : TODO
    * @author：刘威巍 
    * @date： 2018年10月30日 下午11:14:23
    * @param wx
    * @return ResponseDto
     * @throws Exception 
     */
    @RequestMapping("/checkOpenId")
    @ResponseBody
    public ResponseDto checkOpenid(ReqWeiXin wx) throws Exception{
    	if(wx ==null||wx.getOpenid().isEmpty())
			return new ResponseDto(ErrorCode.ERROR_OPENID_CODE, ErrorCode.ERROR_OPENID_MSG,"");
    	if(userService.checkOpenId(wx.getOpenid()))
    	{
 		 return ResponseDto.buildSuccess().setData(wx);
    	}
    	userService.updateByUid(wx);
    	String userId=userService.findUserId(wx.getOpenid());
        String token = userService.token(userId);
    	return ResponseDto.buildFail().setData(token);
    }
    /**
    * @MethodName: bindPhone 
    * @description : 
    * 1.验证手机号是否规则
    * 2.验证码是否正确
    * 3.绑定手机号库中是否有，如果有更新；否则进行新增
    * 4.生成登录token
    * @author：刘威巍 
    * @date： 2018年10月30日 下午11:15:11
    * @param wx
    * @return ResponseDto
     * @throws Exception 
     */
    @RequestMapping("/bindPhone")
    @ResponseBody
    public ResponseDto bindPhone(ReqWeiXin wx) throws Exception{
    	//传入参数是否为空，为空默认走入系统异常捕捉
    	if(wx==null || wx.getUserId().isEmpty())
    	//判断手机号是否是规则
    	if(!Constant.isPhone(wx.getUserId())) 
			return new ResponseDto(ErrorCode.REG_ERROR_PHONE_MACH_CODE, ErrorCode.REG_ERROR_PHONE__MACH_MSG,"");
		//首先验证短信验证码是否正确
		int falg=this.valid(wx.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}
    	//手机号被注册了进行更新
    	if(!userService.verificationPhone(wx.getUserId())) 
    		userService.updateByUid(wx);
    	else
    		userService.addWeixin(wx);
    	//新增用户
    	String token = userService.token(wx.getUserId());
    	
		wx.setToken(token);
		
    	return ResponseDto.buildSuccess().setData(wx);
    }
    
    /**
	 * 1 ok 0 not ok
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	private int valid(String code) throws IOException {
		System.out.println(new Date());
		String sessionCheckCode = (String) getSession().getAttribute("LOGIN_CHECKCODE_");
		System.out.println("sessionCheckCode"+sessionCheckCode);
		System.out.println("sessionTime"+this.getSession().getMaxInactiveInterval());
		int flag = VerificationCodeHelper.checkIdentifyingCode(
				sessionCheckCode, code);
		return flag;
	}
	
}
