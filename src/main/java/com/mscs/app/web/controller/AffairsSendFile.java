package com.mscs.app.web.controller;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

 
/**
 *发送日志文件工具类
 */
public class AffairsSendFile {
 
 
    /**
     * 发送日志文件方法
     * @param url 接收文件接口连接
     * @param file  发送文件
     */
   public static String sendFile(String url, File file) {
        if (file.exists()) {
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            postMethod.setParameter("uid", "15911006517");
            postMethod.setParameter("type", "doc");
            postMethod.setParameter("folder_name", "//");
            try {
                // FilePart：用来上传文件的类,file即要上传的文件
                FilePart fp = new FilePart("", file);
                Part[] parts = { fp };
 
                // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
                MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
                postMethod.setRequestEntity(mre);
                // 由于要上传的文件可能比较大 , 因此在此设置最大的连接超时时间
                //client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
 
                int status = client.executeMethod(postMethod);
                System.out.println("1111111111");
                if (status == HttpStatus.SC_OK) {
                	System.out.println("222222222");
                    // 获取返回数据
                	byte[] b = postMethod.getResponseBody();
                    String body = new String(b);
                    System.out.println("66666666666"+body);
                    return body;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("44444444444444");
                return null;
            } finally {
                // 释放连接
                postMethod.releaseConnection();
            }
        }else {
        	return "{msg:\"no file\"}";
        }
		return null;
    }
 
}
