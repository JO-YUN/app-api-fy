package com.mscs.app.common.util;


public class TestDemo {
	/**
     * 签名私钥 使用请求政融签名原有私钥 否则会导致验签不过。
     */
    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";

    /**
     * 使用政融支付平台公钥对加密密钥进行RSA非对称加密 此处密钥更换为具体公钥
     */
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkxJVDKgDhDGS/OCBDmqD1F5j8yzeimmEmaFjz3w+oO6vUDpJ+bGesONsK0Ue1ttOyFBm0x1IRRmJJWTTL9NSNm+f8P1nttZiRSdWIgOjXwxZvasndDN9cUArilIGPNhpLxkHaZkzvym/GKPPbr/p5ys/iFFjZsxGTJ5k84KnK83yC503TGqLweOp24/ghJZO80lPH3ZNTQUqmV4JodTdRvirJbPAZBoc2lUkizYX4NitAqCDnXXFN8JT9C2tJONy6s8JRsuXC7Y6+kffXTxUne2UAvuwkyBJtMsiH3a38yj9V9PLbggOST9gpUG7ISwe2PGWamYsx0/tJJR8avUnwwIDAQAB";

    /*public static void main(String[] args) {
		String param = "Vno=2&Sgn_Algr=SHA256withRSA&CstPty_Ordr_No=&Fee_Itm_Prj_User_No=&MrchCd=105000086510466&PyF_ClCd=010010&Admn_Rgon_Cd=&Fee_Itm_PyF_MtdCd=00&Usr_ID=15911006517&Cst_Nm=付英贺&Crdt_Tp=1010&Crdt_No=23040419840831051X&MblPh_No=15911006517&Ret_Url=&Tms=20191221105809&IsMobile=2";
        String[] str = TestDemo.test(param);
        System.out.println("Bus_Data：" + str[0]);
        System.out.println("Encrypt_Key：" + str[1]);
	}
	
	public static String[] test(String s) {
        String[] str = encryDemo.newRequestZrzfDncryptParam(s, PRIVATE_KEY, PUBLIC_KEY);
        return str;
	}*/

}
