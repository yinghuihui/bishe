package com.rongdu.ow.core.module.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.domain.ClBorrowRepay;


/**
 * 还款计划表Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:47:49
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClBorrowRepayService extends BaseService<ClBorrowRepay, Long>{
	/**
	 * 生成还款计划
	 * @param borrow
	 * @return
	 */
	public boolean genRepayPlan(ClBorrow borrow);
	/**
	 * 根据查询条件搜索
	 * @param paramMap
	 * @return
	 */
	List<ClBorrowRepay> listSelective(Map<String,Object> paramMap);
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
	 * 确认还款
	 * @param borrowRepay
	 * @param repayDate
	 * @param state
	 * @param amount
	 * @param orderNo
	 * @param repayWay
	 * @param cardNo
	 * @param penaltyAmout
	 * @return
	 */
	 Map<String, Object> confirmRepay(
			ClBorrowRepay borrowRepay,
			Date repayDate, 
			String state,
			Double amount,
			String orderNo,
			String repayWay,
			String cardNo,
			Double penaltyAmout);
	 /**
	  * 完成借款
	  * @param borrowRepay
	  * @param repayDate
	  * @param state
	  * @param amount
	  * @param repayWay
	  * @param orderNo
	  * @param cardNo
	  * @return
	  */
	 boolean finishBorrow(ClBorrowRepay borrowRepay, Date repayDate, String state, Double amount, String repayWay, String orderNo,String cardNo);
}
