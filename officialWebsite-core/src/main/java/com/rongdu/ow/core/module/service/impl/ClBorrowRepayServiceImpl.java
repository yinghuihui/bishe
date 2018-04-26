package com.rongdu.ow.core.module.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.ClBorrowRepay;
import com.rongdu.ow.core.module.mapper.ClBorrowRepayMapper;
import com.rongdu.ow.core.module.service.ClBorrowRepayService;


/**
 * 还款计划表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:47:49
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clBorrowRepayService")
public class ClBorrowRepayServiceImpl extends BaseServiceImpl<ClBorrowRepay, Long> implements ClBorrowRepayService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowRepayServiceImpl.class);
   
    @Resource
    private ClBorrowRepayMapper clBorrowRepayMapper;

	@Override
	public BaseMapper<ClBorrowRepay, Long> getMapper() {
		return clBorrowRepayMapper;
	}
	
}