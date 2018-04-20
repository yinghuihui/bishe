package com.rongdu.ow.core.module.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.Programa;
import com.rongdu.ow.core.module.mapper.ProgramaMapper;
import com.rongdu.ow.core.module.service.ProgramaService;


/**
 * 栏目ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-08-09 10:53:48
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("programaService")
public class ProgramaServiceImpl extends BaseServiceImpl<Programa, Long> implements ProgramaService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProgramaServiceImpl.class);
   
    @Resource
    private ProgramaMapper programaMapper;

	@Override
	public BaseMapper<Programa, Long> getMapper() {
		return programaMapper;
	}

	@Override
	public Page<Programa> list(int currentPage, int pageSize, Map<String, Object> mapdata) {
		PageHelper.startPage(currentPage, pageSize);
		return (Page<Programa>) programaMapper.listSelective(mapdata);
	}
	@Override
	public boolean updateSelective(Map<String, Object> paramMap){
		int result=programaMapper.updateSelective(paramMap);
		return result==1;		
	}
	public List<Map<String, Object>> list(Map<String,Object> searchParams){
		List<Map<String, Object>> list=programaMapper.pageSelective(searchParams);
		return list;
	}

	@Override
	public List<Programa> listByParentId(Map<String,Object> searchParams) {
		return programaMapper.listByParentId(searchParams);
	}
	
}