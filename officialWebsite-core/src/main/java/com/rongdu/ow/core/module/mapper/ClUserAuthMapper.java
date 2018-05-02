package com.rongdu.ow.core.module.mapper;


import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.ClUserAuth;

/**
 * 用户认证状态表Dao
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-26 14:32:33
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ClUserAuthMapper extends BaseMapper<ClUserAuth, Long> {
    /**
     * 根据userId查询认证信息
     * @param userId
     * @return
     */
	ClUserAuth findByUserId(long userId);
	/**
	 * 根据userId更新认证信息
	 * @param paramMap
	 * @return
	 */
	int updateStateByUserId(Map<String,Object> paramMap);
	
	/**
	 * 查询认证状态是否全部已认证
	 * @param paramMap
	 * @return
	 */
	Map<String,Object> getRequiredAuthState(Map<String,Object> paramMap); 

}
