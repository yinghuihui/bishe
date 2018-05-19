package com.rongdu.ow.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

import com.rongdu.ow.core.common.util.CacheUtil;


/**
 * 监听器

 */

public class WebConfigContextListener implements ServletContextListener,HttpSessionAttributeListener{

	private static Logger logger=Logger.getLogger(WebConfigContextListener.class);
	
	
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	
	public void contextInitialized(ServletContextEvent event) {
		logger.info("启动加载...");
		// 系统参数
		CacheUtil.initSysConfig();
	}


	
	public void attributeAdded(HttpSessionBindingEvent event) {
		
	}

	
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
	}

	
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}
}
