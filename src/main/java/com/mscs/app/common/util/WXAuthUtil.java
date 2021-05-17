package com.mscs.app.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
/**
 * @program: integral_wall
 * @description: 网络请求工具类（微信登录使用）
 * @author: Chenjf
 * @create: 2018-09-25 19:44
 **/
public class WXAuthUtil {
	public static final String CITY_NH="nh";
	public static final String APPID_NH="wxfca0e723e8175cf2";
    public static final String APPSECRET_NH ="9dfe2b791672362d986cbc5e50524205";
    
    public static final String CITY_HG="hg";
	public static final String APPID_HG="wxfca0e723e8175cf2";
    public static final String APPSECRET_HG ="9dfe2b791672362d986cbc5e50524205";
   //public static final String APPID_HG="wx4a6de49991a916cd";
    //public static final String APPSECRET_HG ="99051c5fe8eb56635e731319f41e3313";
    
    public static final String APPID_HC="wx91180ec0c7a5b82d";
    public static final String APPSECRET_HC ="b3d6236b63e1fb937525ac2683406969";
    //private static final String TOKEN = "*********";
    public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
        JSONObject jsonObject =null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet =new HttpGet(url);
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity =response.getEntity();
        if(entity!=null){
            //把返回的结果转换为JSON对象
            String result =EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSON.parseObject(result);
        }

        return jsonObject;
    }

}
