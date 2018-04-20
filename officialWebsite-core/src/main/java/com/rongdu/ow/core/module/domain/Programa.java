package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 栏目实体
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-08-09 10:53:48
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 唯一标识
    */
    private String programaKeyword;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 栏目名称
    */
    private String programaName;

    /**
    * 父级ID
    */
    private Integer parentId;

    /**
    * 链接类型
    */
    private String hrefType;

    /**
    * 链接地址
    */
    private String href;

    /**
    * 备注
    */
    private String remark;

    /**
    * 添加时间
    */
    private Date addTime;

    /**
    * 修改时间
    */
    private Date updateTime;

    /**
    * 状态 10显示，20隐藏
    */
    private Integer state;


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
    * 获取唯一标识
    *
    * @return programaKeyword
    */
    public String getProgramaKeyword(){
        return programaKeyword;
    }

    /**
    * 设置唯一标识
    * 
    * @param programaKeyword 要设置的唯一标识
    */
    public void setProgramaKeyword(String programaKeyword){
        this.programaKeyword = programaKeyword;
    }

    /**
    * 获取排序
    *
    * @return sort
    */
    public Integer getSort(){
        return sort;
    }

    /**
    * 设置排序
    * 
    * @param sort 要设置的排序
    */
    public void setSort(Integer sort){
        this.sort = sort;
    }

    /**
    * 获取栏目名称
    *
    * @return programaName
    */
    public String getProgramaName(){
        return programaName;
    }

    /**
    * 设置栏目名称
    * 
    * @param programaName 要设置的栏目名称
    */
    public void setProgramaName(String programaName){
        this.programaName = programaName;
    }

    /**
    * 获取父级ID
    *
    * @return parentId
    */
    public Integer getParentId(){
        return parentId;
    }

    /**
    * 设置父级ID
    * 
    * @param parentId 要设置的父级ID
    */
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    /**
    * 获取链接类型
    *
    * @return hrefType
    */
    public String getHrefType(){
        return hrefType;
    }

    /**
    * 设置链接类型
    * 
    * @param hrefType 要设置的链接类型
    */
    public void setHrefType(String hrefType){
        this.hrefType = hrefType;
    }

    /**
    * 获取链接地址
    *
    * @return href
    */
    public String getHref(){
        return href;
    }

    /**
    * 设置链接地址
    * 
    * @param href 要设置的链接地址
    */
    public void setHref(String href){
        this.href = href;
    }

    /**
    * 获取备注
    *
    * @return remark
    */
    public String getRemark(){
        return remark;
    }

    /**
    * 设置备注
    * 
    * @param remark 要设置的备注
    */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取添加时间
    *
    * @return addTime
    */
    public Date getAddTime(){
        return addTime;
    }

    /**
    * 设置添加时间
    * 
    * @param addTime 要设置的添加时间
    */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }

    /**
    * 获取修改时间
    *
    * @return updateTime
    */
    public Date getUpdateTime(){
        return updateTime;
    }

    /**
    * 设置修改时间
    * 
    * @param updateTime 要设置的修改时间
    */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
    * 获取状态 10显示，20隐藏
    *
    * @return state
    */
    public Integer getState(){
        return state;
    }

    /**
    * 设置状态 10显示，20隐藏
    * 
    * @param state 要设置的状态 10显示，20隐藏
    */
    public void setState(Integer state){
        this.state = state;
    }

}