package com.rongdu.ow.core.module.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.ClBorrowProgress;
import com.rongdu.ow.core.module.mapper.ClBorrowProgressMapper;
import com.rongdu.ow.core.module.service.ClBorrowProgressService;


/**
 * 借款进度表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:46:15
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clBorrowProgressService")
public class ClBorrowProgressServiceImpl extends BaseServiceImpl<ClBorrowProgress, Long> implements ClBorrowProgressService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowProgressServiceImpl.class);
   
    @Resource
    private ClBorrowProgressMapper clBorrowProgressMapper;

	@Override
	public BaseMapper<ClBorrowProgress, Long> getMapper() {
		return clBorrowProgressMapper;
	}
	@Override
	public int isNormalBorrowProgress(Long borrowId){
		return clBorrowProgressMapper.isNormalBorrowProgress(borrowId);
	}
	
}