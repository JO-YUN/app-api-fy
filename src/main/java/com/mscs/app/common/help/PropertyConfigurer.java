package com.mscs.app.common.help;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyConfigurer extends PropertyPlaceholderConfigurer {

	private static Map<String, String> ctxPropertiesMap;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		ctxPropertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
		new PropertiesPlaceholderResolver().parse(ctxPropertiesMap);
	}

	public static Object getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}

	public static String getPropertyString(String name) {
		Object obj = ctxPropertiesMap.get(name);
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public static String getPropertyString(String name, String def) {
		Object obj = ctxPropertiesMap.get(name);
		if (obj == null) {
			return def;
		}
		return obj.toString();
	}

	public static Integer getPropertyInt(String name) {
		Object obj = ctxPropertiesMap.get(name);
		if (obj == null) {
			return null;
		}
		return Integer.valueOf(obj.toString());
	}

	public static Integer getPropertyInt(String name, int def) {
		Object obj = ctxPropertiesMap.get(name);
		if (obj == null) {
			return def;
		}
		return Integer.valueOf(obj.toString());
	}
}
