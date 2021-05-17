package com.mscs.app.common.util;


import java.util.Properties;

import org.springframework.context.ApplicationContext;

public class ApplicationConstants {

	private static final String APPLICATION_CONSTANTS = "aplicationConstants";
	private static Properties constantsProperties;

	public static void init(ApplicationContext appContext) {
		constantsProperties = (Properties) appContext.getBean(APPLICATION_CONSTANTS);
	}

	public static String get(String key) {
		String result = constantsProperties.getProperty(key);
		if (result != null) {
			return result;
		} else {
			throw new RuntimeException("Constant " + key + " is not specified.");
		}
	}

}
