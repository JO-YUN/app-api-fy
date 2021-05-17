package com.mscs.app.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
* @title: OathConfig 
* @description：TODO
* @author： 刘威巍
* @date： 2018年10月24日 下午3:33:07
 */
public class OathConfig {

    private static Properties props = new Properties();

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("oauth.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setProps(Properties prop) {
        props = prop;
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }
}
