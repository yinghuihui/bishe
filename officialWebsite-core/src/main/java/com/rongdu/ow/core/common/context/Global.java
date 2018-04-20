package com.rongdu.ow.core.common.context;

import java.util.Map;

import tool.util.NumberUtil;
import tool.util.StringUtil;

/**
 * 启动加载缓存类
 * 
 * @author gc
 * @version 1.0.0
 * @date 2016年11月11日 下午3:37:13
 * Copyright 杭州融都科技股份有限公司  All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class Global {

	/**	系统参数 */
	public static Map<String, Object> configMap;

	/**	短信模板 */
	public static Map<String, Object> msgTemplateMap;

	/** 额度配置 */
	public static Map<String, Object> creditConfigMap;
	
	/** 初审客服订单 */
	public static Map<String, Integer> trialOrderMap;

	/** 复审客服订单 */
	public static Map<String, Integer> reviewOrderMap;
	
	

	public static String getValue(String key) {
		return StringUtil.isNull(configMap.get(key));
	}
	
	public static int getInt(String key){
		return NumberUtil.getInt(StringUtil.isNull(configMap.get(key)));
	}

	public static Long getLong(String key){
		return NumberUtil.getLong(StringUtil.isNull(getValue(key)));
	}
	
	public static double getDouble(String key){
		return NumberUtil.getDouble(StringUtil.isNull(configMap.get(key)));
	}

	public static Object getObject(String key){
		return configMap.get(key);
	}

	public static String getMsgTempLate(String key) {
		return StringUtil.isNull(msgTemplateMap.get(key));
	}
	

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getCreditConfig(String key) {
		Map<String, Object> maps = creditConfigMap;
		for (Map.Entry<String, Object> entry : maps.entrySet()) {
			if(entry.getKey().contains(key)){
				return (Map<String, Object>) entry.getValue();
			}
		}
		return null;
	}
}
