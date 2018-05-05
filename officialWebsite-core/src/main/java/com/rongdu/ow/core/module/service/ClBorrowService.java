package com.rongdu.ow.core.module.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.model.ManageBorrowModel;

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
   /**
    * 返回待审核信息(状态是人工审核通过的)
    * @param params
    * @param currentPage
    * @param pageSize
    * @return
    */
   Page<ManageBorrowModel> listReview(Map<String,Object> params,int currentPage, int pageSize);
   /**
    * 返回待审核信息
    * @param params
    * @param currentPage
    * @param pageSize
    * @return
    */
   Page<ManageBorrowModel> listModel(Map<String,Object> params,int currentPage, int pageSize);
   /**
    * 人工复审
    * @param borrowId
    * @param state
    * @param remark
    * @return
    */
   public int manualVerifyBorrow(Long borrowId, String state, String remark);
   /**
    * 根据主键查找借款信息
    * @param borrowId
    * @return
    */
   ClBorrow findByPrimary(Long borrowId);
   /**
    * 更新 根据多条件
    * @param params
    * @return
    */
   int updateSelective(Map<String,Object> params);

}
