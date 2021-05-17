package com.mscs.app.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.DaYuService;


/**
 * @author LiuGuoHui
 * @date 2018-10-09
 *
 */
@Controller
@RequestMapping("daYU")
public class DaYUController extends BIZController {
	public static final Logger logger = LoggerFactory.getLogger(DaYUController.class);
	@Qualifier("DaYuServiceImpl")
	@Autowired
	private DaYuService daYuService;

	/**
	 * 发送短信验证码
	 * 
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "sendSMSCode", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseDto sendSMSCode(String userId, String type) throws AppException {
		// 1生成短信验证码
		String code = DaYUController.getCaptcha();
		System.out.println("====" + code);
		String falg = daYuService.sendSMSCode(type, userId, code);// 发送短信验证码
		if (falg.equals("success")) {
			System.out.println("放入到session" + code);
			System.out.println(new Date());
			// session.setMaxInactiveInterval(120);
			this.getSession().setAttribute("LOGIN_CHECKCODE_", code);
		//System.out.println("code" + this.getSession().getAttribute("code"));
		//System.out.println("sessionTime" + this.getSession().getMaxInactiveInterval());
		} else {
			throw new AppException(ErrorCode.DAYU_ERROR_CODE, ErrorCode.DAYU_ERROR_MSG);
		}
		return ResponseDto.buildSuccess();
	}

	// 生成验证码
	public static String getCaptcha() {
		String str = "0,1,2,3,4,5,6,7,8,9";
		String str2[] = str.split(",");// 将字符串以,分割
		Random rand = new Random();// 创建Random类的对象rand
		int index = 0;
		String randStr = "";// 创建内容为空字符串对象randStr
		randStr = "";// 清空字符串对象randStr中的值
		for (int i = 0; i < 4; ++i) {
			index = rand.nextInt(str2.length - 1);// 在0到str2.length-1生成一个伪随机数赋值给index
			randStr += str2[index];// 将对应索引的数组与randStr的变量值相连接
		}
		return randStr;
	}
}
