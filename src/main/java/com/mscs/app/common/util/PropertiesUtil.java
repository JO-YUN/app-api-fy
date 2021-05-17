package com.mscs.app.common.util;

import java.io.IOException;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
public class PropertiesUtil {
 
	/*获取系统文件分隔符*/
//	public static String SEPARATOR = System.getProperty("file.separator");
	public static String SEPARATOR = "/";
	public static String getKey(String path,String key) throws IOException{
		Properties properties = new Properties();
		ClassPathResource resource = new ClassPathResource(path);
		properties.load(resource.getInputStream());
		return properties.getProperty(key);
	}
	public static Properties   getKey(String path ) throws IOException{
		Properties properties = new Properties();
		ClassPathResource resource = new ClassPathResource(path);
		properties.load(resource.getInputStream());
		return properties;
	}
}
