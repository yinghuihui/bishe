package com.rongdu.ow.core.module.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.model.ManageBorrowModel;

/**
 * 借款信息表Dao
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:45:17
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ClBorrowMapper extends BaseMapper<ClBorrow, Long> {
	/**
	 * 查询待审核的所有订单
	 * @param paramMap
	 * @return
	 */
	List<ManageBorrowModel> listReview(Map<String,Object> paramMap);
	/**
	 * 查询待审核的所有订单listmodel
	 * @param paramMap
	 * @return
	 */
	List<ManageBorrowModel> listModel(Map<String,Object> paramMap);
	/**
	 * 修改状态(人工审核)
	 * @param paramMap
	 * @return
	 */
	
	int reviewState(Map<String,Object> paramMap);
	/**
	 * 修改状态到放款成功状态
	 * @param paramMap
	 * @return
	 */
	
	int updatePayState(Map<String,Object> paramMap);

}
