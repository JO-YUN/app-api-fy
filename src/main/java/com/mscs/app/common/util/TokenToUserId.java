package com.mscs.app.common.util;

import java.util.HashMap;
import java.util.Map;


/**
 * token工具类
 * @author lenovo
 *
 */
public class TokenToUserId {
	
	//http://221.207.246.119:9090/app-api/token/user?token=4dbe8a3d659d471da4e5ca099ae13375
	public static final String TOKEN_URL="http://127.0.0.1:8080/app-api/token/user";
	
	/**
	 * 返回token对应的userID
	 * @param param
	 * @return
	 */
	public static String getUserId(String token) {
		//贺纯阳提供的token转ID接口
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("token", token);
		return getUserId(param);
	}
	
	/**
	 * 返回token对应的userID
	 * @param param
	 * @return
	 */
	public static String getUserId(Map<String, Object> param) {
		//String resultJson = Http.sendGet(TOKEN_URL, param, "UTF-8");
		//TokenUser tokenUser = new Gson().fromJson(resultJson, com.hrbwmxx.framework.model.TokenUser.class);
		//if(tokenUser.getData()!=null) {
		//	return tokenUser.getData();
		//}
		return "20100008";
		
	}
	
	

}
