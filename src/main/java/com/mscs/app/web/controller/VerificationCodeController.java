package com.mscs.app.web.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.util.ImageUtil;
import com.mscs.app.web.controller.base.BIZController;


/**
 * 登录相关controller
 * 
 * @author hechunyang
 *
 */
@Controller
@RequestMapping
public class VerificationCodeController extends BIZController {

	public static Logger logger = LoggerFactory
			.getLogger(VerificationCodeController.class);

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/code/get", method = RequestMethod.GET)
	@ResponseBody
	public void getCode() throws Exception {
		Object[] objs = ImageUtil.createImage();
		getSession().setAttribute("LOGIN_CHECKCODE_", objs[0]);// 设置登陆验证码
		logger.info("SESSION ID: {} 设置验证码:{}", getSession().getId(), objs[0]);
		BufferedImage image = (BufferedImage) objs[1];
		getResponse().setContentType("image/png");
		OutputStream os = this.getResponse().getOutputStream();
		ImageIO.write(image, "png", os);
	}
}
