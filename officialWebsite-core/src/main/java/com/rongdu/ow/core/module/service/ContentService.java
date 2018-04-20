package com.rongdu.ow.core.module.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.Content;
import com.rongdu.ow.core.module.model.ContentModel;

/**
 * 栏目内容Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-08-09 10:54:15
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ContentService extends BaseService<Content, Long>{
	/**
	 * 获取内容
	 * 
	 * @param id
	 * @throws Exception
	 */
	public Page<ContentModel> list(Map<String, Object> params, int currentPage, int pageSize);
	

	public int updateSelective(Map<String, Object> paramMap);
	
	/**
	 * 根据栏目id获取内容列表(新闻类，根据时间排序)
	 * 
	 * @param programaId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	public Page<Content> listByProgramaId(Map<String, Object> params, int currentPage, int pageSize);
	
	/**
	 * 获取logo
	 * @throws Exception
	 */
	public Content getLogo(Map<String, Object> params);
	
	/**
	 * 根据栏目id获取内容（首页内容，根据sort排序）
	 * 
	 * @param programaId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	public Page<Content> sortByProgramaId(Map<String, Object> params, int currentPage, int pageSize);
}
