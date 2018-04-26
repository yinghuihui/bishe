package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 还款记录表实体
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:48:30
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class ClBorrowRepayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 还款计划id
    */
    private Long repayId;

    /**
    * 借款订单id
    */
    private Long borrowId;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 实际还款金额
    */
    private Double amount;

    /**
    * 逾期天数
    */
    private Integer penaltyDay;

    /**
    * 逾期罚金
    */
    private Double penaltyAmout;

    /**
    * 还款方式   10代扣，20银行卡转账，30支付宝转账
    */
    private String repayWay;

    /**
    * 还款账号
    */
    private String repayAccount;

    /**
    * 还款流水号
    */
    private String serialNumber;

    /**
    * 实际还款时间
    */
    private Date repayTime;

    /**
    * 创建时间
    */
    private Date createTime;


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
    * 获取还款计划id
    *
    * @return 还款计划id
    */
    public Long getRepayId(){
        return repayId;
    }

    /**
    * 设置还款计划id
    * 
    * @param repayId 要设置的还款计划id
    */
    public void setRepayId(Long repayId){
        this.repayId = repayId;
    }

    /**
    * 获取借款订单id
    *
    * @return 借款订单id
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款订单id
    * 
    * @param borrowId 要设置的借款订单id
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
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
    * 获取实际还款金额
    *
    * @return 实际还款金额
    */
    public Double getAmount(){
        return amount;
    }

    /**
    * 设置实际还款金额
    * 
    * @param amount 要设置的实际还款金额
    */
    public void setAmount(Double amount){
        this.amount = amount;
    }

    /**
    * 获取逾期天数
    *
    * @return 逾期天数
    */
    public Integer getPenaltyDay(){
        return penaltyDay;
    }

    /**
    * 设置逾期天数
    * 
    * @param penaltyDay 要设置的逾期天数
    */
    public void setPenaltyDay(Integer penaltyDay){
        this.penaltyDay = penaltyDay;
    }

    /**
    * 获取逾期罚金
    *
    * @return 逾期罚金
    */
    public Double getPenaltyAmout(){
        return penaltyAmout;
    }

    /**
    * 设置逾期罚金
    * 
    * @param penaltyAmout 要设置的逾期罚金
    */
    public void setPenaltyAmout(Double penaltyAmout){
        this.penaltyAmout = penaltyAmout;
    }

    /**
    * 获取还款方式   10代扣，20银行卡转账，30支付宝转账
    *
    * @return 还款方式   10代扣，20银行卡转账，30支付宝转账
    */
    public String getRepayWay(){
        return repayWay;
    }

    /**
    * 设置还款方式   10代扣，20银行卡转账，30支付宝转账
    * 
    * @param repayWay 要设置的还款方式   10代扣，20银行卡转账，30支付宝转账
    */
    public void setRepayWay(String repayWay){
        this.repayWay = repayWay;
    }

    /**
    * 获取还款账号
    *
    * @return 还款账号
    */
    public String getRepayAccount(){
        return repayAccount;
    }

    /**
    * 设置还款账号
    * 
    * @param repayAccount 要设置的还款账号
    */
    public void setRepayAccount(String repayAccount){
        this.repayAccount = repayAccount;
    }

    /**
    * 获取还款流水号
    *
    * @return 还款流水号
    */
    public String getSerialNumber(){
        return serialNumber;
    }

    /**
    * 设置还款流水号
    * 
    * @param serialNumber 要设置的还款流水号
    */
    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
    * 获取实际还款时间
    *
    * @return 实际还款时间
    */
    public Date getRepayTime(){
        return repayTime;
    }

    /**
    * 设置实际还款时间
    * 
    * @param repayTime 要设置的实际还款时间
    */
    public void setRepayTime(Date repayTime){
        this.repayTime = repayTime;
    }

    /**
    * 获取创建时间
    *
    * @return 创建时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置创建时间
    * 
    * @param createTime 要设置的创建时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

}