package com.mscs.app.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/*import com.ccb.govpay.sign.SignUtil;*/
import com.mscs.app.common.dto.req.ReqPayCostDto;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.dao.TokenMapper;
import com.mscs.app.web.model.Token;
/**
 * @author LiuGuoHui
 * @date 2018-10-09
 *最初方法
 */
@Controller
@RequestMapping("bmjf111")
public class PayCostController extends BIZController {
	public static final Logger logger = LoggerFactory.getLogger(PayCostController.class);
	@Autowired
	private TokenMapper tokenMapper;
	/**
	 * 便民缴费
	 * 
	 * @throws Exception
	 */
	/*@RequestMapping(value = "pay", method = {RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void payCost(ReqPayCostDto dto) throws Exception {
		// 拼接
		// 找到登录的用户
		System.out.println("城市代码1"+dto.getCityCode());
		System.out.println("城市代码2"+dto.getXzqydm());
		try {
			Token token = tokenMapper.findInfoByToken(dto.getToken());
			String userId = "";
			if (token != null) {
				userId = token.getUserId();
			}
			dto.setXzqydm("230281");
			dto.setPaytype("01006");
			// 获取当前时间
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = format.format(new Date());
			//StringBuffer url = new StringBuffer("https://govpaytesthljnh.mytunnel.site/online/bmjf/MainPlat?Vno=1");
			StringBuffer url = new StringBuffer("https://hljnh.govpay.ccb.com/online/bmjf/MainPlat?Vno=1");

			//url.append("&MrchCd=105000086510315");// 商户代码（必输）
			url.append("&MrchCd=105000086510315");// 商户代码（必输）
			url.append("&PyF_ClCd=" + dto.getPaytype());// 缴费类型代码
			if(!StringUtils.isBlank(dto.getXzqydm()))
			{
			  url.append("&Admn_Rgon_Cd="+dto.getXzqydm());// 行政地区代码（非必输）
			}
			url.append("&Fee_Itm_PyF_MtdCd=00");// 费项缴费方式代码（00-账单式；01-充值式）
			if(!StringUtils.isBlank(userId))
			{
				url.append("&Usr_ID="+userId);// 登陆用户的用户号
			}
			url.append("&Tms=" + date);
			StringBuffer srcStr=new StringBuffer("Vno=1&MrchCd=105000086510315");
			srcStr.append("&PyF_ClCd=" + dto.getPaytype());
			if(!StringUtils.isBlank(dto.getXzqydm()))
			{
				srcStr.append("&Admn_Rgon_Cd="+dto.getXzqydm());
			}
			srcStr.append("&Fee_Itm_PyF_MtdCd=00");
			if(!StringUtils.isBlank(userId))
			{
			srcStr.append("&Usr_ID="+userId);
			}
			srcStr.append("&Tms="+date);
			
			srcStr.append("&isMobile=0");
			
			//System.out.println("原串："+srcStr);
			String signInfo = this.sign(srcStr.toString());// 需要生成签名的原串
			url.append("&isMobile=0");// 手机端标志，0-PC端，1-手机APP，2-微信公众号
			url.append("&SIGN_INF=" + signInfo);// 商户签名--需要MD5加密
			//System.out.println("路径"+url);
			this.getResponse().sendRedirect(url.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String payCostType(String type) {
		// 缴费类型01001-水费;01002-电费;01003-燃气费;
		// 01004-物业费;01005-热力费;01006-手机话费;
		// 01007-宽带费;01008-有线电视; 01009-固话话费
		// 010010-交通罚款
		String typeCode = "";
		if (type.equals("1")) {// 手机话费
			typeCode = "01006";
		} else if (type.equals("2")) {// 交通罚款
			typeCode = "010010";
		} else if (type.equals("3")) {// 电费
			typeCode = "01002";
		}else if (type.equals("4")) {// 固话话费
			typeCode = "01009";
		}
		return typeCode;
	}

	// 生成商户签名
	private String sign(String srcStr) {
		String signInf="";
		try {
			//srcStr = srcStr.substring(srcStr.indexOf("Vno=1"));
			String[] keyPair = SignUtil.generateKeyPair();// 生成秘钥
			//String publicKey = keyPair[0];// 公钥
			//String privateKey = keyPair[1];// 私钥
			String publicKey = "04211EDB1B5AF2DE940D1987D9183A6B57697B029FA673B2969DDF8AC3E0D46A7965BA46E7446634BC6BD3752C9BE86B85B61787367EB5AB487325ABCF9B33A833";// 公钥
			String privateKey = "5D19331A49CEF7A355A4E7B879B7FB5C4567E9A2F8C6AD18AAC5BBA8BD7A3692";// 私钥
			System.out.println("srcStr"+srcStr);
			System.out.println("公钥:" + publicKey);
			System.out.println("私钥:" +privateKey);
			signInf = SignUtil.sign(srcStr, privateKey);
			//System.out.println("签名:" +signInf);
			//signInf=MD5Utils.getMD5Token32(signInf);//给签名加密
			//System.out.println("验签结果=" + SignUtil.verify(srcStr, signInf, publicKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return signInf;
	}*/
}
