package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表实体
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:41:23
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class ClUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 登录名
    */
    private String loginName;

    /**
    * 登录密码
    */
    private String loginPwd;

    /**
    * 上次登录密码修改时间
    */
    private Date loginpwdModifyTime;

    /**
    * 注册时间
    */
    private Date registTime;

    /**
    * 登录时间
    */
    private Date loginTime;


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
    * 获取登录名
    *
    * @return 登录名
    */
    public String getLoginName(){
        return loginName;
    }

    /**
    * 设置登录名
    * 
    * @param loginName 要设置的登录名
    */
    public void setLoginName(String loginName){
        this.loginName = loginName;
    }

    /**
    * 获取登录密码
    *
    * @return 登录密码
    */
    public String getLoginPwd(){
        return loginPwd;
    }

    /**
    * 设置登录密码
    * 
    * @param loginPwd 要设置的登录密码
    */
    public void setLoginPwd(String loginPwd){
        this.loginPwd = loginPwd;
    }

    /**
    * 获取上次登录密码修改时间
    *
    * @return 上次登录密码修改时间
    */
    public Date getLoginpwdModifyTime(){
        return loginpwdModifyTime;
    }

    /**
    * 设置上次登录密码修改时间
    * 
    * @param loginpwdModifyTime 要设置的上次登录密码修改时间
    */
    public void setLoginpwdModifyTime(Date loginpwdModifyTime){
        this.loginpwdModifyTime = loginpwdModifyTime;
    }

    /**
    * 获取注册时间
    *
    * @return 注册时间
    */
    public Date getRegistTime(){
        return registTime;
    }

    /**
    * 设置注册时间
    * 
    * @param registTime 要设置的注册时间
    */
    public void setRegistTime(Date registTime){
        this.registTime = registTime;
    }

    /**
    * 获取登录时间
    *
    * @return 登录时间
    */
    public Date getLoginTime(){
        return loginTime;
    }

    /**
    * 设置登录时间
    * 
    * @param loginTime 要设置的登录时间
    */
    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }

}