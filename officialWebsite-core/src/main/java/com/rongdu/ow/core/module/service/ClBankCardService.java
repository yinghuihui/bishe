package com.rongdu.ow.core.module.service;


import java.util.Map;

import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.ClBankCard;

/**
 * 银行卡信息表Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:44:27
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClBankCardService extends BaseService<ClBankCard, Long>{
	/**
	 * 银行卡信息，持卡人姓名
	 * @param clBankCard
	 * @param cardUserName
	 */
	int bankCardSave(ClBankCard clBankCard,String cardUserName);
	/**
	 * 根据参数查询银行卡信息
	 * @param paramMap
	 * @return
	 */
	ClBankCard findSelective(Map<String,Object> paramMap);
}
