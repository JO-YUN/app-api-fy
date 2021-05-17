package com.mscs.app.common;

public class ErrorCode {

	// 用户认证失败
	public static final String AUTH_FAIL_CODE = "1000";
	public static final String AUTH_FAIL_MSG = "登陆认证失败";

	// token失效
	public static final String TOKEN_FAIL_CODE = "1001";
	public static final String TOKEN_FAIL_MSG = "Token值获取失败";

	//
	public static final String TOKENNULL_FAIL_CODE = "1002";
	public static final String TOKENNULL_FAIL_MSG = "传递的Token值为空";

	// 注销登陆失败
	public static final String LOGOUT_ERROR_CODE = "1003";
	public static final String LOGOUT_ERROR_MSG = "注销登陆失败";

	// 参数错误
	public static final String PARAM_VALIDATE_CODE = "1004";
	public static final String PARAM_VALIDATE_MSG = "参数校验错误";

	// 用户获取异常
	public static final String UID_FAIL_CODE = "1005";
	public static final String UID_FAIL_MSG = "用户信息获取失败:未找到该用户";

	// 意见处理失败
	public static final String ADVICE_FAIL_CODE = "1006";
	public static final String ADVICE_FAIL_MSG = "新增意见错误";
	// 应用获取失败
	public static final String APP_ERROR_CODE = "1007";
	public static final String APP_ERROR_MSG = "APP获取失败";
	// 收藏APP出错
	public static final String COL_APP_ERROR_CODE = "1008";
	public static final String COL_APP_ERROR_MSG = "添加收藏APP出错";

	public static final String COL_APP_ERROR_CODE1 = "10081";
	public static final String COL_APP_ERROR_MSG1 = "APP已经被收藏,无法重复收藏";
	// 取消收藏APP出错
	public static final String DEL_APP_ERROR_CODE = "1009";
	public static final String DEL_APP_ERROR_MSG = "取消收藏APP出错";

	// 取消收藏APP出错
	public static final String CHECKCODE_ERROR_CODE = "1010";
	public static final String CHECKCODE_ERROR_MSG = "验证码校验失败";
	// 注册手机验证码校验失败
	public static final String REGMISS_ERROR_CODE = "1010";
	public static final String REGMISS_ERROR_MSG = "手机短信验证码已经失效";
	public static final String REG_ERROR_CODE = "1011";
	public static final String REG_ERROR_MSG = "手机短信验证码填写错误";
	// 注册手机验证码校验失败
	public static final String DAYU_ERROR_CODE = "1011";
	public static final String DAYU_ERROR_MSG = "手机短信验证码发送失败";
	// 请输入正确的手机号！
		public static final String REG_ERROR_PHONE_MACH_CODE = "1012";
		public static final String REG_ERROR_PHONE__MACH_MSG = "请输入正确的手机号！";
		// 手机号已经注册
		public static final String REG_ERROR_PHONE_CODE = "1013";
		public static final String REG_ERROR_PHONE_MSG = "手机号已经注册";
	
	// 其他系统异常
	public static final String SYS_ERROR_CODE = "1050";
	public static final String SYS_ERROR_MSG = "系统内部异常";
	
	// 其他系统异常
	public static final String ERROR_CODE = "1030";
	public static final String ERROR_MSG = "密码错误";
	
	// 自动定位城市
	public static final String ERROR_CITY_CODE = "2300";
	public static final String ERROR_CITY_MSG = "定位失败";
	
	// openid传入错误
	public static final String ERROR_OPENID_CODE = "2020";
	public static final String ERROR_OPENID_MSG = "参数传入失败";
}
