package com.rongdu.ow.core.module.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.Content;
import com.rongdu.ow.core.module.model.ContentModel;

/**
 * 栏目内容Dao
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-08-09 13:35:48
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ContentMapper extends BaseMapper<Content, Long> {
	
	List<Content> listByProgramaId(Map<String, Object> paramMap);
	
	List<ContentModel> list(Map<String, Object> paramMap);

	List<Content> sortByProgramaId(Map<String, Object> paramMap);
}
