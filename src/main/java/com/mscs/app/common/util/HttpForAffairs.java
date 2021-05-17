package com.mscs.app.common.util;


import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/*import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;*/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
public class HttpForAffairs {
	public JSONObject getRequest(String url) throws HttpException, IOException {
		HttpClient client = new HttpClient();  
		client.getParams().setContentCharset("UTF-8");
		GetMethod getMethod = new GetMethod(url);
		client.executeMethod(getMethod);  
		JSONObject resultJson = (JSONObject) JSONObject.parse(getMethod.getResponseBodyAsString());
		return  resultJson;
	}
	
	public JSONObject postRequest(String url,Map<String,String> map) throws HttpException, IOException {
		HttpClient client = new HttpClient();  
		PostMethod postMethod = new PostMethod(url);
		client.getParams().setContentCharset("UTF-8");
		for(Map.Entry<String, String> m : map.entrySet()) {
			postMethod.setParameter(m.getKey(),m.getValue());
		}
		
		client.executeMethod(postMethod);
		JSONObject resultJson = (JSONObject) JSONObject.parse(postMethod.getResponseBodyAsString());
		return  resultJson;
	}
	
	public JSONObject postRequestAPP(String url,Map<String,String> map) throws HttpException, IOException {
		HttpClient client = new HttpClient();  
		PostMethod postMethod = new PostMethod(url);
		client.getParams().setContentCharset("UTF-8");
		//postMethod.addRequestHeader("Content-Type", "application/json");
		for(Map.Entry<String, String> m : map.entrySet()) {
			postMethod.setParameter(m.getKey(),m.getValue());
		}
		String s = postMethod.getResponseBodyAsString();
		client.executeMethod(postMethod);
		JSONObject resultJson = (JSONObject) JSONObject.parse(s);
		return  resultJson;
	}
	
	

}
