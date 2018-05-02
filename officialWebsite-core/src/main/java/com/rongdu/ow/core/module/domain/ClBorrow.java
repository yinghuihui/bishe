package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款信息表实体
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:45:17
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class ClBorrow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 订单号
    */
    private String orderNo;

    /**
    * 借款金额
    */
    private Double amount;

    /**
    * 实际到账金额
    */
    private Double realAmount;

    /**
    * 订单生成时间
    */
    private Date createTime;

    /**
    * 借款期限(天)
    */
    private String timeLimit;

    /**
    * 订单状态 10-审核中 20-自动审核成功  21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期
    */
    private String state;

    /**
    * 收款银行卡关联id
    */
    private Long cardId;

    /**
    * 借款利息
    */
    private Double interest;

    /**
    * 备注、审核说明
    */
    private String remark;

    /**
    * 借款用途 10 个人日常类型 20 装修 30 旅游 40 教育 50 医疗
    */
    private String borrowUse;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取用户id
    *
    * @return 用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户id
    * 
    * @param userId 要设置的用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取订单号
    *
    * @return 订单号
    */
    public String getOrderNo(){
        return orderNo;
    }

    /**
    * 设置订单号
    * 
    * @param orderNo 要设置的订单号
    */
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    /**
    * 获取借款金额
    *
    * @return 借款金额
    */
    public Double getAmount(){
        return amount;
    }

    /**
    * 设置借款金额
    * 
    * @param amount 要设置的借款金额
    */
    public void setAmount(Double amount){
        this.amount = amount;
    }

    /**
    * 获取实际到账金额
    *
    * @return 实际到账金额
    */
    public Double getRealAmount(){
        return realAmount;
    }

    /**
    * 设置实际到账金额
    * 
    * @param realAmount 要设置的实际到账金额
    */
    public void setRealAmount(Double realAmount){
        this.realAmount = realAmount;
    }

    /**
    * 获取订单生成时间
    *
    * @return 订单生成时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置订单生成时间
    * 
    * @param createTime 要设置的订单生成时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
    * 获取借款期限(天)
    *
    * @return 借款期限(天)
    */
    public String getTimeLimit(){
        return timeLimit;
    }

    /**
    * 设置借款期限(天)
    * 
    * @param timeLimit 要设置的借款期限(天)
    */
    public void setTimeLimit(String timeLimit){
        this.timeLimit = timeLimit;
    }

    /**
    * 获取订单状态 10-审核中 20-自动审核成功  21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期
    *
    * @return 订单状态 10-审核中 20-自动审核成功  21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期
    */
    public String getState(){
        return state;
    }

    /**
    * 设置订单状态 10-审核中 20-自动审核成功  21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期
    * 
    * @param state 要设置的订单状态 10-审核中 20-自动审核成功  21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过 30-待还款 40-已还款 41减免还款 50已逾期
    */
    public void setState(String state){
        this.state = state;
    }

    /**
    * 获取收款银行卡关联id
    *
    * @return 收款银行卡关联id
    */
    public Long getCardId(){
        return cardId;
    }

    /**
    * 设置收款银行卡关联id
    * 
    * @param cardId 要设置的收款银行卡关联id
    */
    public void setCardId(Long cardId){
        this.cardId = cardId;
    }

    /**
    * 获取借款利息
    *
    * @return 借款利息
    */
    public Double getInterest(){
        return interest;
    }

    /**
    * 设置借款利息
    * 
    * @param interest 要设置的借款利息
    */
    public void setInterest(Double interest){
        this.interest = interest;
    }

    /**
    * 获取备注、审核说明
    *
    * @return 备注、审核说明
    */
    public String getRemark(){
        return remark;
    }

    /**
    * 设置备注、审核说明
    * 
    * @param remark 要设置的备注、审核说明
    */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取借款用途 10 个人日常类型 20 装修 30 旅游 40 教育 50 医疗
    *
    * @return 借款用途 10 个人日常类型 20 装修 30 旅游 40 教育 50 医疗
    */
    public String getBorrowUse(){
        return borrowUse;
    }

    /**
    * 设置借款用途 10 个人日常类型 20 装修 30 旅游 40 教育 50 医疗
    * 
    * @param borrowUse 要设置的借款用途 10 个人日常类型 20 装修 30 旅游 40 教育 50 医疗
    */
    public void setBorrowUse(String borrowUse){
        this.borrowUse = borrowUse;
    }
    public ClBorrow(Long userId,String amount,String borrowUse,String timeLimit,String realAmount,String cardId){
    	this.userId = userId;
    	this.amount = Double.parseDouble(amount);
    	this.borrowUse = borrowUse;
    	this.timeLimit = timeLimit;
    	this.realAmount = Double.parseDouble(realAmount);
    	this.cardId = Long.parseLong(cardId);
    }

}