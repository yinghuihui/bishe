package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 还款计划表实体
 * 
 */
 public class ClBorrowRepay implements Serializable {

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
    * 借款订单id
    */
    private Long borrowId;

    /**
    * 还款金额
    */
    private Double amount;

    /**
    * 应还款时间
    */
    private Date repayTime;

    /**
    * 状态 10已还款 20未还款
    */
    private String state;

    /**
    * 逾期罚金
    */
    private Double penaltyAmout;

    /**
    * 逾期天数
    */
    private Integer penaltyDay;

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
    * 获取还款金额
    *
    * @return 还款金额
    */
    public Double getAmount(){
        return amount;
    }

    /**
    * 设置还款金额
    * 
    * @param amount 要设置的还款金额
    */
    public void setAmount(Double amount){
        this.amount = amount;
    }

    /**
    * 获取应还款时间
    *
    * @return 应还款时间
    */
    public Date getRepayTime(){
        return repayTime;
    }

    /**
    * 设置应还款时间
    * 
    * @param repayTime 要设置的应还款时间
    */
    public void setRepayTime(Date repayTime){
        this.repayTime = repayTime;
    }

    /**
    * 获取状态 10已还款 20未还款
    *
    * @return 状态 10已还款 20未还款
    */
    public String getState(){
        return state;
    }

    /**
    * 设置状态 10已还款 20未还款
    * 
    * @param state 要设置的状态 10已还款 20未还款
    */
    public void setState(String state){
        this.state = state;
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