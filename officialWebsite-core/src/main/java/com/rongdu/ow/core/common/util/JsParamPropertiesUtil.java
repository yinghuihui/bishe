package com.rongdu.ow.core.common.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsParamPropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(JsParamPropertiesUtil.class);

	/** 资源属性 */
	private static Properties properties;

	/**
	 * 私有构造方法
	 */
	private JsParamPropertiesUtil() {
	}

	static {
		properties = new Properties();
		try {
			// 读取配置文件
			properties.load(JsParamPropertiesUtil.class.getClassLoader().getResourceAsStream("jsParamConfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取配置文件出错，请确认properties文件已经放在目录下。");
		}
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key 键
	 * @return 配置信息
	 */
	public static String getValue(String key) {
		String value = properties.getProperty(key);
		if (StringUtil.isBlank(value)) {
			logger.info("没有获取指定key的值，请确认资源文件中是否存在【" + key + "】");
		}
		return value;
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key 键
	 * @param param 参数
	 * @return 配置信息
	 */
	public static String getValue(String key, Object[] param) {
		String value = getValue(key);
		return MessageFormat.format(value, param);
	}

}
