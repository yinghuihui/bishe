package com.rongdu.ow.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.domain.QuartzLog;
import com.rongdu.ow.model.QuartzLogModel;

/**
 * 定时任务记录Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-03-15 13:38:29
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface QuartzLogService extends BaseService<QuartzLog, Long>{

	/**
	 * 保存日志
	 */
	int save(QuartzLog ql);

	Page<QuartzLogModel> page(Map<String, Object> searchMap, int current,
			int pageSize);

}
