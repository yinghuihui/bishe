package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户认证状态表实体
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-26 14:32:33
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class ClUserAuth implements Serializable {

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
    * 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    private String idState;

    /**
    * 身份认证时间
    */
    private Date idTime;

    /**
    * 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    private String bankCardState;

    /**
    * 银行卡认证时间
    */
    private Date bankCardTime;

    /**
    * 手机运营商认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    private String phoneState;

    /**
    * 手机运营商认证时间
    */
    private Date phoneTime;


    /**
    * 工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    private String workInfoState;

    /**
    * 工作信息认证时间
    */
    private Date workInfoTime;


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
    * 获取身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    *
    * @return 身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    public String getIdState(){
        return idState;
    }

    /**
    * 设置身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    * 
    * @param idState 要设置的身份认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    public void setIdState(String idState){
        this.idState = idState;
    }

    /**
    * 获取身份认证时间
    *
    * @return 身份认证时间
    */
    public Date getIdTime(){
        return idTime;
    }

    /**
    * 设置身份认证时间
    * 
    * @param idTime 要设置的身份认证时间
    */
    public void setIdTime(Date idTime){
        this.idTime = idTime;
    }

    /**
    * 获取银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    *
    * @return 银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    public String getBankCardState(){
        return bankCardState;
    }

    /**
    * 设置银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    * 
    * @param bankCardState 要设置的银行卡状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    public void setBankCardState(String bankCardState){
        this.bankCardState = bankCardState;
    }

    /**
    * 获取银行卡认证时间
    *
    * @return 银行卡认证时间
    */
    public Date getBankCardTime(){
        return bankCardTime;
    }

    /**
    * 设置银行卡认证时间
    * 
    * @param bankCardTime 要设置的银行卡认证时间
    */
    public void setBankCardTime(Date bankCardTime){
        this.bankCardTime = bankCardTime;
    }

    /**
    * 获取手机运营商认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    *
    * @return 手机运营商认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    public String getPhoneState(){
        return phoneState;
    }

    /**
    * 设置手机运营商认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    * 
    * @param phoneState 要设置的手机运营商认证状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    public void setPhoneState(String phoneState){
        this.phoneState = phoneState;
    }

    /**
    * 获取手机运营商认证时间
    *
    * @return 手机运营商认证时间
    */
    public Date getPhoneTime(){
        return phoneTime;
    }

    /**
    * 设置手机运营商认证时间
    * 
    * @param phoneTime 要设置的手机运营商认证时间
    */
    public void setPhoneTime(Date phoneTime){
        this.phoneTime = phoneTime;
    }

   

    
    /**
    * 获取工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    *
    * @return 更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    public String getWorkInfoState(){
        return workInfoState;
    }

    /**
    * 设置工作信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    * 
    * @param otherInfoState 要设置的更多信息状态 ，10未认证/未完善，20认证中/完善中，30已认证/已完善
    */
    public void setWorkInfoState(String workInfoState){
        this.workInfoState = workInfoState;
    }

    /**
    * 获取工作信息认证时间
    *
    * @return 其他信息认证时间
    */
    public Date getOtherInfoTime(){
        return workInfoTime;
    }

    /**
    * 设置工作信息认证时间
    * 
    * @param otherInfoTime 要设置的其他信息认证时间
    */
    public void setOtherInfoTime(Date workInfoTime){
        this.workInfoTime = workInfoTime;
    }

}