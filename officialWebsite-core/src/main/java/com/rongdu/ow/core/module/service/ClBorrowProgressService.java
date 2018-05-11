package com.rongdu.ow.core.module.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.ClBorrowProgress;
import com.rongdu.ow.core.module.model.ManageBorrowProgressModel;

/**
 * 借款进度表Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:46:15
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClBorrowProgressService extends BaseService<ClBorrowProgress, Long>{
	/**
	 * 查询是否有逾期和还账的进度
	 * @param borrowId
	 * @return
	 */
	public int isNormalBorrowProgress(Long borrowId);
	
	/**
	 * 后台还款进度列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowProgressModel> listModel(Map<String, Object> params,
			int currentPage, int pageSize);
}
