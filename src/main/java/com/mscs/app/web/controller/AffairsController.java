package com.mscs.app.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.util.HttpForAffairs;
import com.mscs.app.web.controller.base.BIZController;
@Controller
@RequestMapping("affairs")
public class AffairsController extends BIZController{
	
	private String getRegionOrgenByRegionCode = "http://221.209.84.8:38886/web/getRegionOrgenByRegionCode";
	private String getItemListByPage = "http://221.209.84.8:38887/Inspur.Ecgap.PowerManager/main/power/getItemListByPage";
	private String getItemInfoByItemCode = "http://221.209.84.8:38887/Inspur.Ecgap.PowerManager/main/power/getItemInfoByItemCode";
	private String getFormInfo = "http://221.209.84.8:38888/Inspur.Dzzw.CloudAccept/service/getFormInfo";
	private String saveData= "http://221.209.84.8:38889/cform/saveData";
	private String webApply = "http://221.209.84.8:38888/Inspur.Dzzw.CloudAccept/service/webApply";
	private String getBusinessList = "http://221.209.84.8:38888/Inspur.Dzzw.CloudAccept/service/getBusinessList";
	private String UploadUtil = "http://221.209.84.8:38885/WebDiskServerDemo/upload";
	
	@RequestMapping(value = "getRegionOrgenByRegionCode", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JSONObject getRegionOrgenByRegionCode(@RequestBody Map<String, String> map) throws ParseException, AppException, HttpException, IOException {
		return new HttpForAffairs().getRequest(getRegionOrgenByRegionCode+"?regionCode="+map.get("regionCode"));
	}
	
	@RequestMapping(value = "getItemListByPage", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JSONObject getItemListByPage(@RequestBody Map<String, String> map) throws ParseException, AppException, HttpException, IOException {
		return new HttpForAffairs().postRequest(getItemListByPage,map);
	}
	
	@RequestMapping(value = "getItemInfoByItemCode", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JSONObject getItemInfoByItemCode(@RequestBody Map<String, String> map) throws ParseException, AppException, HttpException, IOException {
		String para = "?";
		for(Map.Entry<String, String> m : map.entrySet()) {
			para += m.getKey()+"="+m.getValue()+"&";
		}
		
		return new HttpForAffairs().getRequest(getItemInfoByItemCode+para);
	}
	
	@RequestMapping(value = "getFormInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JSONObject getFormInfo(@RequestBody Map<String, String> map) throws ParseException, AppException, HttpException, IOException {
		String para = "?";
		for(Map.Entry<String, String> m : map.entrySet()) {
			para += m.getKey()+"="+m.getValue()+"&";
		}
		return new HttpForAffairs().getRequest(getFormInfo+para);
	}
	
	@RequestMapping(value = "saveData", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JSONObject saveData(@RequestBody Map<String, String> map) throws ParseException, AppException, HttpException, IOException {
		String formId = map.get("formId");
		if(formId != null) {
			map.remove("formId");
		}
		JSONObject re = new HttpForAffairs().postRequestAPP(saveData+"?formId="+formId,map);
		return re;
	}
	
	@RequestMapping(value = "webApply", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JSONObject webApply(@RequestBody Map<String, String> map) throws ParseException, AppException, HttpException, IOException {
//		String para = "?";
//		for(Map.Entry<String, String> m : map.entrySet()) {
//			para += m.getKey()+"="+m.getValue()+"&";
//		}
		//String paraS = JSON.toJSONString(map);
		Map<String,String> mpara = new HashMap<String, String>();
		String paraS = URLDecoder.decode(map.get("postdata"),"utf-8");
		paraS = paraS.replace("\\", "");
//		paraS = paraS.substring(1);
//		paraS = paraS.substring(0,paraS.length()-1);
//		Object o = JSON.parse(paraS);
//		HashMap<String,Object> omap = (HashMap)o;
		
		//mpara.put("postdata",(String)o);
		
		mpara.put("postdata", paraS);
		return new HttpForAffairs().postRequest(webApply, mpara);
	}
	
	@RequestMapping(value = "getBusinessList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public JSONObject getBusinessList(@RequestBody Map<String, String> map) throws ParseException, AppException, HttpException, IOException {
		String para = "?";
		for(Map.Entry<String, String> m : map.entrySet()) {
			para += m.getKey()+"="+m.getValue()+"&";
		}
		return new HttpForAffairs().getRequest(getBusinessList+para);
	}
	
}
