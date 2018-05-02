package com.rongdu.ow.core.module.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.ClUserAuth;
import com.rongdu.ow.core.module.mapper.ClUserAuthMapper;
import com.rongdu.ow.core.module.service.ClUserAuthService;




/**
 * 用户认证状态表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-26 14:32:33
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clUserAuthService")
public class ClUserAuthServiceImpl extends BaseServiceImpl<ClUserAuth, Long> implements ClUserAuthService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClUserAuthServiceImpl.class);
   
    @Resource
    private ClUserAuthMapper clUserAuthMapper;

	@Override
	public BaseMapper<ClUserAuth, Long> getMapper() {
		return clUserAuthMapper;
	}
	@Override
	public int updateStateByUserId(Map<String,Object> paramMap){
		return clUserAuthMapper.updateStateByUserId(paramMap);
	}
	
}