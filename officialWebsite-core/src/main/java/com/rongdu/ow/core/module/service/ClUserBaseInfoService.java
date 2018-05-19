package com.rongdu.ow.core.module.service;


import java.util.Map;

import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;
import com.rongdu.ow.core.module.model.ManagerUserModel;

/**
 * 用户详情表Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:47:00
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClUserBaseInfoService extends BaseService<ClUserBaseInfo, Long>{
	/**
	 * 根据userId更新基本信息
	 * @param baseIfo
	 */
    public int updateByUserId(ClUserBaseInfo baseIfo);
    /**
	 * 根据userId更新工作基本信息
	 * @param baseIfo
	 */
    int updateworkByUserId(ClUserBaseInfo baseIfo);
    /**
     * 根据userId查询 用户详情返回给后台manage使用
     * @param userId
     * @return
     */
    public ManagerUserModel getBaseModelByUserId(Long userId);
    
    /**
     * 根据userId查询 用户三项认证信息和状态
     * @param userId
     * @return
     */
    public Map<String,Object> getAuthForCredit(Long userId);
}
