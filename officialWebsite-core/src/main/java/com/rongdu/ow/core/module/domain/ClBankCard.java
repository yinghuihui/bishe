package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行卡信息表实体
 * 
 */
 public class ClBankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 开户行
    */
    private String bank;

    /**
    * 银行卡号
    */
    private String cardNo;

    /**
    * 预留手机号
    */
    private String phone;

    /**
    * 绑卡时间
    */
    private Date bindTime;


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
    * 获取用户标识
    *
    * @return 用户标识
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置用户标识
    * 
    * @param userId 要设置的用户标识
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取开户行
    *
    * @return 开户行
    */
    public String getBank(){
        return bank;
    }

    /**
    * 设置开户行
    * 
    * @param bank 要设置的开户行
    */
    public void setBank(String bank){
        this.bank = bank;
    }

    /**
    * 获取银行卡号
    *
    * @return 银行卡号
    */
    public String getCardNo(){
        return cardNo;
    }

    /**
    * 设置银行卡号
    * 
    * @param cardNo 要设置的银行卡号
    */
    public void setCardNo(String cardNo){
        this.cardNo = cardNo;
    }

    /**
    * 获取预留手机号
    *
    * @return 预留手机号
    */
    public String getPhone(){
        return phone;
    }

    /**
    * 设置预留手机号
    * 
    * @param phone 要设置的预留手机号
    */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
    * 获取绑卡时间
    *
    * @return 绑卡时间
    */
    public Date getBindTime(){
        return bindTime;
    }

    /**
    * 设置绑卡时间
    * 
    * @param bindTime 要设置的绑卡时间
    */
    public void setBindTime(Date bindTime){
        this.bindTime = bindTime;
    }

}