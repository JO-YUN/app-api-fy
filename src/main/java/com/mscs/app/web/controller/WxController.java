package com.mscs.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mscs.app.common.util.Constant;
import com.mscs.app.common.util.WXAuthUtil;
import com.mscs.app.web.dao.UserMapper;

/*import org.apache.log4j.Logger;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
/**
 * @program: integral_wall
 * @description: 微信登录授权相关
 * @author: Chenjf
 * @create: 2018-09-25 17:40
 **/
@RestController
public class WxController {
	@Autowired
	private UserMapper userMapper;

    //private static final Logger logger = Logger.getLogger(WxController.class);

    /** 公众号微信登录授权
    * @Description:
    * @Param: [request, response]
    * @return: java.lang.String
     * @throws IOException 
    * @Author: Chenjf
    * @Date: 2018/9/27
    */
    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
    public String wxLogin(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) throws ParseException, IOException {
        //这个url的域名必须要进行再公众号中进行注册验证，这个地址是成功后的回调地址
    	String n = Math.random()+"";
        String backUrl= Constant.server_url + "/callBack?n="+n;
        // 第一步：用户同意授权，获取code
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WXAuthUtil.APPID_NH
                + "&redirect_uri="+ URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=nh#wechat_redirect";
        response.sendRedirect(url);
        return null;//必须重定向，否则不能成功
    }
    
    @RequestMapping(value = "/wxLoginhg", method = RequestMethod.GET)
    public String wxLoginhg(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) throws ParseException, IOException {
        //这个url的域名必须要进行再公众号中进行注册验证，这个地址是成功后的回调地址
    	String n = Math.random()+"";
        String backUrl= Constant.server_url + "/callBack?n="+n;
        // 第一步：用户同意授权，获取code
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WXAuthUtil.APPID_HG
                + "&redirect_uri="+ URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=hg#wechat_redirect";
        response.sendRedirect(url);
        return null;//必须重定向，否则不能成功
    }
    
    @RequestMapping(value = "/wxLoginhc", method = RequestMethod.GET)
    public String wxLoginhc(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) throws ParseException, IOException {
        //这个url的域名必须要进行再公众号中进行注册验证，这个地址是成功后的回调地址
    	String n = Math.random()+"";
        String backUrl= Constant.server_url + "/callBack?n="+n;
        // 第一步：用户同意授权，获取code
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WXAuthUtil.APPID_HC
                + "&redirect_uri="+ URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=hc#wechat_redirect";
        response.sendRedirect(url);
        return null;//必须重定向，否则不能成功
    }

    /**
    * @Description: 公众号微信登录授权回调函数
    * @Param: [modelMap, req, resp]
    * @return: java.lang.String
    * @Author: Chenjf
    * @Date: 2018/9/25
    */
    @RequestMapping(value = "/callBack", method = RequestMethod.GET)
    public String callBack(ModelMap modelMap, HttpServletRequest req, HttpServletResponse resp,HttpSession httpSession) throws ServletException, IOException {

       //start 获取微信用户基本信息
       String code =req.getParameter("code");
       String state =req.getParameter("state");
       System.out.println("code:"+code);
       //第二步：通过code换取网页授权access_token
       String appid= WXAuthUtil.APPID_NH;
       String appsecret =WXAuthUtil.APPSECRET_NH;
       if("nh".equals(state)) {
    	   appid = WXAuthUtil.APPID_NH;
    	   appsecret = WXAuthUtil.APPSECRET_NH;
       }else if("hc".equals(state)) {
    	   appid = WXAuthUtil.APPID_HC;
    	   appsecret = WXAuthUtil.APPSECRET_HC;
       }else if("hg".equals(state)) {
    	   appid = WXAuthUtil.APPID_HG;
    	   appsecret = WXAuthUtil.APPSECRET_HG;
       }
       
       String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid
               + "&secret="+appsecret
               + "&code="+code
               + "&grant_type=authorization_code";
       
       JSONObject jsonObject = WXAuthUtil.doGetJson(url);
       String openid = jsonObject.getString("openid");
       String access_token = jsonObject.getString("access_token");
       String refresh_token = jsonObject.getString("refresh_token");
       
       httpSession.setAttribute("openid"+state, openid);
		/*
		 * //第五步验证access_token是否失效；展示都不需要 String
		 * chickUrl="https://api.weixin.qq.com/sns/auth?access_token="+access_token+
		 * "&openid="+openid; JSONObject chickuserInfo = WXAuthUtil.doGetJson(chickUrl);
		 * System.out.println(chickuserInfo.toString());
		 * if(!"0".equals(chickuserInfo.getString("errcode"))){ //
		 * 第三步：刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki， String
		 * refreshTokenUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+
		 * openid+"&grant_type=refresh_token&refresh_token="+refresh_token; JSONObject
		 * refreshInfo = WXAuthUtil.doGetJson(chickUrl);
		 * System.out.println(refreshInfo.toString());
		 * access_token=refreshInfo.getString("access_token"); }
		 */

       // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
		/*
		 * String infoUrl =
		 * "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token +
		 * "&openid="+openid + "&lang=zh_CN"; System.out.println("infoUrl:"+infoUrl);
		 * JSONObject userInfo = WXAuthUtil.doGetJson(infoUrl);
		 * System.out.println("JSON-----"+userInfo.toString());
		 * System.out.println("名字-----"+userInfo.getString("nickname"));
		 * System.out.println("头像-----"+userInfo.getString("headimgurl"));
		 */

       //end 获取微信用户基本信息
       
       //获取到用户信息后就可以进行重定向，走自己的业务逻辑了。。。。。。
       //接来的逻辑就是你系统逻辑了，请自由发挥
       String urlrsult = "bmjf/pay";
		/*
		 * User userInfo = userMapper.checkOpenIdAndS(openid); if(userInfo != null) {
		 * String sfzjh = userInfo.getSfzjh(); if(sfzjh == null || sfzjh.equals("")) {
		 * urlrsult = "details.html"; } }else { urlrsult = "details.html"; }
		 */
      
       resp.sendRedirect(urlrsult);
       return null;
    }

}
