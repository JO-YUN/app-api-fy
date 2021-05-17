package com.mscs.app.common.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
* @title: HttpClientHelper 
* @description：TODO
* @author： 刘威巍
* @date： 2018年10月18日 下午3:02:14
 */
public class HttpClientHelper {  
  
    public static String sendPost(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> e : params.entrySet()) {  
                sbParams.append(e.getKey());  
                sbParams.append("=");  
                sbParams.append(e.getValue());  
                sbParams.append("&");  
            }  
        }  
        HttpURLConnection con = null;  
        OutputStreamWriter osw = null;  
        BufferedReader br = null;  
        // 发送请求  
        try {  
            URL url = new URL(urlParam);  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestMethod("POST");  
            con.setDoOutput(true);  
            con.setDoInput(true);  
            con.setUseCaches(false);  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            if (sbParams != null && sbParams.length() > 0) {  
                osw = new OutputStreamWriter(con.getOutputStream(), charset);  
                osw.write(sbParams.substring(0, sbParams.length() - 1));  
                osw.flush();  
            }  
            // 读取返回内容  
            resultBuffer = new StringBuffer();  
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));  
            if (contentLength > 0) {  
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
                String temp;  
                while ((temp = br.readLine()) != null) {  
                    resultBuffer.append(temp);  
                }  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
        }  
  
        return resultBuffer.toString();  
    }  

    public static String sendPost2(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> e : params.entrySet()) {  
                sbParams.append(e.getKey());  
                sbParams.append("=");  
                sbParams.append(e.getValue());  
                sbParams.append("&");  
            }  
        }  
        URLConnection con = null;  
        OutputStreamWriter osw = null;  
        BufferedReader br = null;  
        try {  
            URL realUrl = new URL(urlParam);  
            // 打开和URL之间的连接  
            con = realUrl.openConnection();  
            // 设置通用的请求属性  
            con.setRequestProperty("accept", "*/*");  
            con.setRequestProperty("connection", "Keep-Alive");  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            con.setDoOutput(true);  
            con.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            osw = new OutputStreamWriter(con.getOutputStream(), charset);  
            if (sbParams != null && sbParams.length() > 0) {  
                // 发送请求参数  
                osw.write(sbParams.substring(0, sbParams.length() - 1));  
                // flush输出流的缓冲  
                osw.flush();  
            }  
            // 定义BufferedReader输入流来读取URL的响应  
            resultBuffer = new StringBuffer();  
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));  
            if (contentLength > 0) {  
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
                String temp;  
                while ((temp = br.readLine()) != null) {  
                    resultBuffer.append(temp);  
                }  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                }  
            }  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
  
    /**  
     * @Description:发送get请求保存下载文件  
     * @author:liuyc  
     * @time:2016年5月17日 下午3:27:29  
     */  
    public static void sendGetAndSaveFile(String urlParam, Map<String, Object> params, String fileSavePath) {  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        HttpURLConnection con = null;  
        BufferedReader br = null;  
        FileOutputStream os = null;  
        try {  
            URL url = null;  
            if (sbParams != null && sbParams.length() > 0) {  
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));  
            } else {  
                url = new URL(urlParam);  
            }  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.connect();  
            InputStream is = con.getInputStream();  
            os = new FileOutputStream(fileSavePath);  
            byte buf[] = new byte[1024];  
            int count = 0;  
            while ((count = is.read(buf)) != -1) {  
                os.write(buf, 0, count);  
            }  
            os.flush();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (os != null) {  
                try {  
                    os.close();  
                } catch (IOException e) {  
                    os = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
        }  
    }  
  
    /** 
     * @Description:使用HttpURLConnection发送get请求 
     * @author:liuyc 
     * @time:2016年5月17日 下午3:27:29 
     */  
    public static String sendGet(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        HttpURLConnection con = null;  
        BufferedReader br = null;  
        try {  
            URL url = null;  
            if (sbParams != null && sbParams.length() > 0) {  
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));  
            } else {  
                url = new URL(urlParam);  
            }  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.connect();  
            resultBuffer = new StringBuffer();  
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
  
    /** 
     * @Description:使用URLConnection发送get请求 
     * @author:liuyc 
     * @time:2016年5月17日 下午3:27:58 
     */  
    public static String sendGet2(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        BufferedReader br = null;  
        try {  
            URL url = null;  
            if (sbParams != null && sbParams.length() > 0) {  
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));  
            } else {  
                url = new URL(urlParam);  
            }  
            URLConnection con = url.openConnection();  
            // 设置请求属性  
            con.setRequestProperty("accept", "*/*");  
            con.setRequestProperty("connection", "Keep-Alive");  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 建立连接  
            con.connect();  
            resultBuffer = new StringBuffer();  
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
  
    /**  
     * @Description:使用HttpClient发送post请求  
     */  
    public static String httpClientPost(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(urlParam);  
        // 构建请求参数  
        List<NameValuePair> list = new ArrayList<NameValuePair>();  
        Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();  
        while (iterator.hasNext()) {  
            Entry<String, Object> elem = iterator.next();  
            list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem.getValue())));  
        }  
        BufferedReader br = null;  
        try {  
            if (list.size() > 0) {  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            // 读取服务器响应数据  
            resultBuffer = new StringBuffer();  
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
  
    /**
     * 封装HTTP GET方法
     * 无参数的Get请求
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    public static String get(String url) throws ClientProtocolException, IOException {
        //首先需要先创建一个DefaultHttpClient的实例
    	 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create(url));
        HttpResponse response = httpClient.execute(httpGet);
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP GET方法
     * 有参数的Get请求
     * @param
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws java.io.IOException
     */
    public static String get(String url, Map<String, String> paramMap) throws ClientProtocolException, IOException {
   	 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet();
        List<NameValuePair> formparams = setHttpParams(paramMap);
        String param = URLEncodedUtils.format(formparams, "UTF-8");
        httpGet.setURI(URI.create(url + "?" + param));
        HttpResponse response = httpClient.execute(httpGet);
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        return httpEntityContent;
    }
    /**
     * 设置请求参数
     * @param
     * @return
     */
    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formparams;
    }
    /**
     * 获得响应HTTP实体内容
     * @param response
     * @return
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    private static String getHttpEntityContent(HttpResponse response) throws IOException, UnsupportedEncodingException {
       //通过HttpResponse 的getEntity()方法获取返回信息
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            br.close();
            is.close();
            return sb.toString();
        }
        return "";
    }

    /** 
     * @Description:使用socket发送post请求 
     * @author:liuyc 
     * @time:2016年5月18日 上午9:26:22 
     */  
    public static String sendSocketPost(String urlParam, Map<String, Object> params, String charset) {  
        String result = "";  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        Socket socket = null;  
        OutputStreamWriter osw = null;  
        InputStream is = null;  
        try {  
            URL url = new URL(urlParam);  
            String host = url.getHost();  
            int port = url.getPort();  
            if (-1 == port) {  
                port = 80;  
            }  
            String path = url.getPath();  
            socket = new Socket(host, port);  
            StringBuffer sb = new StringBuffer();  
            sb.append("POST " + path + " HTTP/1.1\r\n");  
            sb.append("Host: " + host + "\r\n");  
            sb.append("Connection: Keep-Alive\r\n");  
            sb.append("Content-Type: application/x-www-form-urlencoded; charset=utf-8 \r\n");  
            sb.append("Content-Length: ").append(sb.toString().getBytes().length).append("\r\n");  
            // 这里一个回车换行，表示消息头写完，不然服务器会继续等待  
            sb.append("\r\n");  
            if (sbParams != null && sbParams.length() > 0) {  
                sb.append(sbParams.substring(0, sbParams.length() - 1));  
            }  
            osw = new OutputStreamWriter(socket.getOutputStream());  
            osw.write(sb.toString());  
            osw.flush();  
            is = socket.getInputStream();  
            String line = null;  
            // 服务器响应体数据长度  
            int contentLength = 0;  
            // 读取http响应头部信息  
            do {  
                line = readLine(is, 0, charset);  
                if (line.startsWith("Content-Length")) {  
                    // 拿到响应体内容长度  
                    contentLength = Integer.parseInt(line.split(":")[1].trim());  
                }  
                // 如果遇到了一个单独的回车换行，则表示请求头结束  
            } while (!line.equals("\r\n"));  
            // 读取出响应体数据（就是你要的数据）  
            result = readLine(is, contentLength, charset);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    is = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
        }  
        return result;  
    }  
  
    /** 
     * @Description:使用socket发送get请求 
     */  
    public static String sendSocketGet(String urlParam, Map<String, Object> params, String charset) {  
        String result = "";  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        Socket socket = null;  
        OutputStreamWriter osw = null;  
        InputStream is = null;  
        try {  
            URL url = new URL(urlParam);  
            String host = url.getHost();  
            int port = url.getPort();  
            if (-1 == port) {  
                port = 80;  
            }  
            String path = url.getPath();  
            socket = new Socket(host, port);  
            StringBuffer sb = new StringBuffer();  
            sb.append("GET " + path + " HTTP/1.1\r\n");  
            sb.append("Host: " + host + "\r\n");  
            sb.append("Connection: Keep-Alive\r\n");  
            sb.append("Content-Type: application/x-www-form-urlencoded; charset=utf-8 \r\n");  
            sb.append("Content-Length: ").append(sb.toString().getBytes().length).append("\r\n");  
            // 这里一个回车换行，表示消息头写完，不然服务器会继续等待  
            sb.append("\r\n");  
            if (sbParams != null && sbParams.length() > 0) {  
                sb.append(sbParams.substring(0, sbParams.length() - 1));  
            }  
            osw = new OutputStreamWriter(socket.getOutputStream());  
            osw.write(sb.toString());  
            osw.flush();  
            is = socket.getInputStream();  
            String line = null;  
            // 服务器响应体数据长度  
            int contentLength = 0;  
            // 读取http响应头部信息  
            do {  
                line = readLine(is, 0, charset);  
                if (line.startsWith("Content-Length")) {  
                    // 拿到响应体内容长度  
                    contentLength = Integer.parseInt(line.split(":")[1].trim());  
                }  
                // 如果遇到了一个单独的回车换行，则表示请求头结束  
            } while (!line.equals("\r\n"));  
            // 读取出响应体数据（就是你要的数据）  
            result = readLine(is, contentLength, charset);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    is = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
        }  
        return result;  
    }  
  
    /** 
     * @Description:读取一行数据，contentLe内容长度为0时，读取响应头信息，不为0时读正文 
     * @time:2016年5月17日 下午6:11:07 
     */  
    private static String readLine(InputStream is, int contentLength, String charset) throws IOException {  
        List<Byte> lineByte = new ArrayList<Byte>();  
        byte tempByte;  
        int cumsum = 0;  
        if (contentLength != 0) {  
            do {  
                tempByte = (byte) is.read();  
                lineByte.add(Byte.valueOf(tempByte));  
                cumsum++;  
            } while (cumsum < contentLength);// cumsum等于contentLength表示已读完  
        } else {  
            do {  
                tempByte = (byte) is.read();  
                lineByte.add(Byte.valueOf(tempByte));  
            } while (tempByte != 10);// 换行符的ascii码值为10  
        }  
  
        byte[] resutlBytes = new byte[lineByte.size()];  
        for (int i = 0; i < lineByte.size(); i++) {  
            resutlBytes[i] = (lineByte.get(i)).byteValue();  
        }  
        return new String(resutlBytes, charset);  
    }  
/*     public static void main(String[] args) { 
    	String url_oa="http://127.0.0.1/zyy/login/login.do";
    	String userName = "1";
    	String password = "123456";
		Map<String, Object> params=new HashMap<>();
		params.put("username", userName);
		params.put("password", password);
		params.put("signature",Md5Util.MD5(userName));
		String charset="utf-8";
		try {
			 String str_reponse = httpClientPost(url_oa, params, charset);
			 ObjectMapper mapper=new ObjectMapper();
			 //获取返回字符串
			 str_reponse= mapper.readValue(str_reponse, Map.class).get("json_oa").toString();
			//获取响应码
			 Map map_root = mapper.readValue(str_reponse, Map.class);
	    	 map_root.get("errcode");
	    	//获取成功对象数据
	    	 Map map_obj= (Map) map_root.get("obj");
	    	 System.out.println(map_obj.get("accessToken"));
	    	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
    	
    }*/
    
 
}  