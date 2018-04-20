package com.rongdu.ow.core.module.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.Programa;

/**
 * 栏目Dao
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-08-09 10:53:48
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ProgramaMapper extends BaseMapper<Programa, Long> {
	
	List<Map<String, Object>> pageSelective(Map<String, Object> paramMap);
    
	List<Programa> listByParentId(Map<String, Object> paramMap);
}
