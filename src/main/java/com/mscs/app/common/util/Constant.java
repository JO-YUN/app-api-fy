package com.mscs.app.common.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 静态常量
 * @author LiuGuoHui
 *
 */
public class Constant {
	public static final String server_url = "http://www.data0452.com/app-api";
	/*邮件附件上传最大数量*/
	public static final int MAX_FILE_SIZE=20;
	/*email缓存文件位置*/
	public static   String emailTemp="emailTemp";
	/*news新闻缓存文件位置*/
	public static String newsTemp="newsTemp";
	/*notice公告缓存位置*/
	public static String noticeTemp="noticeTemp";
	/*工作流缓存位置*/
	public static String  workflowTemp="workflowTemp";
	/*报告流缓存位置*/
	public static String presentationTemp="presentationTemp";
	/*会议流缓存位置*/
	public static String conferenceTemp="conferenceTemp";
	
	/*email邮件文件位置*/
	public static String emailFile="emailFile";
	/*news新闻文件位置*/
	public static String newsFile="newsFile";
	/*notice公告文件位置*/
	public static String noticeFile="noticeFile";
	/*工作流文件位置*/
	public static String workflowFile="workflowFile";
	/*报告文件位置*/
	public static String presentationFile="presentationFile";
	/*会议文件位置*/
	public static String conferenceFile="conferenceFile";
	/*媒体文件位置*/
	public static String mediaFile="mediaFile";
	/*媒体文件缓存位置*/
	public static String mediaTemp="mediaTemp";
	
	/*图片预览路径*/
	public static String  showImgUrl="file/showImg?attrId=";
	/*图片预览路径2.0*/
	public static String  showImg2Url="file/showImg2?attrId=";
	/*文件正式目录位置*/
	public static String file="file";
	/*文件临时根目录位置*/
	public static String temp="temp";
	
	/*文件正式目录位置*/
	public static String fileDir="fileDir";
	/*文件临时根目录位置*/
	
	public static String tempDir="tempDir";
	/*动态创建表，表的	prefix , suffix*/
	public static String   TABLE_PREFIX="T_";
	public static String   TABLE_SUFFIX="_ATTR";
	public static String  SEQ_PREFIX="SEQ_";
	public static String   SEQ_SUFFIX="_ATTRID";
	public static String   FK_SUFFIX="ID";
	/**
	 * 图片类型
	 */
	public static String IMAGETYPE="jpeg,jpg,png,gif,bmp"; 
	
	/**
	 * 
	* @MethodName: getUUID 
	* @description : 获取UUID
	* @author：FileService
	* @date： 2018年10月16日 
	* @return String
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 数据库
	 * 逻辑删除标识，sys_state。1有效，0无效
	 */
	public static final int SYS_STATE_0=0; 
	/**
	 * 数据库
	 * 逻辑删除标识，sys_state。1有效，0无效
	 */
	public static final int SYS_STATE_1=1; 
	
	 public static boolean isPhone(String str) { 
        Pattern p1 = null;
        Matcher m = null;
        boolean b = false;  
        p1 = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");  // 验证手机号的
        if(str.length() >9)
        {   m = p1.matcher(str);
            b = m.matches();  
        } 
        return b;
	    }
	
}
