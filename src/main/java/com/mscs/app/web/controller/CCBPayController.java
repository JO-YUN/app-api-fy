package com.mscs.app.web.controller;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*import com.autograph.SHA256withRSA;*/
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqOrderResultDto;
import com.mscs.app.common.dto.req.ReqPayCostDto;
import com.mscs.app.common.dto.resp.ResqorderResult;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.dao.OrderResultMapper;
import com.mscs.app.web.dao.TokenMapper;
import com.mscs.app.web.dao.UserMapper;
import com.mscs.app.web.model.Token;
import com.mscs.app.web.model.User;

/**
 * 
* @title: CCBPayController 
* @description：TODO
* @author： liuweiwei
* @date： 2019年2月19日 上午9:14:44
 */
@Controller
@RequestMapping("ccb")
public class CCBPayController extends BIZController {
	public static final Logger logger = LoggerFactory.getLogger(CCBPayController.class);
	@Autowired
	private TokenMapper tokenMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OrderResultMapper orderResultMapper;
	/**
	* @MethodName: payCost 
	* @description : TODO
	* @author：liuweiwei
	* @date： 2019年2月19日 上午9:14:57
	* @param dto
	* @return
	* @throws Exception ResponseDto
	 */
	/*@RequestMapping(value = "pay", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseDto payCost(ReqPayCostDto dto) throws Exception {
		System.out.println(111);
		// 拼接
		Charset cc = Charset.defaultCharset();
		//System.out.println("编码"+cc.name());
        String data ="";		
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
			String Rgon_cd="&Admn_Rgon_Cd=";
			//行政区域代码
			if (!StringUtils.isBlank(dto.getXzqydm())) {
				Rgon_cd = "&Admn_Rgon_Cd=" + dto.getXzqydm();
			}else {
				Rgon_cd ="&Admn_Rgon_Cd=230281";
				
			}
			String Usr_ID="";
			if (!StringUtils.isBlank(userId)) {
				Usr_ID="&Usr_ID=" + userId;// 登陆用户的用户号
			}else {
				userId="15945695685";
				Usr_ID="&Usr_ID=15945695685";
			}
			String IsMobile="";
			if (!StringUtils.isBlank(dto.getIsMobile())) {
				IsMobile="&IsMobile=" + dto.getIsMobile();// 登陆用户的用户号
			}else {
				IsMobile="&IsMobile=0";
			}
			
			String name ="";
			String nameSrc="";
			if (!StringUtils.isBlank(user.getName())) {
				//user.setName("aa");
				nameSrc=user.getName();
				name =URLEncoder.encode(user.getName(),"utf-8");
			}else {
				user.setName("aa");
				name =URLEncoder.encode(user.getName(),"utf-8");
			}
			
			
			
			String email = "";
			if (!StringUtils.isBlank(user.getEmail())) {
				email="&Email="+user.getEmail();
			}else {
				email = "&Email=";
			}
			
			//StringBuffer url = new  StringBuffer("https://govpaytesthljnh.mytunnel.site/online/bmjf/MainPlat?Vno=2");
			StringBuffer url = new StringBuffer("https://hljnh.govpay.ccb.com/online/bmjf/MainPlat?Vno=2");
			url.append("&Sgn_Algr=SHA256withRSA")
			   .append("&CstPty_Ordr_No=")//105000086510048(测试商户)--105000086510315(正式商户)
			   .append("&Fee_Itm_Prj_User_No=")
			   .append("&MrchCd=105000086510315")
			   .append("&PyF_ClCd="+dto.getPaytype())
			   .append(Rgon_cd)
			   .append("&Fee_Itm_PyF_MtdCd=00")
			   .append(Usr_ID)
			   .append("&Cst_Nm=").append("&Crdt_Tp=")
			   .append("&Crdt_No=").append("&MblPh_No=")
			   .append(email).append("&Ret_Url=")
			   .append("&Tms=" + date).append(IsMobile);
			StringBuffer urlSrc = new  StringBuffer("Vno=2");
			urlSrc.append("&Sgn_Algr=SHA256withRSA")
			   .append("&CstPty_Ordr_No=")
			   .append("&Fee_Itm_Prj_User_No=")
			   .append("&MrchCd=105000086510315")
			   .append("&PyF_ClCd="+dto.getPaytype())
			   .append(Rgon_cd)
			   .append("&Fee_Itm_PyF_MtdCd=00")
			   .append(Usr_ID)
			   .append("&Cst_Nm=")
			   .append("&Crdt_Tp=")
			   .append("&Crdt_No=")
			   .append("&MblPh_No=")
			   .append(email)
			   .append("&Ret_Url=")
			   .append("&Tms=" + date).append(IsMobile);
			
			//System.out.println("原串"+urlSrc.toString());
			
			String signInfo = this.sign(urlSrc.toString());
			//signInfo = signInfo.replace("+", "Q3QQQ");
			signInfo = signInfo.replace("+", "%2B");
			url.append("&SIGN_INF="+signInfo);// 商户签名
			//System.out.println("ccb"+url.toString());
			data = url.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseDto.buildSuccess().setData(data);
	}

	// 生成商户签名
	private String sign(String srcStr) {
		String signInf = "";
		String pubkey = "";
		String prikey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";
		try {
			//Map<String, String> keyMap = SHA256withRSA.generateKeyBytes();
//			/pubkey =  keyMap.get(SHA256withRSA.PUBLIC_KEY);
			
			signInf = SHA256withRSA.sign(prikey, srcStr);
			//System.out.println(signInf);
			//验签结果
			//boolean flag = SHA256withRSA.verifySign(pubkey, srcStr, signInf);
			//System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return signInf;
	}
	
	@RequestMapping(value = "orderResultqqhr", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResqorderResult orderResultqqhr(@RequestBody Map<String, String> map) throws Exception {
		try {
			ReqOrderResultDto dto = new ReqOrderResultDto();
			for (Map.Entry<String, String> m : map.entrySet()) {
				if("Py_Ordr_No".equals(m.getKey())) {
					dto.setPy_Ordr_No(m.getValue());
				}
				if("Ordr_StCd".equals(m.getKey())) {
					dto.setOrdr_StCd(m.getValue());
				}
				if("Txn_Dt".equals(m.getKey())) {
					dto.setTxn_Dt(m.getValue());
				}
				if("Txn_Tm".equals(m.getKey())) {
					dto.setTxn_Tm(m.getValue());
				}
				if("Fee_Itm_Cd".equals(m.getKey())) {
					dto.setFee_Itm_Cd(m.getValue());
				}
				if("Fee_Itm_Prj_Nm".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Nm(m.getValue());
				}
				if("Fee_Itm_Prj_Usr_No".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Usr_No(m.getValue());
				}
				if("Fee_Itm_Prj_Amt".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Amt(m.getValue());
				}
				if("Py_Chnl_Cd".equals(m.getKey())) {
					dto.setPy_Chnl_Cd(m.getValue());
				}
				if("Usr_ID".equals(m.getKey())) {
					dto.setUsr_ID(m.getValue());
				}
				if("SIGN_INF".equals(m.getKey())) {
					dto.setSIGN_INF(m.getValue());
				}
		    }
			orderResultMapper.saveOrderqqhr(dto);
		}catch(Exception e) {
			e.printStackTrace();
			ResqorderResult rs = new ResqorderResult();
			rs.setRcv_StCd("01");
			return rs;
		}
			ResqorderResult rs = new ResqorderResult();
			rs.setRcv_StCd("00");
			return rs;	
	}
	
	@RequestMapping(value = "orderResulthg", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResqorderResult orderResulthg(@RequestBody Map<String, String> map) throws Exception {
		try {
			
			ReqOrderResultDto dto = new ReqOrderResultDto();
			for (Map.Entry<String, String> m : map.entrySet()) {
				if("Py_Ordr_No".equals(m.getKey())) {
					dto.setPy_Ordr_No(m.getValue());
				}
				if("Ordr_StCd".equals(m.getKey())) {
					dto.setOrdr_StCd(m.getValue());
				}
				if("Txn_Dt".equals(m.getKey())) {
					dto.setTxn_Dt(m.getValue());
				}
				if("Txn_Tm".equals(m.getKey())) {
					dto.setTxn_Tm(m.getValue());
				}
				if("Fee_Itm_Cd".equals(m.getKey())) {
					dto.setFee_Itm_Cd(m.getValue());
				}
				if("Fee_Itm_Prj_Nm".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Nm(m.getValue());
				}
				if("Fee_Itm_Prj_Usr_No".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Usr_No(m.getValue());
				}
				if("Fee_Itm_Prj_Amt".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Amt(m.getValue());
				}
				if("Py_Chnl_Cd".equals(m.getKey())) {
					dto.setPy_Chnl_Cd(m.getValue());
				}
				if("Usr_ID".equals(m.getKey())) {
					dto.setUsr_ID(m.getValue());
				}
				if("SIGN_INF".equals(m.getKey())) {
					dto.setSIGN_INF(m.getValue());
				}
		    }
			orderResultMapper.saveOrderhg(dto);
		}catch(Exception e) {
			e.printStackTrace();
			ResqorderResult rs = new ResqorderResult();
			rs.setRcv_StCd("01");
			return rs;
		}
			ResqorderResult rs = new ResqorderResult();
			rs.setRcv_StCd("00");
			return rs;	
	}
	
	@RequestMapping(value = "orderResult", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResqorderResult orderResult(@RequestBody Map<String, String> map) throws Exception {
		try {
			
			ReqOrderResultDto dto = new ReqOrderResultDto();
			for (Map.Entry<String, String> m : map.entrySet()) {
				if("Py_Ordr_No".equals(m.getKey())) {
					dto.setPy_Ordr_No(m.getValue());
				}
				if("Ordr_StCd".equals(m.getKey())) {
					dto.setOrdr_StCd(m.getValue());
				}
				if("Txn_Dt".equals(m.getKey())) {
					dto.setTxn_Dt(m.getValue());
				}
				if("Txn_Tm".equals(m.getKey())) {
					dto.setTxn_Tm(m.getValue());
				}
				if("Fee_Itm_Cd".equals(m.getKey())) {
					dto.setFee_Itm_Cd(m.getValue());
				}
				if("Fee_Itm_Prj_Nm".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Nm(m.getValue());
				}
				if("Fee_Itm_Prj_Usr_No".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Usr_No(m.getValue());
				}
				if("Fee_Itm_Prj_Amt".equals(m.getKey())) {
					dto.setFee_Itm_Prj_Amt(m.getValue());
				}
				if("Py_Chnl_Cd".equals(m.getKey())) {
					dto.setPy_Chnl_Cd(m.getValue());
				}
				if("Usr_ID".equals(m.getKey())) {
					dto.setUsr_ID(m.getValue());
				}
				if("SIGN_INF".equals(m.getKey())) {
					dto.setSIGN_INF(m.getValue());
				}
		    }
			orderResultMapper.saveOrder(dto);
		}catch(Exception e) {
			e.printStackTrace();
			ResqorderResult rs = new ResqorderResult();
			rs.setRcv_StCd("01");
			return rs;
		}
			ResqorderResult rs = new ResqorderResult();
			rs.setRcv_StCd("00");
			return rs;	
	}*/
}
