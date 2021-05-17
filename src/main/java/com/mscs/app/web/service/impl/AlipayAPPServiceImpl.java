package com.mscs.app.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.mscs.app.web.model.AlipayUser;
@Service
public class AlipayAPPServiceImpl implements InitializingBean{
	
 private static final Logger LOGGER = LoggerFactory.getLogger(AlipayLoginService.class);

    /**Alipay客户端*/
    private AlipayClient alipayClient;

    /**支付宝网关*/
    private static final String ALIPAY_BORDER_PROD = "https://openapi.alipay.com/gateway.do";
    /**appID**/
    private static final String APP_ID_PROD = "2018100561630285";
    /**支付宝公钥*/
    private static final String APP_ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwn5iZcC70yZAMSH/dvZceMyGMXwFtkyui0rQPKjSfLTUBY2cZD1+juFRVlVRGdZCH2SMsxxzqh3Oj92Efv/XzWM14Sr9+3K1IyOweL8/psubmWkgL/99jf7fsnmKRTw5t0ierEdxcj2UN4A6QQyUjCwAhg79CJs1B2Xd1fT6xLVsuoLHx7WV2EyMCnXJmhPKu2lZWwRGxND9hjW3nLicD3FyCq0zym7XEoCvyfAUEK0TWOLdD6Ed/qdaMXiGy7U6kIXZY8ZrvCOynSwJXjNZKpv5FoztSE0PzQ0zN4mJ7Mhwn/eGnaA0/d8anyuyyh3kU5nFHxsk8UUTpWFJdEpnZQIDAQAB";
    /**私钥*/
    private static final String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCd+QbaLwHUSI4yyDOG4AJkpLKuxfTZZoz9413cBgdRCMN7SXJdv1tBYNKBj0vFKMKQEIbBF8VmOU9O79iefBv86Y75qjj5RO+Oc85mSOe6UGXeY6cUntDSrSMoJsJsKU1uwQY7XYsG1weNEAmaLGM2TUfI5cY6h4sglaWJtHyh3FzNQkLYdcxd5r+tJTKrrX8SiTEYWrLYeXJ7Jt6xK3OYPbn5OKJ1dF3bx4hu77rpmiNU8xhqqy2ckaYnZvdjD/gEeB5U9uRs/azcXBgSPUweyWLpHNg0gG1RBsdoaYsuQIV79uR0P67obncttVleYKlKKSXk96cMoq85jgOzKkG/AgMBAAECggEAU2ph8egMgTnmzG3mt3umB9rn913UUs/R92aN81bDmkiUYI+B8nrPBxDHV16BVhSV2FjBr0iMFdSBSl9f9R/3726s7qyx9P4vuPrxrBUnN7yobewVF9Bc2EJQZC1DULXmi2sljUdx2CTHefdT+rMhmFTqaOzb5aAZGRWsGbDSduz+o/E9aNFwNaa5+ZnNxp8/aM7dslfeftYLH6dpl23x4IX5fovAyMU9a8ww/1Sp+tqTBZAbbYbfSBZFwLYzMd1XFVJr6kHGYxbCiuILpCpS7jUHKFXQqjjYA1b/1wYcw0UKoZaux2yscKg5XoHV6ykWg5uYzyTlywcqRUbbEtF3QQKBgQDYpbk/Fh6F0+SiZXeh8sINHQKWt+Nan+V6BwZt47zBmDys4Nd9k0gZjVljiTBaItMe3b+EsyjOXT5PU2SsKpy0FIrKfd8OCtJXNoOWR3j+b5GeKSQtXXJAmtulKpMMe71QhXA3wrUCnEqrNTrWzue12wEwvtDJuACkkHksLQsH0QKBgQC6quMfLJBJ0Mf8st7LFgDHjGeHkDLOsfQFUBTzTdPxhtzcthndutjo4S+ZaZ52x8eFemOM3pDwdFMxQY1pvgvXKJiWMPnMb0yCCS0DSs3WlGyXgthzcmSbJBqCllPt9hGJZONRpL96Uw8c7JMJg6EA86kOiC+8FiVl3n+Od8+kjwKBgQDNCnv6syqMKnE3KaVCBwmVc6FhmAR7Xt3G0iNJmcKpzzWNwPbyCJSyak7xn4O9qzv4+JIsO4whU6qHVTvwnhvVa6O35c1X8vIETLnZXyeqV1mQQQvWS5y4fFfpSBQIOoyASupcO9Eo+VbKFwTrDMPMH8xOoCnm4FjrZzjnkHHiIQKBgDqeFxxe/iBv850i/lt/2VbQfGJRoeLOdvaEI1AUDI4373tPo+fBGQRoUm42gJCEQ99ig4hlC9a2cLlzAFlIWKeEFAZ18ruD596dywjRoLnVehRJ/naiYX1mZPzFAC4lq3QdvlSRC5/6HTDUCYhfUVvfKCNsGLFTswK+UAgJD6d5AoGAfzuzkKGtjg0CCM9ar15w1yZwLfb1xw0aHMxsozK7aiMg6Bj3nYxzCazBOnD1llDow3SJ2ogC7jFFlz8wUlFs89uDw4cjUXTtPxi16KdrKIawi4a083ZIDSXIBkPAhdfUnOVg3oQB48lPK7UAmbGYeIHK1odeiIxg79k4FRb9gOU=";
    
    @Override
    public void afterPropertiesSet() throws Exception {
    	alipayClient = new DefaultAlipayClient(ALIPAY_BORDER_PROD, APP_ID_PROD, APP_PRIVATE_KEY, "json", "UTF-8", APP_ALIPAY_PUBLIC_KEY, "RSA2");
    }
    /**
     * 根据auth_code获取用户的user_id和access_token
     * @param authCode
     * @return
     */
    public String getAccessToken(String authCode) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            return oauthTokenResponse.getAccessToken();
        } catch (Exception e) {
            LOGGER.error("使用authCode获取信息失败！", e);
            return null;
        }
    }

    /**
     * 根据access_token获取用户信息
     * @param token
     * @return
     */
    public AlipayUser getUserInfoByToken(String token) {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest ();
        try {
            AlipayUserInfoShareResponse response =  alipayClient.execute(request, token);
            if (response.isSuccess()) {
                //打印响应信息
//                System.out.println(ReflectionToStringBuilder.toString(response));
                //封装支付宝对象信息
            	System.out.println("alipayId"+response.getUserId());
            	System.out.println("CertNo"+response.getCertNo());
            	System.out.println("city"+response.getCity());
            	System.out.println("CollegeName"+response.getCollegeName());
            	System.out.println("Degree"+response.getDegree());
            	System.out.println("Mobile"+response.getMobile());
            	System.out.println("username"+response.getUserName());
            	System.out.println("nikeName"+response.getNickName());
            	System.out.println("Gender"+response.getGender());
            	System.out.println("pic"+response.getAvatar());
                AlipayUser alipayUser = new AlipayUser();
                alipayUser.setAddress(response.getAddress());
                alipayUser.setCertNo(response.getCertNo());
                alipayUser.setCity(response.getCity());
                alipayUser.setCollegeName(response.getCollegeName());
                alipayUser.setDegree(response.getDegree());
                alipayUser.setMobile(response.getMobile());
                alipayUser.setPhone(response.getPhone());
                alipayUser.setProvince(response.getProvince());
                alipayUser.setUserName(response.getUserName());
                alipayUser.setNickName(response.getNickName());
                alipayUser.setAlipayId(response.getUserId());
                String gender=response.getGender();
                if(gender.equalsIgnoreCase("f")) {
                	alipayUser.setSex("1");
                }else if(gender.equalsIgnoreCase("M")){
                	alipayUser.setSex("2");
                }
                alipayUser.setEmail(response.getEmail());
                alipayUser.setAlipayheadurl(response.getAvatar());//头像
                return alipayUser;
            }
            LOGGER.error("根据 access_token获取用户信息失败!");
            return null;

        } catch (Exception e) {
            LOGGER.error("根据 access_token获取用户信息抛出异常！", e);
            return null;
        }
    }
}
