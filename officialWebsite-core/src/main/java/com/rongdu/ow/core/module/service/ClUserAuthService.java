package com.rongdu.ow.core.module.service;


import java.util.Map;

import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.ClUserAuth;

/**
 * 用户认证状态表Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-26 14:32:33
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClUserAuthService extends BaseService<ClUserAuth, Long>{
	/**
	 * 根据userId修改认证状态
	 * @param paramMap
	 * @return
	 */
	int updateStateByUserId(Map<String,Object> paramMap);
	/**
	 * 根据参数查询userAuth信息
	 * @param paramMap
	 * @return
	 */
	ClUserAuth findSelective(Map<String,Object> paramMap);

}
