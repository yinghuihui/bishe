package com.rongdu.ow.core.module.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.mapper.ClBorrowMapper;
import com.rongdu.ow.core.module.service.ClBorrowService;


/**
 * 借款信息表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:45:17
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clBorrowService")
public class ClBorrowServiceImpl extends BaseServiceImpl<ClBorrow, Long> implements ClBorrowService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowServiceImpl.class);
   
    @Resource
    private ClBorrowMapper clBorrowMapper;

	@Override
	public BaseMapper<ClBorrow, Long> getMapper() {
		return clBorrowMapper;
	}
	
}