package com.rongdu.ow.core.module.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.ClBorrowRepay;

/**
 * 还款计划表Dao
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:47:49
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ClBorrowRepayMapper extends BaseMapper<ClBorrowRepay, Long> {
    /**
     * 更新 逾期部分
     * @param clBorrowRepay
     * @return
     */
	int updateLate(ClBorrowRepay clBorrowRepay);
	/**
	 * 查询未还款的还款计划
	 * @param paramMap
	 * @return
	 */
	
	List<ClBorrowRepay> findUnRepay(Map<String,Object> paramMap);
	/**
	 * 更新  逾期天数和逾期金额
	 * @param paramMap
	 * @return
	 */
	int updateParam(Map<String,Object> paramMap);

}
