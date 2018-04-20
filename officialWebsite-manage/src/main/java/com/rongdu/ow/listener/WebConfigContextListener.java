package com.rongdu.ow.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;
import org.quartz.Job;

import com.rongdu.ow.core.common.util.CacheUtil;
import com.rongdu.ow.core.module.domain.QuartzInfo;
import com.rongdu.ow.core.module.model.QuartzInfoModel;
import com.rongdu.ow.core.module.model.QuartzManager;
import com.rongdu.ow.core.module.service.QuartzInfoService;

import tool.util.BeanUtil;


/**
 * 监听器
 * @author gc
 * @version 1.0.0
 * @date 2016年11月11日 下午3:40:52
 * Copyright 杭州融都科技股份有限公司  All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */

public class WebConfigContextListener implements ServletContextListener,HttpSessionAttributeListener{

	private static Logger logger=Logger.getLogger(WebConfigContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("启动加载...");

		// 系统参数
		CacheUtil.initSysConfig();

		try {
			QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
		
			// 查询启用状态的定时任务信息
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("state", QuartzInfoModel.STATE_ENABLE);
			List<QuartzInfo> list = quartzInfoService.list(paramMap);
			QuartzManager quartzManager = new QuartzManager();
			// 循环添加任务
			for (QuartzInfo quartzInfo : list) {
				String clName = quartzInfo.getClassName();
				Job cl = (Job) Class.forName(clName).newInstance();
				quartzManager.addJob(quartzInfo.getCode(),QuartzManager.JOB_GROUP_NAME, cl.getClass(),quartzInfo.getCycle());
			}
			
			// 启动所有定时任务			
			quartzManager.start();

		} catch (Exception e) {
			logger.error("启动定时任务异常--->" + e.getMessage(), e);
		}
	}


	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}
}
