package com.mscs.app.web.controller;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 	
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;

/**
 * @program: Inspur.Operation
 * @description:
 * @author: lee
 * @author: 1170370113@qq.com
 **/
@Controller
@RequestMapping(value = "/file2")
public class AffairsSendFileController2 {
	private String uploadURL = "http://221.209.84.8:38885/WebDiskServerDemo/upload";
    /**
     * 访问的网站响应时间有点慢，所以设置的时间比较大
     */
    private final RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .setSocketTimeout(15000)
            .setStaleConnectionCheckEnabled(true)
            .build();


    /**
     * 文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file, @RequestParam Map<String, String> map, HttpServletRequest request) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        JSONObject result = new JSONObject();

        try {

            httpClient = HttpClients.createDefault();
            String url = uploadURL;
            String uid = map.get("uid");
            String folderName = map.get("folder_name");
            String type = map.get("type");
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(config);

            //指定文件上传的位置
            String path = request.getSession().getServletContext().getRealPath("/upload/");
            //判断该路径是否存在
            File tmpFile = new File(path);
            if (!tmpFile.exists()) {
                //不存在就创建一个路径
                tmpFile.mkdirs();
            }
            //获取上传文件的名称
            String filename = file.getOriginalFilename();
            //把文件名称设置成唯一值uuid
            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename = uuid + "_" + filename;
            File tempFile = new File(path, filename);

            //完成上传文件
            file.transferTo(tempFile);


            FileBody fileBody = new FileBody(tempFile);
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    //.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName)// 文件流
                    .addPart("file", fileBody)
                    .addPart("uid", new StringBody(uid, ContentType.create(
                            "text/plain", Consts.UTF_8)))
                    .addPart("folder_name", new StringBody(folderName, ContentType.create(
                            "text/plain", Consts.UTF_8)))
                    .addPart("type", new StringBody(type, ContentType.create(
                            "text/plain", Consts.UTF_8)))
                    .build();
            httpPost.setEntity(reqEntity);
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            String resultstr = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
            // 销毁
            EntityUtils.consume(resEntity);
            //删除临时文件
            tempFile.delete();
            if (resEntity != null) {
                return (JSONObject)JSONObject.parseObject(resultstr);
            } else {
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件下载
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject download(@RequestParam(value = "docId") String docId, @RequestParam(value = "fileName") String fileName, HttpServletResponse response) {
    	JSONObject result = new JSONObject();
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            String url = "";

            url = url + "?doc_id=" + docId;

            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(config);
            // 发起请求 并返回请求的响应
            closeableHttpResponse = httpClient.execute(httpGet);

            InputStream inputStream = closeableHttpResponse.getEntity().getContent();
            // 获取响应对象

            byte[] buffer = readInputStream(inputStream);
            if (null != buffer && buffer.length > 0) {
                // 清空response
                response.reset();
                // 设置response的Header
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                response.setContentType("application/x-download");// 定义输出类型
                response.setContentType("multipart/form-data");//解决在firefox浏览器下页面中的文件类型显示错误
                OutputStream toClient = response.getOutputStream();
                //二进制文件流下载
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } else {
                return (JSONObject) JSONObject.parse(EntityUtils.toString(closeableHttpResponse.getEntity(), Charset.forName("UTF-8")));
            }


        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            try {
                if (response != null) {
                    closeableHttpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    private byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}