package com.mscs.app.common.help;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.exception.AppException;
/*import com.wiscom.ldapvalidate.*;*/

/**
 * 
 * @author hechunyang
 * @date 2018年5月15日
 * @remark TODO
 */
public class LdapHelper {

	public static Logger logger = LoggerFactory.getLogger(LdapHelper.class);

	public static final String LDAPPATH = "/config/ldap.properties";

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	/*public static boolean authUserPwd(String username, String password)
			throws AppException {
		try {
			return getLdap().checkPassword(username, password);
		} catch (Exception e) {
			throw new AppException(ErrorCode.AUTH_FAIL_CODE, e.getMessage());
		}
	}

	*//**
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public static ldapCheck getLdap() throws Exception {
		File cfgFile = ResourceUtils.getFile("classpath:" + LDAPPATH);
		ldapCheck instance = ldapCheck.getInstance(cfgFile.getAbsolutePath());
		logger.info("初始化登陆instance" + instance);
		return instance;
	}*/

}
