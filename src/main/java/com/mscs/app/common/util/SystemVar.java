package com.mscs.app.common.util;

public class SystemVar {
	//pc沙箱
	public  final static String PC_APP_ID_SX="2016092000557578";
	public  final static String PC_REDIRECT_URL_SX="http://192.168.12.21/zhcs/blank.html";
	
	//PC正式环境的appid+回调页面
	public  final static String PC_REDIRECT_URL="http://www.data0452.com/blank.html";
	public  final static String PC_APP_ID ="2018100561619304";
	//APP正式开发的appid+回调页面
	//public  final static String APP_REDIRECT_URL="http://192.168.12.21/mscs-app/page/blank.html";//支付宝授权之后的回调地址
	public  final static String APP_REDIRECT_URL="http://192.168.31.84/mscs-app/page/blank.html";//支付宝手机端授权回调接口
	public  final static String APP_APP_ID ="2018100561630285";//app_id就是你创建的应用的id
	
	public  final static String SCOPE="auth_user";// scope可以取的值有多个，这里使用"auth_user"实现网站授权登录
	public  final static String ALIPAY_URL="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm";//授权页面
    public final static String SJ_URL="alipays://platformapi/startapp?appId=20000067&fromAppUrlScheme=test&url=";//app用于调用手机支付宝客户端前缀
    public final static String APP_PRIVATEKEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCd+QbaLwHUSI4yyDOG4AJkpLKuxfTZZoz9413cBgdRCMN7SXJdv1tBYNKBj0vFKMKQEIbBF8VmOU9O79iefBv86Y75qjj5RO+Oc85mSOe6UGXeY6cUntDSrSMoJsJsKU1uwQY7XYsG1weNEAmaLGM2TUfI5cY6h4sglaWJtHyh3FzNQkLYdcxd5r+tJTKrrX8SiTEYWrLYeXJ7Jt6xK3OYPbn5OKJ1dF3bx4hu77rpmiNU8xhqqy2ckaYnZvdjD/gEeB5U9uRs/azcXBgSPUweyWLpHNg0gG1RBsdoaYsuQIV79uR0P67obncttVleYKlKKSXk96cMoq85jgOzKkG/AgMBAAECggEAU2ph8egMgTnmzG3mt3umB9rn913UUs/R92aN81bDmkiUYI+B8nrPBxDHV16BVhSV2FjBr0iMFdSBSl9f9R/3726s7qyx9P4vuPrxrBUnN7yobewVF9Bc2EJQZC1DULXmi2sljUdx2CTHefdT+rMhmFTqaOzb5aAZGRWsGbDSduz+o/E9aNFwNaa5+ZnNxp8/aM7dslfeftYLH6dpl23x4IX5fovAyMU9a8ww/1Sp+tqTBZAbbYbfSBZFwLYzMd1XFVJr6kHGYxbCiuILpCpS7jUHKFXQqjjYA1b/1wYcw0UKoZaux2yscKg5XoHV6ykWg5uYzyTlywcqRUbbEtF3QQKBgQDYpbk/Fh6F0+SiZXeh8sINHQKWt+Nan+V6BwZt47zBmDys4Nd9k0gZjVljiTBaItMe3b+EsyjOXT5PU2SsKpy0FIrKfd8OCtJXNoOWR3j+b5GeKSQtXXJAmtulKpMMe71QhXA3wrUCnEqrNTrWzue12wEwvtDJuACkkHksLQsH0QKBgQC6quMfLJBJ0Mf8st7LFgDHjGeHkDLOsfQFUBTzTdPxhtzcthndutjo4S+ZaZ52x8eFemOM3pDwdFMxQY1pvgvXKJiWMPnMb0yCCS0DSs3WlGyXgthzcmSbJBqCllPt9hGJZONRpL96Uw8c7JMJg6EA86kOiC+8FiVl3n+Od8+kjwKBgQDNCnv6syqMKnE3KaVCBwmVc6FhmAR7Xt3G0iNJmcKpzzWNwPbyCJSyak7xn4O9qzv4+JIsO4whU6qHVTvwnhvVa6O35c1X8vIETLnZXyeqV1mQQQvWS5y4fFfpSBQIOoyASupcO9Eo+VbKFwTrDMPMH8xOoCnm4FjrZzjnkHHiIQKBgDqeFxxe/iBv850i/lt/2VbQfGJRoeLOdvaEI1AUDI4373tPo+fBGQRoUm42gJCEQ99ig4hlC9a2cLlzAFlIWKeEFAZ18ruD596dywjRoLnVehRJ/naiYX1mZPzFAC4lq3QdvlSRC5/6HTDUCYhfUVvfKCNsGLFTswK+UAgJD6d5AoGAfzuzkKGtjg0CCM9ar15w1yZwLfb1xw0aHMxsozK7aiMg6Bj3nYxzCazBOnD1llDow3SJ2ogC7jFFlz8wUlFs89uDw4cjUXTtPxi16KdrKIawi4a083ZIDSXIBkPAhdfUnOVg3oQB48lPK7UAmbGYeIHK1odeiIxg79k4FRb9gOU=";//app私钥
    public final static String PID="2088031423596816";//支付宝app登录授权的签约ID
    public final static String TARGET_ID="kkkkk091125";// 商户唯一标识
}