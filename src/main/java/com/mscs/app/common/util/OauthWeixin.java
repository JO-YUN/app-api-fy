package com.mscs.app.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @title: OauthWeixin 
* @description：TODO
* @author： 刘威巍
* @date： 2018年10月24日 下午3:40:59
 */
public class OauthWeixin extends Oauth{
    private static Logger log = LoggerFactory.getLogger(OauthWeixin.class);
    private static final String AUTH_URL = "https://open.weixin.qq.com/connect/qrconnect";
    private static final String TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    private static OauthWeixin oauthWeixin = new OauthWeixin();
    /**
     * 用于链式操作
     * @return OauthWeixin
     */
    public static OauthWeixin me() {
        return oauthWeixin;
    }

    public OauthWeixin() {
        setClientId(OathConfig.getValue("openid_weixin"));
        setClientSecret(OathConfig.getValue("openkey_weixin"));
        setRedirectUri(OathConfig.getValue("redirect_weixin"));
    }

    /**
     * 获取授权url
     * @return String url
     */
    public String getAuthorizeUrl(String state) throws UnsupportedEncodingException{
    	String url =getRedirectUri();
    	//String url_encode = java.net.URLEncoder.encode(url, "utf-8");
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("appid", getClientId());
        params.put("redirect_uri", url);
        params.put("response_type", "code");
        params.put("scope", "snsapi_login");
        if (StringUtils.isBlank(state)) {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }
        return super.getAuthorizeUrl(AUTH_URL, params);
    }



    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * 获取token
     * @param @param code
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getTokenByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException{
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("appid", getClientId());
        params.put("secret", getClientSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        //String url=TOKEN_URL+"?appid="+getClientId()+"&secret="+getClientSecret()
        //+"&code="+code+"&grant_type=authorization_code";
        String charset ="utf-8";
        //String token = HttpClientHelper.sendPost(url, params, charset);
        
        String token = super.doPost(TOKEN_URL, params);
        log.debug(token);
        return token;
    }


    /**
     * 获取UserInfo
     * @param accessToken AccessToken
     * @return String 返回类型
     */
    public String getUserInfo(String accessToken,String openId) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("access_token", accessToken);
        params.put("openid", openId);
        return super.doPost(USER_INFO_URL, params);
    }
}
