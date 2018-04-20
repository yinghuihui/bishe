package com.rongdu.ow.core.module.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.Programa;

/**
 * 栏目Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-08-09 10:53:48
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ProgramaService extends BaseService<Programa, Long>{

	/**
	 * 分页查询出的列表
	 * 
	 * @param mapdata
	 * @return
	 */
    Page<Programa> list(int currentPage, int pageSize, Map<String, Object> mapdata);
    /**
	 * 根据id更新
	 * 
	 * @param paramMap
	 * @return
	 */
    boolean updateSelective(Map<String, Object> paramMap);
    
    /**
	 * 查询所有菜单
	 * @return
	 */
	List<Map<String, Object>> list(Map<String,Object> searchParams);
	
    /**
	 * 根据父栏目查询子栏目
	 * @return
	 */
	List<Programa> listByParentId(Map<String,Object> searchParams);

}
