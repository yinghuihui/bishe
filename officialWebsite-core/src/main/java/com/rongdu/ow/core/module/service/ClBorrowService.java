package com.rongdu.ow.core.module.service;


import java.util.Map;

import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.ClBorrow;

/**
 * 借款信息表Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:45:17
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClBorrowService extends BaseService<ClBorrow, Long>{
	/**
	 * 保存借款信息
	 * @param borrow
	 * @return
	 */
   ClBorrow saveApply(ClBorrow borrow);
   /**
    * 计算利息保存借款信息
    * @param borrow
    * @return
    */
   
   ClBorrow saveBorrow(ClBorrow borrow);
   /**
    * 保存借款进度
    * @param borrow
    * @param state
    * @param remark
    */
   
   public void savePressState(ClBorrow borrow, String state,String remark);
   /**
    * 更新额度
    * @param userId
    * @param amount
    * @param type
    * @return
    */
   
   public int modifyCredit(Long userId, double amount, String type);
   /**
    * 返回首页信息
    * @param userId
    * @return
    */
   Map<String,Object> findIndex();
   /**
    * 返回还款金额
    * @param amount
    * @param timeLimit
    * @return
    */
   Map<String,Object> indexRepayAmount(String amount,String timeLimit);
}
