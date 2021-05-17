package com.mscs.app.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqAlipayUserInfroDto;
import com.mscs.app.common.dto.req.ReqLoginDto;
import com.mscs.app.common.dto.req.ReqUserDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.help.VerificationCodeHelper;
import com.mscs.app.web.controller.ZXTZGGController;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.UserService;


/**
 * @author LiuGuoHui
 * @date 2018-10-09
 *
 */
@Controller
@RequestMapping("reg")
public class RegisterController extends BIZController {
	public static final Logger logger = LoggerFactory
			.getLogger(ZXTZGGController.class);
	@Qualifier("UserServiceImpl")
	@Autowired
	private UserService userService;
	/**
	 * 手机号是否已经被注册
	 * @return
	 */
	@RequestMapping(value = "/verificationPhone", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto verificationPhone(String userId) throws AppException {
		if(!this.isPhone(userId)) 
			return new ResponseDto( ErrorCode.REG_ERROR_PHONE__MACH_MSG,ErrorCode.REG_ERROR_PHONE_MACH_CODE,"");
		if(!userService.verificationPhone(userId)) 
		   return new ResponseDto(ErrorCode.REG_ERROR_PHONE_MSG,ErrorCode.REG_ERROR_PHONE_CODE, "");
		return ResponseDto.buildSuccess();
	}
	
	/**
	 * 手机号注册
	 * @return
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto add(ReqUserDto dto) throws Exception {
		//1.首先验证短信验证码是否正确
		int falg=this.valid(dto.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}
		userService.add(dto);
		return ResponseDto.buildSuccess();
	}
	
	/**
	 * 公众号自动注册
	 * @return
	 */
	@RequestMapping(value = "/addWx", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto addWx(ReqUserDto dto,HttpSession httpSession) throws Exception {
		int falg=this.valid(dto.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}
		Object ido = httpSession.getAttribute("openidnh");
		if(ido != null) {
			String openid = ido.toString();
			dto.setOpenid(openid);
			userService.addWx(dto);
		}
		
		return ResponseDto.buildSuccess();
	}
	@RequestMapping(value = "/addWxhg", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto addWxhg(ReqUserDto dto,HttpSession httpSession) throws Exception {
		if (this.valid(dto.getCode()) == 0)
			throw new AppException(ErrorCode.CHECKCODE_ERROR_CODE,
					ErrorCode.CHECKCODE_ERROR_MSG);
		Object ido = httpSession.getAttribute("openidhg");
		if(ido != null) {
			String openid = ido.toString();
			dto.setOpenid(openid);
			userService.addWxhg(dto);
		}
		
		return ResponseDto.buildSuccess();
	}
	
	@RequestMapping(value = "/addWxhc", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto addWxhc(ReqUserDto dto,HttpSession httpSession) throws Exception {
		if (this.valid(dto.getCode()) == 0)
			throw new AppException(ErrorCode.CHECKCODE_ERROR_CODE,
					ErrorCode.CHECKCODE_ERROR_MSG);
		Object ido = httpSession.getAttribute("openidhc");
		if(ido == null) {
			ido = "";
		}
		String openid = ido.toString();
		dto.setOpenid(openid);
		userService.addWxhc(dto);
		
		return ResponseDto.buildSuccess();
	}
	
	/**
	 *验证短信验证码是否正确
	 *@param 验证码
	 * @return
	 */
	@RequestMapping(value = "/SMSCode", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto SMSCode(ReqUserDto dto) throws Exception {
		//1.首先验证短信验证码是否正确
		int falg=this.valid(dto.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}
		return ResponseDto.buildSuccess();
	}
	
	/**
	 *重置密码
	 *@param 手机号+密码+验证码
	 * @return
	 */
	@RequestMapping(value = "/resetPwd", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ResponseDto resetPwd(ReqUserDto dto) throws Exception {
		if(!this.isPhone(dto.getUserId())) 
			return new ResponseDto(ErrorCode.REG_ERROR_PHONE__MACH_MSG,ErrorCode.REG_ERROR_PHONE_MACH_CODE,"");
		int falg=this.valid(dto.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}
		userService.resetPwd(dto);
		return ResponseDto.buildSuccess();
	}
	/**
	 * 
	 * @Title: addAlipayUser   
	 * @Description: TODO(支付宝登录授权绑定手机号)   
	 * @param: @throws AppException      
	 * @return: ResponseDto
	 * @author: LiuGuoHui      
	 * @throws
	 */
	@RequestMapping(value = "/addAlipayUser", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResponseDto addAlipayUser(ReqAlipayUserInfroDto dto) throws Exception {
		//首先判断手机号格式是否正确
		System.err.println("alipay绑定手机号");
		if(!this.isPhone(dto.getUserId())) 
			return new ResponseDto(ErrorCode.REG_ERROR_PHONE__MACH_MSG,ErrorCode.REG_ERROR_PHONE_MACH_CODE, "");
		//首先判断验证码是否正确
		/*int falg=this.valid(dto.getVerificationCode());
		if (falg== 0) {
			throw new AppException(ErrorCode.REGMISS_ERROR_CODE, ErrorCode.REGMISS_ERROR_MSG);
		}else if(falg==-1){
			throw new AppException(ErrorCode.REG_ERROR_CODE, ErrorCode.REG_ERROR_MSG);
		}*/
		userService.addAlipayUser(dto);
		//得到token
		ReqLoginDto login = new ReqLoginDto();
		login.setUsername(dto.getUserId());
		String token = userService.loginForAlipay(login);
		return ResponseDto.buildSuccess().setData(token);
	}
	/**
	 * 1 ok 0 not ok
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	private int valid(String code) throws IOException {
		//System.out.println(new Date());
		String sessionCheckCode = (String) getSession().getAttribute("LOGIN_CHECKCODE_");
		//System.out.println("sessionCheckCode"+sessionCheckCode);
		//System.out.println("sessionTime"+this.getSession().getMaxInactiveInterval());
		int flag = VerificationCodeHelper.checkIdentifyingCode(
				sessionCheckCode, code);
		/*if(flag!=-1) {
			System.out.println("删除session数据");
			getSession().removeAttribute("code");
			logger.warn("SESSION ID: {}删除SESSION中保存到验证码", getSession().getId());
		}*/
		return flag;
	}
	 /**
     * 电话号码验证
     * 
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null;
        Matcher m = null;
        boolean b = false;  
        p1 = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");  // 验证手机号的
        if(str.length() >9)
        {   m = p1.matcher(str);
            b = m.matches();  
        } 
        return b;
    }
}
