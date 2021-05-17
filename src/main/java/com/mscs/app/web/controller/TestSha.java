package com.mscs.app.web.controller;

import java.util.Map;

/*import com.autograph.SHA256withRSA;*/




public class TestSha {

	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
	public static final String ENCODE_ALGORITHM = "SHA-256";
	public static final String PLAIN_TEXT = "哈哈大家看见发看剧场版里";
	public static String pubkey = "";
	public static String prikey = "";
	public static String sing_byte = "";
	/*public static void main(String[] args) {
		// 公私钥对
		generateKeyBytes();
		// 签名
	    sign();
		// 验签
		verifySign();
	}
	
	// 生成公私钥对
	public static void generateKeyBytes() {
		// 公私钥对
		Map<String, String> keyMap = SHA256withRSA.generateKeyBytes();
		pubkey =  keyMap.get(SHA256withRSA.PUBLIC_KEY) ;
		prikey =  keyMap.get(SHA256withRSA.PRIVATE_KEY) ;
		System.out.println("公钥 = " + pubkey);
		System.out.println("私钥 = " + prikey);
	}
	 
	// 签名
	public static void sign() {
		// 签名
		sing_byte = SHA256withRSA.sign(prikey, PLAIN_TEXT);
		System.out.println("签名 = " + sing_byte);
	}
	// 签名
	public static void verifySign() {
		// 验签
		boolean flag = SHA256withRSA.verifySign(pubkey, PLAIN_TEXT, sing_byte);
		System.out.println(flag);
				
	}
	*/
	
	

}
