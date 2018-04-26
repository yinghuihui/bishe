package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户详情表实体
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:47:00
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class ClUserBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 客户表 外键
    */
    private Long userId;

    /**
    * 手机号码
    */
    private String phone;

    /**
    * 真实姓名
    */
    private String realName;

    /**
    * 年龄 
    */
    private Integer age;

    /**
    * 性别
    */
    private String sex;

    /**
    * 证件号码
    */
    private String idNo;

    /**
    * 身份证地址
    */
    private String idAddr;

    /**
    * 自拍(人脸识别照片)
    */
    private String livingImg;

    /**
    * 身份证头像
    */
    private String ocrImg;

    /**
    * 身份证正面
    */
    private String frontImg;

    /**
    * 身份证反面
    */
    private String backImg;

    /**
    * 学历
    */
    private String education;

    /**
    * 公司名称
    */
    private String companyName;

    /**
    * 公司电话
    */
    private String companyPhone;

    /**
    * 公司地址
    */
    private String companyAddr;

    /**
    * 公司详细地址
    */
    private String companyDetailAddr;

    /**
    * 居住地址
    */
    private String liveAddr;

    /**
    * 更新时间
    */
    private Date updateTime;

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
    * 获取客户表 外键
    *
    * @return 客户表 外键
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置客户表 外键
    * 
    * @param userId 要设置的客户表 外键
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取手机号码
    *
    * @return 手机号码
    */
    public String getPhone(){
        return phone;
    }

    /**
    * 设置手机号码
    * 
    * @param phone 要设置的手机号码
    */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
    * 获取真实姓名
    *
    * @return 真实姓名
    */
    public String getRealName(){
        return realName;
    }

    /**
    * 设置真实姓名
    * 
    * @param realName 要设置的真实姓名
    */
    public void setRealName(String realName){
        this.realName = realName;
    }

    /**
    * 获取年龄 
    *
    * @return 年龄 
    */
    public Integer getAge(){
        return age;
    }

    /**
    * 设置年龄 
    * 
    * @param age 要设置的年龄 
    */
    public void setAge(Integer age){
        this.age = age;
    }

    /**
    * 获取性别
    *
    * @return 性别
    */
    public String getSex(){
        return sex;
    }

    /**
    * 设置性别
    * 
    * @param sex 要设置的性别
    */
    public void setSex(String sex){
        this.sex = sex;
    }

    /**
    * 获取证件号码
    *
    * @return 证件号码
    */
    public String getIdNo(){
        return idNo;
    }

    /**
    * 设置证件号码
    * 
    * @param idNo 要设置的证件号码
    */
    public void setIdNo(String idNo){
        this.idNo = idNo;
    }

    /**
    * 获取身份证地址
    *
    * @return 身份证地址
    */
    public String getIdAddr(){
        return idAddr;
    }

    /**
    * 设置身份证地址
    * 
    * @param idAddr 要设置的身份证地址
    */
    public void setIdAddr(String idAddr){
        this.idAddr = idAddr;
    }

    /**
    * 获取自拍(人脸识别照片)
    *
    * @return 自拍(人脸识别照片)
    */
    public String getLivingImg(){
        return livingImg;
    }

    /**
    * 设置自拍(人脸识别照片)
    * 
    * @param livingImg 要设置的自拍(人脸识别照片)
    */
    public void setLivingImg(String livingImg){
        this.livingImg = livingImg;
    }

    /**
    * 获取身份证头像
    *
    * @return 身份证头像
    */
    public String getOcrImg(){
        return ocrImg;
    }

    /**
    * 设置身份证头像
    * 
    * @param ocrImg 要设置的身份证头像
    */
    public void setOcrImg(String ocrImg){
        this.ocrImg = ocrImg;
    }

    /**
    * 获取身份证正面
    *
    * @return 身份证正面
    */
    public String getFrontImg(){
        return frontImg;
    }

    /**
    * 设置身份证正面
    * 
    * @param frontImg 要设置的身份证正面
    */
    public void setFrontImg(String frontImg){
        this.frontImg = frontImg;
    }

    /**
    * 获取身份证反面
    *
    * @return 身份证反面
    */
    public String getBackImg(){
        return backImg;
    }

    /**
    * 设置身份证反面
    * 
    * @param backImg 要设置的身份证反面
    */
    public void setBackImg(String backImg){
        this.backImg = backImg;
    }

    /**
    * 获取学历
    *
    * @return 学历
    */
    public String getEducation(){
        return education;
    }

    /**
    * 设置学历
    * 
    * @param education 要设置的学历
    */
    public void setEducation(String education){
        this.education = education;
    }

    /**
    * 获取公司名称
    *
    * @return 公司名称
    */
    public String getCompanyName(){
        return companyName;
    }

    /**
    * 设置公司名称
    * 
    * @param companyName 要设置的公司名称
    */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
    * 获取公司电话
    *
    * @return 公司电话
    */
    public String getCompanyPhone(){
        return companyPhone;
    }

    /**
    * 设置公司电话
    * 
    * @param companyPhone 要设置的公司电话
    */
    public void setCompanyPhone(String companyPhone){
        this.companyPhone = companyPhone;
    }

    /**
    * 获取公司地址
    *
    * @return 公司地址
    */
    public String getCompanyAddr(){
        return companyAddr;
    }

    /**
    * 设置公司地址
    * 
    * @param companyAddr 要设置的公司地址
    */
    public void setCompanyAddr(String companyAddr){
        this.companyAddr = companyAddr;
    }

    /**
    * 获取公司详细地址
    *
    * @return 公司详细地址
    */
    public String getCompanyDetailAddr(){
        return companyDetailAddr;
    }

    /**
    * 设置公司详细地址
    * 
    * @param companyDetailAddr 要设置的公司详细地址
    */
    public void setCompanyDetailAddr(String companyDetailAddr){
        this.companyDetailAddr = companyDetailAddr;
    }

    /**
    * 获取居住地址
    *
    * @return 居住地址
    */
    public String getLiveAddr(){
        return liveAddr;
    }

    /**
    * 设置居住地址
    * 
    * @param liveAddr 要设置的居住地址
    */
    public void setLiveAddr(String liveAddr){
        this.liveAddr = liveAddr;
    }

    /**
    * 获取更新时间
    *
    * @return 更新时间
    */
    public Date getUpdateTime(){
        return updateTime;
    }

    /**
    * 设置更新时间
    * 
    * @param updateTime 要设置的更新时间
    */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
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