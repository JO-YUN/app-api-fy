package com.mscs.app.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.autograph.SHA256withRSA;
import com.mscs.app.common.dto.req.ReqPayCostDto;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.dao.TokenMapper;
import com.mscs.app.web.dao.UserMapper;
import com.mscs.app.web.model.Token;
import com.mscs.app.web.model.User;

/**

 *
 */
@Controller
@RequestMapping("appbmjf2")
public class AppPayCostController2 extends BIZController {
	public static final Logger logger = LoggerFactory.getLogger(AppPayCostController2.class);
	@Autowired
	private TokenMapper tokenMapper;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 便民缴费
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "pay", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void payCost(ReqPayCostDto dto) throws Exception {
		// 拼接
		// 找到登录的用户
		try {
			Token token = tokenMapper.findInfoByToken(dto.getToken());
			String userId = "";
			User user = new User();
			if (token != null) {
				userId = token.getUserId();
				user = userMapper.findUserByUserId(userId);
			}
			//dto.setXzqydm("230281");
			// 获取当前时间
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = format.format(new Date());
			String mll="https://govpaytesthljnh.mytunnel.site/online/bmjf/MainPlat?Vno=2&Sgn_Algr=SHA256withRSA&CstPty_Ordr_No=&Fee_Itm_Prj_User_No=&MrchCd=105000086510009&PyF_ClCd=01006&Admn_Rgon_Cd=&Fee_Itm_PyF_MtdCd=00&Usr_ID=15945695685&Cst_Nm=张三丰&Crdt_Tp=&Crdt_No=&MblPh_No=15945695685&Email=&Ret_Url=&Tms=20181120162420&IsMobile=0&SIGN_INF=";
			String urlSrc = "Vno=2&Sgn_Algr=SHA256withRSA&CstPty_Ordr_No=&Fee_Itm_Prj_User_No=&MrchCd=105000086510009&PyF_ClCd=01006&Admn_Rgon_Cd=&Fee_Itm_PyF_MtdCd=00&Usr_ID=15945695685&Cst_Nm=张三丰&Crdt_Tp=&Crdt_No=&MblPh_No=15945695685&Email=&Ret_Url=&Tms=20181120162420&IsMobile=0";
			String signInfo = this.sign(urlSrc.toString());
			//signInfo = signInfo.replace("+", "Q3QQQ");
			System.out.println("原串"+urlSrc);
			
			String qq=mll+signInfo;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 生成商户签名
	private String sign(String srcStr) {
		String signInf = "";
		String pubkey = "";
		String prikey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";
		try {
			Map<String, String> keyMap = SHA256withRSA.generateKeyBytes();
			pubkey =  keyMap.get(SHA256withRSA.PUBLIC_KEY) ;
			//String  prikey =  keyMap.get(SHA256withRSA.PRIVATE_KEY) ;
		    String sing_byte = SHA256withRSA.sign(prikey,srcStr);
			System.out.println("签名 = " + sing_byte);
			signInf =sing_byte;
			//验签结果
			boolean flag = SHA256withRSA.verifySign(pubkey, srcStr, sing_byte);
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return signInf;
	}
}
