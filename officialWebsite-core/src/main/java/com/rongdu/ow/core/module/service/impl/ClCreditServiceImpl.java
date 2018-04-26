package com.rongdu.ow.core.module.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.ClCredit;
import com.rongdu.ow.core.module.mapper.ClCreditMapper;
import com.rongdu.ow.core.module.service.ClCreditService;


/**
 * 授信额度表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:43:39
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clCreditService")
public class ClCreditServiceImpl extends BaseServiceImpl<ClCredit, Long> implements ClCreditService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClCreditServiceImpl.class);
   
    @Resource
    private ClCreditMapper clCreditMapper;

	@Override
	public BaseMapper<ClCredit, Long> getMapper() {
		return clCreditMapper;
	}
	
}