package com.rongdu.ow.core.module.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.ClBorrowProgress;
import com.rongdu.ow.core.module.model.BorrowProgressModel;
import com.rongdu.ow.core.module.model.ManageBorrowProgressModel;

/**
 * 借款进度表Dao
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:46:15
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ClBorrowProgressMapper extends BaseMapper<ClBorrowProgress, Long> {
   /**
    * 查询是否有坏账和逾期的进度
    * @param borrowId
    * @return
    */
	int isNormalBorrowProgress(Long borrowId);
	
	/**
	 * 根据条件查询第一条
	 * @param params
	 * @return
	 */
	ClBorrowProgress findFirst(Map<String, Object> params);
	
	/**
	 * 后台借款进度列表
	 * 
	 * @param params
	 * @return
	 */
	List<ManageBorrowProgressModel> listModel(Map<String, Object> params);
	
	/**
	 * 借款进度查询
	 * 
	 * @param bpMap
	 * @return
	 */
	List<BorrowProgressModel> listProgress(Map<String, Object> bpMap);

}
