package com.mscs.app.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayUserPicture;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.mscs.app.web.model.AlipayUser;

@Service
public class AlipayLoginService implements InitializingBean {
	
 private static final Logger LOGGER = LoggerFactory.getLogger(AlipayLoginService.class);

    /**Alipay客户端*/
    private AlipayClient alipayClient;

    /**支付宝网关*/
    private static final String ALIPAY_BORDER_DEV = "https://openapi.alipaydev.com/gateway.do";//沙箱环境网关
    private static final String ALIPAY_BORDER_PROD = "https://openapi.alipay.com/gateway.do";
    /**appID**/
   // private static final String APP_ID_DEV = "2016092000557578";//沙箱环境的网关
    private static final String APP_ID_PROD = "2018100561619304";
    /**私钥*/
    private static final String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCs5RkodktY4Er7nWBRUakTg/e5pi5REo5NwTrM9XaKSIu5hryRECwiJEjo80W5MV3eoNpAIPPxlrkGn+59ABVBmi81v5pTw2+urP16/ghQL1coGfvQ/4xjyOP6fE5+RLoUs8aGkhol6Magz9HknvaxpyO573KguMKy9b0YyLYT3yGGDyQtWYQ6Q4hEG/WaHmKpPuPU0xqy9q316S5XZGSsju7RZAqb782GHV3qQtgWvwVdYi2YmFRok4TNrWZnE9HzXuqwcxQFkBhUJDPzRbd97xf0E/2VtVSGhdXDPCO5s3iMHaj2qC43xyemEdraxX39BYw1yDxpYOww6cdXGk/hAgMBAAECggEALRv9C2SJ1h93EN0Hw0/GCFR3LtXf4cEwpbLTr6QfkIbLBdXUnxiUGzhSWY0XVPE/Z5WaO/8dBpxnnAXgSHbcFOiwhpp+HnqwSefSnuNmsd0YJVtaKzZxn5tLeBA0vpcZ4crh8jxuXBO2t0Bhp/+dp85O8PU7eBmcmuqosq2za81t585dDQfS0qLlOEFdQKGx4ZvmN1qEoyhj/tiDuBi865OfVANIJMTBPUuNZyffxYsIJTUAFxRHq6WvNWbbkcURYmy89R26SC75DcfsHsbI2wNsflDXF2uCZ8jAxI6UP7hsGOG0ayN1gYuZLmKJ8/CQi19sjXBmjr9oUAO40gYCQQKBgQDyK2Ui0nzvUB2WwOHdS0g+zkNdWjTfgZAn0aw4YZetC2ZyLuYphoLno8jT5LY+udQIGkbyckRRxNXOeRjG5NUa9rosPOLoC0QeJgnrvlt5DIX8bluF69JdbviVb6gsMPelj+QfFAguBfeC522Jh5eRHg7hEApDl3GRH5Of1ib5eQKBgQC2xOJBgKMQGCkHm8WMUr6v0he4MiygZ/Tqu4D/l72zU/TOYqS+Rh1agyxzGr6x/CyVr03gKnhCqM+mgQuaZOBFesND8NISKv7mVNWrJM6U87/cTRAa9JIyIr+IYVyW+kDo5jCBl+NwNliQMFsKm1Ho61xMEIr+W3rcS+uHXvPXqQKBgQCjb9ITzEbxI/q0MXy94v7hFEe2ixi/9ysZFJLMgmDhMp5zVV+Uiulx98Gj7+lnL/b/eOyE18VwAg3iCSxFn06LohRHotfojyHrzvRHG/OUIHWUxc4S9fLFiA1QnUi8elfIbvIgX16d4P0IZbQxa0dJcyXLkw26TE0jClKRPvCS0QKBgQCkspy0rCfFDBCFI7EL91CQfcEUIlqmnadNk3P2XxX6MHDNPoT4VZSECOqYOwSWvQisRlHl7D98ykR85ZLAKaET20F0yuFyr74Nb2swEzX5cgF1kxgtQjEv7rIsQpg+EPtHJI+XaHjymHaMUJbp4vcX7me4v7uQ5Hy9Yci3rzALKQKBgQCaifn1lcpFYZbgaurBoHyb7CUCB8prIdlx3n9V4nEVN1IY9DtTNqjn/iGOI6nefkks72uBvLOhZsu0Chevv5hf9NtJ6If3yvrN/40U5dIkUaNt6Q3l5aszEvk11LbDc1X5Fxoc9nTU6ETD7O6zzK2NOITsLzREDG81VtWm1mV3/A==";
    /**公钥*/
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwn5iZcC70yZAMSH/dvZceMyGMXwFtkyui0rQPKjSfLTUBY2cZD1+juFRVlVRGdZCH2SMsxxzqh3Oj92Efv/XzWM14Sr9+3K1IyOweL8/psubmWkgL/99jf7fsnmKRTw5t0ierEdxcj2UN4A6QQyUjCwAhg79CJs1B2Xd1fT6xLVsuoLHx7WV2EyMCnXJmhPKu2lZWwRGxND9hjW3nLicD3FyCq0zym7XEoCvyfAUEK0TWOLdD6Ed/qdaMXiGy7U6kIXZY8ZrvCOynSwJXjNZKpv5FoztSE0PzQ0zN4mJ7Mhwn/eGnaA0/d8anyuyyh3kU5nFHxsk8UUTpWFJdEpnZQIDAQAB";

    @Override
    public void afterPropertiesSet() throws Exception {
        alipayClient = new DefaultAlipayClient(ALIPAY_BORDER_PROD, APP_ID_PROD, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
    	//沙箱环境
    	//alipayClient = new DefaultAlipayClient(ALIPAY_BORDER_DEV,"2016092000557578","MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCs5RkodktY4Er7nWBRUakTg/e5pi5REo5NwTrM9XaKSIu5hryRECwiJEjo80W5MV3eoNpAIPPxlrkGn+59ABVBmi81v5pTw2+urP16/ghQL1coGfvQ/4xjyOP6fE5+RLoUs8aGkhol6Magz9HknvaxpyO573KguMKy9b0YyLYT3yGGDyQtWYQ6Q4hEG/WaHmKpPuPU0xqy9q316S5XZGSsju7RZAqb782GHV3qQtgWvwVdYi2YmFRok4TNrWZnE9HzXuqwcxQFkBhUJDPzRbd97xf0E/2VtVSGhdXDPCO5s3iMHaj2qC43xyemEdraxX39BYw1yDxpYOww6cdXGk/hAgMBAAECggEALRv9C2SJ1h93EN0Hw0/GCFR3LtXf4cEwpbLTr6QfkIbLBdXUnxiUGzhSWY0XVPE/Z5WaO/8dBpxnnAXgSHbcFOiwhpp+HnqwSefSnuNmsd0YJVtaKzZxn5tLeBA0vpcZ4crh8jxuXBO2t0Bhp/+dp85O8PU7eBmcmuqosq2za81t585dDQfS0qLlOEFdQKGx4ZvmN1qEoyhj/tiDuBi865OfVANIJMTBPUuNZyffxYsIJTUAFxRHq6WvNWbbkcURYmy89R26SC75DcfsHsbI2wNsflDXF2uCZ8jAxI6UP7hsGOG0ayN1gYuZLmKJ8/CQi19sjXBmjr9oUAO40gYCQQKBgQDyK2Ui0nzvUB2WwOHdS0g+zkNdWjTfgZAn0aw4YZetC2ZyLuYphoLno8jT5LY+udQIGkbyckRRxNXOeRjG5NUa9rosPOLoC0QeJgnrvlt5DIX8bluF69JdbviVb6gsMPelj+QfFAguBfeC522Jh5eRHg7hEApDl3GRH5Of1ib5eQKBgQC2xOJBgKMQGCkHm8WMUr6v0he4MiygZ/Tqu4D/l72zU/TOYqS+Rh1agyxzGr6x/CyVr03gKnhCqM+mgQuaZOBFesND8NISKv7mVNWrJM6U87/cTRAa9JIyIr+IYVyW+kDo5jCBl+NwNliQMFsKm1Ho61xMEIr+W3rcS+uHXvPXqQKBgQCjb9ITzEbxI/q0MXy94v7hFEe2ixi/9ysZFJLMgmDhMp5zVV+Uiulx98Gj7+lnL/b/eOyE18VwAg3iCSxFn06LohRHotfojyHrzvRHG/OUIHWUxc4S9fLFiA1QnUi8elfIbvIgX16d4P0IZbQxa0dJcyXLkw26TE0jClKRPvCS0QKBgQCkspy0rCfFDBCFI7EL91CQfcEUIlqmnadNk3P2XxX6MHDNPoT4VZSECOqYOwSWvQisRlHl7D98ykR85ZLAKaET20F0yuFyr74Nb2swEzX5cgF1kxgtQjEv7rIsQpg+EPtHJI+XaHjymHaMUJbp4vcX7me4v7uQ5Hy9Yci3rzALKQKBgQCaifn1lcpFYZbgaurBoHyb7CUCB8prIdlx3n9V4nEVN1IY9DtTNqjn/iGOI6nefkks72uBvLOhZsu0Chevv5hf9NtJ6If3yvrN/40U5dIkUaNt6Q3l5aszEvk11LbDc1X5Fxoc9nTU6ETD7O6zzK2NOITsLzREDG81VtWm1mV3/A==","json","utf-8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvgnZcb2oV2XU21mTEPssco9UkRPPNSw1jWNko+VBfd4pB+eR0ByTkDT1+pkDzKUnG5HF9qd09NZHrnnfM7bNcGuBKL6vc1NbdS5y7ZCVaMD4+VmuCPEqSyC612L36KdQqtmoZg1dMmrxpfPEI23542Sg3owg+9y9xDQq076v/fH5zxc/iE6fnOPaa4E2Pdv1KF8pDgP5sL6uNhS3anZuPGI9BVMBIhqFyceLZKefOgNeIAnN7rofUfae8hCFgUfpKSFWF1BF5TOSVj4bHAddXWDv1Qbj+SCMuZuwlqMQuO9s193bmeFx03niNqb6XebRHvdXzXnhMEv8K4WEuBcmnwIDAQAB","RSA2");
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
