package com.mscs.app.web.controller;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.autograph.SHA256withRSA;
import com.ccb.ga.cloud.demo.encryDemo;
import com.mscs.app.common.dto.Ccbpara;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqPayCostDto;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.dao.TokenMapper;
import com.mscs.app.web.dao.UserMapper;
import com.mscs.app.web.model.Token;
import com.mscs.app.web.model.User;

/**
 * @author LiuGuoHui
 * @date 2018-10-09
 */
@Controller
@RequestMapping("bmjf2")
public class AppPayCostController3 extends BIZController {
	private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";
	private static final String PRIVATE_KEY_PRO = "MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQCFQ4A2y8qwxTEfG/XlOZFmT71AHDZZl5BSwMgnwKFO+bShQ3SpRdHVpOtjPzfq13hLOUapikn8IKq9dAvye3pES6qYsqJH1tq/Pl5Xwq8Bb96oayZ6NFYWX5datTaN/rRo3XhiAzJyhja1+5bw9utcqZUzHA6cY6vX+W2NUQrs1drK3mK1zP8eb0ySWCdb1/XxRQGp4Pvc1d83YiG4/MUNx7e13QCyBNNt6keuALnIDp0njDFuOF/kgQqktKjjdS+5cDnfOif/cNag4GKj3zu+kWlPdgR4JIyzU7KXRmbb4WQP06F92xft/g2x6YaCaARpJwLokrgj+mWeWbpt5EYTAgMBAAECggEAW6yf2xI+kXRV4my9r772g0d/jUbbLK7THdp41r/xN/8Xf4iEDJ53R114gIHQ2cD4g+cW8TKsysE+M65NObYv2iOjbAAXGwx21vD0vbKz6W0n4vwzMAYo4qNH36KhsPTy7uDMG8DPR3GE2ZQFtiZmqwwOE2bNtk/qu1xfPMPvnBRJel8Q5pgBkMs3EO1mWvYnYeCN4j+8HT5g3d3CFXxlx4biyLNjIW4PhuB8/c2itcJtpkwD4YUMla8EbhBaJalncgXgfFhbNuhopyYxAMW3m1cLb7zN48FK4RVnWk/xtlSUPbRr8Z4F2mTejp9YBnIijZ6tahURXCcFcYOf0XBnwQKBgQD0ugk/GBtzosZS1YCAinMRbDytKE2U7x/ni45D5QkE2qfbm8MTSgyWFvJXggxbthz/319FJJSvBGPaK88QNGz6xcXWUpR3AyX/qhEe7zrxRd5T5ju/i6tysx1Ad7MW8Ds6uWAow2X+Wiz0GBKZLY74MEWGEgy+3cF/THiEzwXPGwKBgQCLZwainljZXKb0cddFA1Zjavscxl1gcyLu5wxx/Xr9NSo0L5kq3QD6jsZPh/7+ir+mFyx6pR9rlbU9710/hex9kaxrNr3FEXDAHf8TJpnXOAr3wSvtkNSBzyaRGw88K3uVGUIPpUzgvyknTMNVUyehLS0yMr9T3sL8k2DfX2g8aQKBgBhcnKcHQ1RTk+KTFmO1ZJcLa07hgDefRlHKdrMTjb5J9gPKkbTN3kripeVIMOrASkq+dcFwTszNZxNE4Sv/zAl6vqMDPXxLd/DngSTPM9Lo3aUEelYrFTop0nOkdtOFvp+t7ICiUvjrCSaTbuaIvn6+b21VRqeJc8W0Wxr9cNqZAn9po6ljkpvCHHRoydxYcC8HKAsjwy7/6ECQyoaj23NYbvGMmoV39gLH3Wpq2qRiQML7GpSaXIdzAABY7wu7F8IfZkVC+jjVHmJTNGgYGyh57/5fwgOIBDkGJfUUAW2KK2l7k6h8MsgPiM2m+oUMI/fkLFgTsmFLLYSV4xmGLMbRAoGAHR6BHWkmZ1auZgJvqWhpiq7rUz95zWQ0/cTBLJJ+0IjiBkYoxXCnnJTzCpRtuzhLyiNh/P5N9bIAcWu2GQHtu8bYf7o+xRxUGLIrtt36LkwYyhJD3IfMCh8kpSLXElXJHD7WBw5BsQN7FW8zrswAd0eXJLtOXnR0zy4VdBUMExU=";
	/**
	     * 使用政融支付平台公钥对加密密钥进行RSA非对称加密 此处密钥更换为具体公钥
	     */
	private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkxJVDKgDhDGS/OCBDmqD1F5j8yzeimmEmaFjz3w+oO6vUDpJ+bGesONsK0Ue1ttOyFBm0x1IRRmJJWTTL9NSNm+f8P1nttZiRSdWIgOjXwxZvasndDN9cUArilIGPNhpLxkHaZkzvym/GKPPbr/p5ys/iFFjZsxGTJ5k84KnK83yC503TGqLweOp24/ghJZO80lPH3ZNTQUqmV4JodTdRvirJbPAZBoc2lUkizYX4NitAqCDnXXFN8JT9C2tJONy6s8JRsuXC7Y6+kffXTxUne2UAvuwkyBJtMsiH3a38yj9V9PLbggOST9gpUG7ISwe2PGWamYsx0/tJJR8avUnwwIDAQAB";
	private static final String PUBLIC_KEY_PRO ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo5JWwRdxVRXRfVsVHaOUBzgC3YmZi6TwZoy57HPdVxk3/lu3Mt5tAkHgNgsdcRvEPxkDgpl92n71Hxi4xlayoN3eSSKtj36VHVeGuDTHafJxs5M4ZRiH3DmQxiL4Z4eDvr2EYwAIDeaVxZQSvuxULyT2tMXzp8G+HNju8Z6xlbqDn6zfkNLMWmIQzA+5djiFh9UDdVeCoM2eKVmS4XiKUNEntbDVcbSMtr6h8JmbaD3UtWw5IaDY6wzYqausZoyya5Hxa3beBZc1jgmhaCP9qeL9q2sD96wlvGSldzrVxkbG0XO0uIV5ZZdhQZb6V5s+wz5i42Xrobh+36COzcFdvQIDAQAB";
	public static final Logger logger = LoggerFactory.getLogger(AppPayCostController3.class);
	@Autowired
	private TokenMapper tokenMapper;
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 便民缴费
	 * @throws Exception
	 */
	@RequestMapping(value = "pay", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseDto payCost(ReqPayCostDto dto,HttpSession httpSession) throws Exception {
		User userInfo = new User();
		boolean wxfrom = false;
		int city = 0;//0讷河1鹤岗
		if(dto.getToken() == null) {
			dto = (ReqPayCostDto) httpSession.getAttribute("dto");
			wxfrom =true;
		}
		if(dto.getToken() != null && (dto.getToken().equals("wx") || dto.getToken().equals("wxhg"))) {
			
			//httpSession.setAttribute("openid","test");
			Object openid = httpSession.getAttribute("openid");
			if(openid == null) {
				 httpSession.setAttribute("dto", dto);
				 return ResponseDto.buildSuccess().setData("wxLogin");
			}else {
				if(dto.getToken().equals("wx")) {
					userInfo = userMapper.checkOpenIdAndS(openid.toString());
				}else if (dto.getToken().equals("wxhg")){
					city = 1;
					userInfo = userMapper.checkOpenIdAndSHG(openid.toString());
				}else if(dto.getToken().equals("wxSearch")) {
					userInfo = userMapper.checkOpenIdAndS(openid.toString());
					if( wxfrom == false) {
						return search(userInfo);
					}else {
						this.getResponse().sendRedirect(search(userInfo).getData().toString());
						return null;
					}
				}
				
				if(userInfo != null) {
					String sfzjh = userInfo.getSfzjh();
					if(sfzjh == null || sfzjh.equals("")) {
						if(dto.getToken().equals("wxhg")) {
							if( wxfrom == false) {
								return ResponseDto.buildSuccess().setData("detailshg.html");
							}else {
								this.getResponse().sendRedirect("../detailshg.html");
								return null;
							}
						}
						if( wxfrom == false) {
							return ResponseDto.buildSuccess().setData("details.html");
						}else {
							this.getResponse().sendRedirect("../details.html");
							return null;
						}
					}
				}else {
					if(dto.getToken().equals("wxhg")) {
						if( wxfrom == false) {
							return ResponseDto.buildSuccess().setData("detailshg.html");
						}else {
							this.getResponse().sendRedirect("../detailshg.html");
							return null;
						}
					}
					if( wxfrom == false) {
						return ResponseDto.buildSuccess().setData("details.html");
					}else {
						this.getResponse().sendRedirect("../details.html");
						return null;
					}
				}
			}
		}
		
		Token token = null;
		// 拼接
		Charset cc = Charset.defaultCharset();
		//System.out.println("编码"+cc.name());
		//测试
		//StringBuffer url = new StringBuffer("http://govpaytesthljnh.mytunnel.site/online/bmjf/MainPlat?Vno=2");
		//正式
		StringBuffer url = new StringBuffer("https://hljnh.govpay.ccb.com/online/bmjf/MainPlat?Vno=2");
		//StringBuffer urlhg = new StringBuffer("http://govpaytesthljhgjj.mytunnel.site/online/bmjf/MainPlatSec");
		StringBuffer urlhg = new StringBuffer("https://hljhgjj.govpay.ccb.com/online/bmjf/MainPlatSec");
		//正式
		String MrchCd = "105000086510315";
		//String MrchCdhg = "105000086510466";
		
		String MrchCdhg = "105000086511618";
		//测试
		//String MrchCd = "105000086511237";
		//String MrchCd = "105000086510009";
		// 找到登录的用户
		StringBuffer urlSrc = new  StringBuffer("Vno=2");
		try {
			if(dto.getToken() != null && !dto.getToken().equals("wx") && !dto.getToken().equals("wxhg")) {
				token = tokenMapper.findInfoByToken(dto.getToken());
			}
			String userId = "";
			User user = new User();
			if (userInfo != null) {
				userId = userInfo.getUserId();
				//user = userMapper.findUserByUserId(userId);
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
				//userId="159456956850";
				//Usr_ID="&Usr_ID=159456956850";
			}
			String IsMobile="";
			if (!StringUtils.isBlank(dto.getIsMobile())) {
				IsMobile="&IsMobile=" + dto.getIsMobile();// 登陆用户的用户号
			}else {
				IsMobile="&IsMobile=0";
			}
			
			String name ="";
			String nameSrc="";
			if (!StringUtils.isBlank(userInfo.getName())) {
				//userInfo.setName("aa");
				//nameSrc=URLEncoder.encode(userInfo.getName(),"UTF-8");
				if(city == 0) {
					nameSrc=userInfo.getName();
				}else {
					nameSrc=userInfo.getName();
					//nameSrc=URLEncoder.encode(userInfo.getName(), "UTF-8");
				}
				
				name=URLEncoder.encode(userInfo.getName(), "UTF-8");
				//name =URLEncoder.encode(URLEncoder.encode(userInfo.getName(),"UTF-8"),"UTF-8");
			}else {
				user.setName("aa");
				name =URLEncoder.encode(user.getName(),"utf-8");
				name="";
			}

			String email = "";
			if (!StringUtils.isBlank(userInfo.getEmail())) {
				email="&Email="+userInfo.getEmail();
			}else {
				//email = "&Email=";
			}
			if(city == 1) {
				//url = urlhg;
				MrchCd = MrchCdhg;
			}
			//StringBuffer url = new  StringBuffer("https://govpaytesthljnh.mytunnel.site/online/bmjf/MainPlat?Vno=2");
			//StringBuffer url = new StringBuffer("https://hljnh.govpay.ccb.com/online/bmjf/MainPlat?Vno=2");
			url.append("&Sgn_Algr=SHA256withRSA")
			   .append("&CstPty_Ordr_No=")//105000086510048(测试商户)--105000086510315(正式商户)
			   .append("&Fee_Itm_Prj_User_No=")
			   //.append("&MrchCd=105000086511237")
			   .append("&MrchCd="+MrchCd)
			   .append("&PyF_ClCd="+dto.getPaytype())
			   .append(Rgon_cd)
			   .append("&Fee_Itm_PyF_MtdCd=00")
			   .append(Usr_ID) .append("&Cst_Nm="+name).append("&Crdt_Tp=1010")
			   .append("&Crdt_No="+userInfo.getSfzjh()).append("&MblPh_No="+userInfo.getUserId()) .append(email).append("&Ret_Url=")
			   .append("&Tms=" + date).append(IsMobile);
			
			urlSrc.append("&Sgn_Algr=SHA256withRSA")
			   .append("&CstPty_Ordr_No=")
			   .append("&Fee_Itm_Prj_User_No=")
			   //.append("&MrchCd=105000086511237")
			   .append("&MrchCd="+MrchCd)
			   .append("&PyF_ClCd="+dto.getPaytype())
			   .append(Rgon_cd)
			   .append("&Fee_Itm_PyF_MtdCd=00")
				.append(Usr_ID) .append("&Cst_Nm="+nameSrc) .append("&Crdt_Tp=1010").append("&Crdt_No="+userInfo.getSfzjh())
				.append("&MblPh_No="+userInfo.getUserId()) .append(email) .append("&Ret_Url=")
			   .append("&Tms=" + date).append(IsMobile);
			String signInfo = new String();
			String bsn_Data = new String();
			if(city == 0) {
				signInfo = this.sign(urlSrc.toString());
				signInfo = signInfo.replace("+", "%2B");
				url.append("&SIGN_INF="+signInfo);// 商户签名
			}else {
				//鹤岗签名方式
		        String[] str = encryDemo.newRequestZrzfDncryptParam(new String(urlSrc),PRIVATE_KEY_PRO, PUBLIC_KEY_PRO);
				signInfo = str[1];
				bsn_Data = str[0];
			}
			
			//signInfo = signInfo.replace("+", "Q3QQQ");
			
			//System.out.println(url.toString()); System.out.println(urlSrc.toString());
			 
			httpSession.setAttribute("url",url);
			
			if(!StringUtils.isBlank(dto.getIsMobile()) && wxfrom == false && city == 0) {
				return ResponseDto.buildSuccess().setData(url.toString());
			}else if(city == 1) {
				//鹤岗新验证方法
				if(wxfrom == false) {
					//String s = HttpRequestfor.sendPost(new String(urlhg),"Tprt_Mode=1&Tprt_Parm="+signInfo+"&Bus_Data="+bus_Data);
					Ccbpara c = new Ccbpara();
					c.setUrl(new String(urlhg));
					c.setBsn_Data(bsn_Data);
					c.setTprt_Mode("1");
					c.setTprt_Parm(signInfo);
					return ResponseDto.buildSuccess().setData(c);
				}else {
					this.getResponse().sendRedirect("../indexhg.html?url="+new String(urlhg)+"&Bsn_Data="+bsn_Data+"&Tprt_Mode=1&Tprt_Parm="+signInfo);
					return null;
				}
				
			}else {
				//return ResponseDto.buildSuccess().setData(url.toString());
				this.getResponse().sendRedirect(url.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	 
	@RequestMapping(value = "searchHistory", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseDto searchHistory(ReqPayCostDto dto,HttpSession httpSession) throws Exception {
//		boolean wxfrom = false;
//		User userInfo = new User();
//		if(dto.getToken() == null) {
//			dto = (ReqPayCostDto) httpSession.getAttribute("dto");
//			wxfrom =true;
//		}else {
//			Object openid = httpSession.getAttribute("openid");
//			if(openid == null) { 
//				 httpSession.setAttribute("dto", dto);
//				 return ResponseDto.buildSuccess().setData("wxLogin");
//			}else {
//				userInfo = userMapper.checkOpenIdAndS(openid.toString());
//				if( wxfrom == false) {
//					return search(userInfo);
//				}else {
//					this.getResponse().sendRedirect(search(userInfo).getData().toString());
//					return null;
//				}
//			}
//		}
//		
		
		//String url = "https://https://hljqqhejj.govpay.ccb.com/online/bmjf/payment/orderHistory.html?";
		String url = "http://hljqqhejj.gp.mytunnel.site:8029/online/bmjf/payment/orderHistory.html?";
		//String OtsdMrchcd = "Vno=2&OtsdMrchcd=230000006";
		String OtsdMrchcd = "Vno=2&OtsdMrchcd=230000007";
		String Sgn_Algr = "&Sgn_Algr=SHA256withRSA";
		//String Usr_ID = "&Usr_ID="+userInfo.getUserId();
		String Usr_ID = "&Usr_ID=1234567890";
		String IsMobile="&IsMobile=1";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = format.format(new Date());
		String Tms="&Tms="+date;
		String SIGN_INF="&SIGN_INF=";
		String srcStr = OtsdMrchcd + Sgn_Algr + Usr_ID + IsMobile + Tms;
		String signInfo = SIGN_INF+sign(srcStr);
		signInfo = signInfo.replace("+", "%2B");
		String alurl = url+srcStr+signInfo;
		return ResponseDto.buildSuccess().setData(alurl);
	}
	
	public ResponseDto search(User userInfo) throws Exception {

		//String url = "https://hljnh.govpay.ccb.com/online/bmjf/payment/orderHistory.html?";
		String url = "http://govpaytesthljnh.mytunnel.site/online/bmjf/payment/orderHistory.html?";
		//String OtsdMrchcd = "Vno=2&OtsdMrchcd=230000006";
		String OtsdMrchcd = "Vno=2&OtsdMrchcd=230000005";
		String Sgn_Algr = "&Sgn_Algr=SHA256withRSA";
		String Usr_ID = "&Usr_ID="+userInfo.getUserId();
		String IsMobile="&IsMobile=1";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = format.format(new Date());
		String Tms="&Tms="+date;
		String SIGN_INF="&SIGN_INF=";
		String srcStr = OtsdMrchcd + Sgn_Algr + Usr_ID + IsMobile + Tms;
		String signInfo = SIGN_INF+sign(srcStr);
		signInfo = signInfo.replace("+", "%2B");
		String alurl = url+srcStr+signInfo;
		return ResponseDto.buildSuccess().setData(alurl);
	}


	// 生成商户签名
	private static String sign(String srcStr) {
		String signInf = "";
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
	
	public static void main(String[] args) {
		 //String[] str = encryDemo.newRequestZrzfDncryptParam("Vno=2&OtsdMrchcd=230000005&Sgn_Algr=SHA256withRSA&Usr_ID=1234567890&IsMobile=1&Tms=20190111",PRIVATE_KEY, PUBLIC_KEY);
		String srcStr = "Vno=2&OtsdMrchcd=230000005&Sgn_Algr=SHA256withRSA&Usr_ID=1234567890&IsMobile=1&Tms=20200301145941";
		String prikey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";
		String[] str = encryDemo.newRequestZrzfDncryptParam(new String(srcStr),PRIVATE_KEY_PRO, PUBLIC_KEY_PRO);
		 System.out.println(str[1]);
		 System.out.println(sign(srcStr));
	}
}
