package com.rongdu.ow.core.module.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.ClBankCard;
import com.rongdu.ow.core.module.mapper.ClBankCardMapper;
import com.rongdu.ow.core.module.service.ClBankCardService;


/**
 * 银行卡信息表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:44:27
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clBankCardService")
public class ClBankCardServiceImpl extends BaseServiceImpl<ClBankCard, Long> implements ClBankCardService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClBankCardServiceImpl.class);
   
    @Resource
    private ClBankCardMapper clBankCardMapper;

	@Override
	public BaseMapper<ClBankCard, Long> getMapper() {
		return clBankCardMapper;
	}
	
}