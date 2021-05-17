package com.mscs.app.web.service.impl;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.dao.UserMapper;
import com.mscs.app.web.model.User;
import com.mscs.app.web.service.DaYuService;


@Service("DaYuServiceImpl")
public class DaYuServiceImpl implements DaYuService {
	public static final Logger logger = LoggerFactory
			.getLogger(DaYuServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Override
	public String sendSMSCode(String type, String telephone,String code) throws AppException {
		String templateCode=null;
        if(type.equals("1")) {//注册
        	templateCode="SMS_147140786";
        }else if(type.equals("2")) {//密码找回
        	//首先看一下这个手机是否注册过，没有注册过则需要先去注册
        	User u = userMapper.findUserByUserId(telephone);
        	if(u==null)
        		throw new AppException(ErrorCode.UID_FAIL_CODE,ErrorCode.UID_FAIL_MSG);
        	templateCode="SMS_147140785";
        	//templateCode="SMS_174921226";
        }else if(type.equals("3")) {//绑定微信
        	templateCode="SMS_149420751";
        	//templateCode="SMS_174921226";
        }else if(type.equals("4")) {//绑定支付宝
        	templateCode="SMS_149420756";
        	//templateCode="SMS_174921226";
        }else if(type.equals("5")) {
        	templateCode="SMS_173405650";
        	//templateCode="SMS_174921226";
        }
		//实现发送短信功能
		//2
        String templateParam = "{\"code\":\"" + code +"\"}";
        String falg= sendSms(templateCode, telephone, templateParam);
        return falg;
	}
	
	/**
     * 阿里短信的通用配置
     * @throws ClientException 
     */
    public static IAcsClient aliSmsConfig() {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "120000");
        System.setProperty("sun.net.client.defaultReadTimeout", "120000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIOLSMn0SOjqoN";//你的accessKeyId,参考本文档步骤2
        //final String accessKeyId = "LTAI4Fwq4ZCg3dkDuN1J9eBX";
        final String accessKeySecret = "uxFBTdk2htyaZVbQxUEy2z0UNxCwTQ";//你的accessKeySecret，参考本文档步骤2
        //final String accessKeySecret = "KfrpY8yYsILpGIpnOEa98MJNubcSgs";
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-haerbin", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-haerbin", "cn-haerbin", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }
    /**
     * 
     * @param templateCode      短信模板编号
     * @param telephone         手机号，可多个，以','隔开，最多1000
     * @param templateParam     变量内容
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    public static String sendSms(String templateCode, String telephone, String templateParam){
        IAcsClient acsClient = aliSmsConfig();
         //组装请求对象
         SendSmsRequest request = new SendSmsRequest();
         //使用post提交
         request.setMethod(MethodType.POST);
         //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
         request.setPhoneNumbers(telephone);
         //必填:短信签名-可在短信控制台中找到
         request.setSignName("码上城市");//-------------------------
         //必填:短信模板-可在短信控制台中找到
         request.setTemplateCode(templateCode);
         //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
         //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
         if(null!=templateParam&&!templateParam.equals("")){
             request.setTemplateParam(templateParam);
         }
         //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
         //request.setSmsUpExtendCode("90997");
         //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//       request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            return "fail";
        } catch (ClientException e) {
            e.printStackTrace();
            return "fail";
        }
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            logger.info("短息发送成功！手机号：" + telephone);
            return "success";
        } else {
            logger.error("短信发送失败！手机号：" + telephone + "|返回错误码：" + sendSmsResponse.getCode());
            return "fail";
        }
    }

	

}
