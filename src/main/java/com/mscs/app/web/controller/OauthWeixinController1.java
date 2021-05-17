package com.mscs.app.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqWeiXin;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.help.VerificationCodeHelper;
import com.mscs.app.common.util.Constant;
import com.mscs.app.common.util.OauthWeixin;
import com.mscs.app.common.util.TokenUtil;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/weixin111")
public class OauthWeixinController1 extends BIZController {
    //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
    private static final String SESSION_STATE = "_SESSION_STATE_WEIXIN_";
    private static Logger log = LoggerFactory.getLogger(OauthWeixinController1.class);
	@Qualifier("UserServiceImpl")
	@Autowired
	private UserService userService;
	
	@RequestMapping("/callback")
    @ResponseBody
    public String callback(HttpServletRequest request){
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String openid = request.getParameter("openid");
        // 取消了授权
        if (StringUtils.isBlank(state)||StringUtils.isBlank(code)){
            return "取消了授权";
        }
        //清除state以防下次登录授权失败
        //session.removeAttribute(SESSION_STATE);
        //获取用户信息
        try{
            String accessToken = OauthWeixin.me().getTokenByCode(code);
            System.out.println("controller:accessToken"+accessToken);
            System.out.println("controller:openid"+openid);
            //
            JSONObject jsStr = JSONObject.parseObject(accessToken); 
            ReqWeiXin wx = (ReqWeiXin) JSONObject.toJavaObject(jsStr,ReqWeiXin.class);
            String userInfo = OauthWeixin.me().getUserInfo(wx.getAccess_token(),wx.getOpenid());
            log.debug(userInfo.toString());
            System.out.println("controller:userInfo"+userInfo);
            JSONObject userStr = JSONObject.parseObject(userInfo); 
            ReqWeiXin user = (ReqWeiXin) JSONObject.toJavaObject(userStr,ReqWeiXin.class);

            if(wx ==null||wx.getOpenid().isEmpty())
    			return "发生错误";
        	if(userService.checkOpenId(wx.getOpenid()))
        	{
	        	 Map data = new HashMap();
	        	 data.put("openid", wx.getOpenid());
	        	 data.put("nickname", wx.getNickname());
	        	 data.put("sex", wx.getSex());
	        	 data.put("language", wx.getLanguage());
	        	 data.put("city", wx.getCity());
	        	 data.put("province", wx.getProvince());
	        	 data.put("country", wx.getCountry());
	        	 data.put("headimgurl", wx.getHeadimgurl());
	        	 data.put("unionid", wx.getUserId());
	     		 return "redirect:wechat.html"+data;
        	}
        	userService.updateByUid(wx);
        	String userId=userService.findUserId(wx.getOpenid());
            String token = userService.token(userId);
            
            return "redirect:index.html";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:index.html";
    }

    /**
     * 构造授权请求url
     * @return void    返回类型
     * @throws
     */
    @RequestMapping(value = "login", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
    public ResponseDto index(HttpServletRequest request, HttpServletResponse response){
        String state = TokenUtil.randomState();
        //state就是一个随机数，保证安全
        try {
            String url = OauthWeixin.me().getAuthorizeUrl(state);
            System.out.println(url);
            return ResponseDto.buildSuccess().setData(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ResponseDto.buildSuccess().setData("");
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
    @RequestMapping("/wechat")
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
 		String sessionCheckCode = (String) getSession().getAttribute("code");
 		System.out.println("sessionCheckCode"+sessionCheckCode);
 		System.out.println("sessionTime"+this.getSession().getMaxInactiveInterval());
 		int flag = VerificationCodeHelper.checkIdentifyingCode(
 				sessionCheckCode, code);
 		return flag;
 	}
}
