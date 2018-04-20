package com.rongdu.ow.core.module.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.Content;
import com.rongdu.ow.core.module.mapper.ContentMapper;
import com.rongdu.ow.core.module.model.ContentModel;
import com.rongdu.ow.core.module.service.ContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * 栏目内容ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-08-09 10:54:15
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@SuppressWarnings("unused")
@Service("contentService")
public class ContentServiceImpl extends BaseServiceImpl<Content, Long> implements ContentService {
	
    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
   
    @Resource
    private ContentMapper contentMapper;

	@Override
	public BaseMapper<Content, Long> getMapper() {
		return contentMapper;
	}

	@Override
	public Page<ContentModel> list(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ContentModel> list =  contentMapper.list(params);
		return (Page<ContentModel>) list;
	}

	@Override
	public int updateSelective(Map<String, Object> paramMap) {
		int result = contentMapper.updateSelective(paramMap);
		return result;
	}

	@Override
	public Page<Content> listByProgramaId(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Content> list =  contentMapper.listByProgramaId(params);
		return (Page<Content>) list;
	}
	@Override
	public Content getLogo(Map<String, Object> params) {
		return contentMapper.findSelective(params);
	}

	@Override
	public Page<Content> sortByProgramaId(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Content> list =  contentMapper.sortByProgramaId(params);
		return (Page<Content>) list;
	}

	
}