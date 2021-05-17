package com.mscs.app.web.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.resp.RespAutoCityDto;
import com.mscs.app.common.util.HttpClientHelper;
import com.mscs.app.common.util.PropertiesUtil;
import com.mscs.app.web.dao.AutoCityMapper;
import com.mscs.app.web.service.AutoCityService;

@Service("AutoCityServiceImpl")
public class AutoCityServiceImpl implements AutoCityService {

	public static Logger logger = LoggerFactory
			.getLogger(AutoCityServiceImpl.class);

	public static final long LOST_TIME = 86400000l;

	@Autowired
	private AutoCityMapper autoCityMapper;
	@Override
	public RespAutoCityDto getAddresses(HttpServletRequest request) throws IOException {
		String clientIp = getClientIpAddress(request);
		/**获取配置文件*/
		Properties properties = PropertiesUtil.getKey("system.properties");
		String url_oa=properties.getProperty("auto_city");
		String charset="utf-8";
		/**添加请求参数*/
		Map<String, Object> params=new HashMap<>();
		//部署到正式环境则注释下面
		clientIp="218.10.5.216";
		params.put("ip", clientIp);
		
		RespAutoCityDto dto = new RespAutoCityDto();
		/**发送请求*/
		String bzdm = "";
		try {
			//获取返回字符串
			String returnStr = HttpClientHelper.sendGet2(url_oa, params,charset);
			System.out.println("returnStr "+returnStr);
			 if (returnStr != null) {  
				   // 处理返回的省市区信息  
				   String[] temp = returnStr.split(",");  
				   if(temp.length<3){  
				    return dto;//无效IP，局域网测试  
				   }  
				    String region = (temp[4].split(":"))[1].replaceAll("\"", "");  
				    
		            String country = (temp[6].split(":"))[1].replaceAll("\"", "");
		            String area = (temp[3].split(":"))[1].replaceAll("\"", "");  
		            String area_id = (temp[9].split(":"))[1].replaceAll("\"", "");
		            String city = (temp[5].split(":"))[1].replaceAll("\"", "");
		            String city_id = (temp[11].split(":"))[1].replaceAll("\"", "");
		            String county = "";  
		            String isp = "";  
		            
		      if(StringUtils.isNotBlank(area_id))
		      {
		    	  System.out.println("区县："+area_id);
		    	  dto.setCityCode(area_id);
		    	  dto.setCityName(area);
		    	  bzdm = autoCityMapper.findBzdmByCode(area_id);
		      }
		      else if(StringUtils.isNotBlank(city_id))
		      {
		    	  
		    	   //客户说齐齐哈尔默认切到讷河
		    	  if(city_id.equals("230200")||city_id.equals("local"))
		    	  {
		    		  dto.setCityCode("230281");
			    	  dto.setCityName("讷河市");
			    	  bzdm="230281";
		    	  }else {
		    	  
		    	  bzdm = autoCityMapper.findBzdmByCode(city_id);
		    	  if(StringUtils.isEmpty(bzdm))
		    	     { dto.setCityCode("230281");
			    	  dto.setCityName("讷河市");
			    	  bzdm="230281"; }else
			    	  {
				    	  dto.setCityCode(city_id);
				    	  dto.setCityName(city);
			    	  }
		    	  }
		    	  
		      }else
		      {
		    	  dto.setCityCode("230281");
		    	  dto.setCityName("讷河市");
		    	  bzdm="230281";
		    	 
		    	  return dto;
		      }
		    System.out.println(country+"="+area+"="+region+"="+city+"="+county+"="+isp);  
		 }
		} catch (Exception e) {
			  dto.setCityCode("230281");
	    	  dto.setCityName("讷河市");
	    	  bzdm="230281";
	    	  return dto;
		}
		
		return dto;
	}

	// java 后台获取访问客户端ip地址 
		protected String getClientIpAddress(HttpServletRequest request) {  
			 String ip = request.getHeader("x-forwarded-for");
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("Proxy-Client-IP");
			    }
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("WL-Proxy-Client-IP");
			    }
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("HTTP_CLIENT_IP");
			    }
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			    }
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    	ip = request.getRemoteAddr();  
	                if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){  
	                    //根据网卡取本机配置的IP  
	                    InetAddress inet=null;  
	                    try {  
	                        inet = InetAddress.getLocalHost();  
	                    } catch (UnknownHostException e) {  
	                        e.printStackTrace();  
	                    }  
	                   ip= inet.getHostAddress();  
	                }  
	            }  
	            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	            if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15  
	                if(ip.indexOf(",")>0){  
	                	ip = ip.substring(0,ip.indexOf(","));  
	                }  
	            }  
	        return ip;  
	    }
		/** 
		  * unicode 转换成 中文 
		  * 
		  * @author fanhui 2007-3-15 
		  * @param theString 
		  * @return 
		  */  
		 public static String decodeUnicode(String theString) {  
		  char aChar;  
		  int len = theString.length();  
		  StringBuffer outBuffer = new StringBuffer(len);  
		  for (int x = 0; x < len;) {  
		   aChar = theString.charAt(x++);  
		   if (aChar == '\\') {  
		    aChar = theString.charAt(x++);  
		    if (aChar == 'u') {  
		     int value = 0;  
		     for (int i = 0; i < 4; i++) {  
		      aChar = theString.charAt(x++);  
		      switch (aChar) {  
		      case '0':  
		      case '1':  
		      case '2':  
		      case '3':  
		      case '4':  
		      case '5':  
		      case '6':  
		      case '7':  
		      case '8':  
		      case '9':  
		       value = (value << 4) + aChar - '0';  
		       break;  
		      case 'a':  
		      case 'b':  
		      case 'c':  
		      case 'd':  
		      case 'e':  
		      case 'f':  
		       value = (value << 4) + 10 + aChar - 'a';  
		       break;  
		      case 'A':  
		      case 'B':  
		      case 'C':  
		      case 'D':  
		      case 'E':  
		      case 'F':  
		       value = (value << 4) + 10 + aChar - 'A';  
		       break;  
		      default:  
		       throw new IllegalArgumentException(  
		         "Malformed      encoding.");  
		      }  
		     }  
		     outBuffer.append((char) value);  
		    } else {  
		     if (aChar == 't') {  
		      aChar = '\t';  
		     } else if (aChar == 'r') {  
		      aChar = '\r';  
		     } else if (aChar == 'n') {  
		      aChar = '\n';  
		     } else if (aChar == 'f') {  
		      aChar = '\f';  
		     }  
		     outBuffer.append(aChar);  
		    }  
		   } else {  
		    outBuffer.append(aChar);  
		   }  
		  }  
		  return outBuffer.toString();  
		 }  

}

