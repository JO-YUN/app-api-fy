package com.mscs.app.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqLoginDto;
import com.mscs.app.common.util.OrderInfoUtil2_0;
import com.mscs.app.common.util.SystemVar;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.model.AlipayUser;
import com.mscs.app.web.model.User;
import com.mscs.app.web.service.UserService;
import com.mscs.app.web.service.impl.AlipayAPPServiceImpl;
import com.mscs.app.web.service.impl.AlipayLoginService;

/**
 * @author LiuGuoHui
 * @date 2018-10-09
 *
 */
@Controller
@RequestMapping("alipay")
public class AlipayController extends BIZController {
	public static final Logger logger = LoggerFactory.getLogger(AlipayController.class);
	@Autowired
	private AlipayLoginService alipayLoginService;
	@Autowired
	private AlipayAPPServiceImpl alipayAPPService;
	@Qualifier("UserServiceImpl")
	@Autowired
	private UserService userService;
	/**
	 * url是需要经过编码的，这里提供一个在线编码的链接：http://tool.chinaz.com/Tools/urlencode.aspx或者URLEncoder.encode()方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUrl", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseDto getUrl() {
		try {
			System.out.println("生成支付宝授权界面路径--------------------------------------");
			String encode = URLEncoder.encode(SystemVar.PC_REDIRECT_URL, "UTF-8");// 经过编码的回调路径
			// 沙箱环境的url
			/*String url = "https://openauth.alipaydev.com/oauth2/publicAppAuthorize.htm" + "?app_id=" + SystemVar.PC_APP_ID_SX
					+ "&scope=" + SystemVar.SCOPE + "&redirect_uri=" + URLEncoder.encode(SystemVar.PC_REDIRECT_URL_SX, "UTF-8");
			*/
			String url = SystemVar.ALIPAY_URL+ "?app_id=" + SystemVar.PC_APP_ID
					   + "&scope=" + SystemVar.SCOPE + "&redirect_uri=" + encode;
			 return ResponseDto.buildSuccess().setData(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return ResponseDto.buildSuccess().setData("");
		}
	}

	/*
	 * 手机端 url是需要经过编码的，这里提供一个在线编码的链接：http://tool.chinaz.com/Tools/urlencode.
	 * aspx或者URLEncoder.encode()方法
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "getUrlForSJ", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseDto getUrlForSJ() {
		boolean rsa2 =true;
		Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(SystemVar.PID, SystemVar.APP_APP_ID, SystemVar.TARGET_ID, rsa2);
		String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
		String privateKey =SystemVar.APP_PRIVATEKEY;
		String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
		String authInfo = info + "&" + sign;
		return ResponseDto.buildSuccess().setData(authInfo);
	}
	/**
	 * 
	 * 支付宝授权之后获取用户信息--pc
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAlipayInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseDto getAlipayInfo(@RequestParam("authCode") String authCode, @RequestParam("appID") String appID,@RequestParam("scope") String scope,@RequestParam("type") String type) throws Exception {

		System.out.println("支付宝返回信息....................................................");
		// 从request中获取授权信息
		/*
		 * String authCode = request.getParameter("auth_code"); String appID =
		 * request.getParameter("app_id"); String scope = request.getParameter("scope");
		 */
		
		if (StringUtils.isNotEmpty(authCode)) {
			String accessToken="";
			if(type.equals("1")) {//pc支付宝授权
				// 获取access_token
				accessToken= alipayLoginService.getAccessToken(authCode);
			}else {
				accessToken=alipayAPPService.getAccessToken(authCode);
			}
			// 获取用户信息
			if (StringUtils.isNotEmpty(accessToken)) {
				// 获取用户信息
				AlipayUser alipayUser = null;
				if(type.equals("1")) {
					alipayUser = alipayLoginService.getUserInfoByToken(accessToken);
				}else {
					alipayUser = alipayAPPService.getUserInfoByToken(accessToken);
				}
				// 取到用户信息，判断支付宝授权登录是否绑定了手机号 如果绑定了则直接进入主页，如果没有绑定则进入手机号绑定页面
				User user = userService.fetchInforByAlipayId(alipayUser.getAlipayId());
				if (user!= null && !"".equals(user)) {
					System.out.println("userID"+user.getUserId());
					if(!user.getUserId().equals("-1")) {
						// 已经绑定了APP直接去生成token 回到登录成功页面
						System.out.println("绑定额token");
						ReqLoginDto dto = new ReqLoginDto();
						dto.setUsername(user.getUserId());
						String token = userService.loginForAlipay(dto);
						alipayUser.setToken(token);
					}
				}else {
					//先将数据存入到数据库中
					userService.addNewAlipayUser(alipayUser);
				}
				// 最后将token返回到前台,前端可以通过判断token的值是否为空，来确定是否需要进入手机号绑定页面
				// 没有绑定APP，直接跳转到手机号绑定页面，将支付宝的信息传递给前台
				return ResponseDto.buildSuccess().setData(alipayUser);
			} else {
				System.out.println("支付宝授权失败");
				return new ResponseDto("支付宝授权失败", "500", "");
			}
		} else {
			System.out.println("取消了支付宝授权");
			return new ResponseDto("取消了支付宝授权", "500", "");
		}

	}

	/**
	 * 
	 * 支付宝授权之后的回调地址(作废)
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAuthInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public String getAuthInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		  System.out.println("支付宝返回信息...................................................."); 
		  //从request中获取授权信息
		  String authCode = request.getParameter("auth_code"); 
		  String appID = request.getParameter("app_id"); 
		  String scope =request.getParameter("scope");
		  String url="/mscs-app/page/blank.html?auth_code="+authCode+"&app_id="+appID+"&scope="+scope;
		  System.out.println("url----"+url);
		  return "redirect:http://192.168.5.13"+url; //return "redirect:"+url;
		  //return "redirect:http://www.baidu.com"; 
	}
}
