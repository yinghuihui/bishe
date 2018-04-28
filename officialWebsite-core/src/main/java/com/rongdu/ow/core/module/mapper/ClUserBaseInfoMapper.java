package com.rongdu.ow.core.module.mapper;


import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;

/**
 * 用户详情表Dao
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:47:00
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ClUserBaseInfoMapper extends BaseMapper<ClUserBaseInfo, Long> {
    /**
     * 根据userId查询用户详细信息
     * @param userId
     * @return
     */
	ClUserBaseInfo findByUserId(long userId);
	/**
	 * 根据userId更新基本信息
	 * @param clUserBaseInfo
	 * @return
	 */
	int updateByUserId(ClUserBaseInfo clUserBaseInfo);

}
