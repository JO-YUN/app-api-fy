package com.mscs.app.common.help;

import com.ccb.govpay.sign.SignUtil;

public class SignDemo {

	public static	void main(String[] args) {

		//测试生成密钥对
		String[] keyPair = testGenKeyPair();
		
		
		String publicKey = keyPair[0];//公钥
		String privateKey =keyPair[1];//私钥
		
		String srcStr = "Vno=1&MrchCd=105000086510005&PyF_CLCd=01002&Urbn_Cd=430100&IttParty_Tms=20180613203611";//需要生成签名的原串
//		String srcStr1 = "Py_Chnl_Cd=1&OnLn_Ofln_IndCd=1&Cmdty_Ordr_No=20180101123456&Opr_No=123456&Usr_ID=&Ccy=156&PgFc_Ret_URL_Adr=http://order.zwgl.com&Fee_Itm_Cd=1234&Fee_Itm_Prj_Nm=水费&RvPyUnt_Cd=8891&RvPyUnt_Nm=长沙水务公司&Fee_Itm_Prj_Usr_No=001022345623&Fee_Itm_Prj_Amt=80";
		String signInf = testSign(srcStr, privateKey);
		
		testVerify(srcStr, signInf, publicKey);
		
		

	}
	
	/**
	 * 测试生成签名方法
	 * @param srcStr
	 * @param privateKey
	 * @return
	 */
	
	public static String testSign(String srcStr, String privateKey) {

		System.out.println("开始测试签名...");
		String signInf = SignUtil.sign(srcStr, privateKey);

		System.out.println("签名=" + signInf);
		return signInf;


	}
	
	/**
	*  测试验签方法
	*  @param srcStr
	*  @param signInf
	*  @param publicKey
	 */
	public static void testVerify(String srcStr, String signInf, String publicKey) {
		
		System.out.println("开始验签...");

		System.out.println("验签结果=" + SignUtil.verify(srcStr, signInf, publicKey));
	}

	/**
	*  测试生成密钥对
	*  @return
	 */
	public static String[] testGenKeyPair() {

		System.out.println("开始生成密钥对...");

		String[] keyPair = SignUtil.generateKeyPair();

		System.out.println("公钥:" + keyPair[0]);

		System.out.println("私钥:" + keyPair[1]);
		return keyPair;

	}
}
