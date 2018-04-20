package com.rongdu.ow.core.module.mapper;

import org.apache.ibatis.annotations.Param;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.AppSession;


/**
 * app sessinon记录Dao
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-05-12 15:46:15
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface AppSessionMapper extends BaseMapper<AppSession,Long> {

    /**
     * 删除
     * @param id
     * @return
     */
	int delete(Long id);
	
	/**
	 * 根据token删除
	 * @param token
	 * @return
	 */
	int deleteByToken(@Param("token")String token);
	
	/**
	 * 查询一条用户记录
	 * @param userId
	 * @return
	 */
	AppSession selectOneByUserId(@Param("userId")Long userId);
}
