package com.mscs.app.common.help;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 验证码相关 手机验证码 短信验证码
 * 
 * 
 * @author hechunyang
 *
 */
public class VerificationCodeHelper {
	private static Logger logger = LoggerFactory.getLogger(VerificationCodeHelper.class);

	/**
	 * 1 輸入正確 0 輸入錯誤
	 * 
	 * @param sessionCode
	 * @param inputCode
	 * @return
	 */
	public static int checkIdentifyingCode(String sessionCode, String inputCode) {
		if (StringUtils.isBlank(sessionCode) || StringUtils.isBlank(inputCode)) {
			logger.warn("输入验证码或者本地验证码已经失效" + "sessionCode:" + sessionCode + "inputCode:" + inputCode);
			return 0;
		}
		String sc = sessionCode.toLowerCase();
		String ic = inputCode.toLowerCase();
		logger.info("SESSION中存放的登陆验证码【】" + sc + "输入的登陆验证码【】" + ic);
		if (StringUtils.equals(sc, ic)) {
			return 1;
		}
		return -1;//没有失效但是不相等的情况
	}
}
