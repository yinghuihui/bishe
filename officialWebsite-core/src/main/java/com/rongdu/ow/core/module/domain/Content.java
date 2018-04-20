package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 栏目内容实体
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2017-08-09 13:35:48
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 栏目ID
    */
    private Integer programaId;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 创建时间
    */
    private Date addTime;

    /**
    * 作者
    */
    private String writer;

    /**
    * 文章来源
    */
    private String resource;

    /**
    * 点击量
    */
    private Integer count;

    /**
    * 是否置顶 10置顶，20不置顶
    */
    private Integer isStick;

    /**
    * SEO关键字
    */
    private String keyword;

    /**
    * 内容简介
    */
    private String description;

    /**
    * 状态 10显示，20隐藏
    */
    private Integer state;

    /**
    * 备注
    */
    private String remark;
    
    /**
     * 备注
     */
     private int sort;
    
    /**
     * 图片
     */
     private String thumbnail;


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
    * 获取栏目ID
    *
    * @return programaId
    */
    public Integer getProgramaId(){
        return programaId;
    }

    /**
    * 设置栏目ID
    * 
    * @param programaId 要设置的栏目ID
    */
    public void setProgramaId(Integer programaId){
        this.programaId = programaId;
    }

    /**
    * 获取标题
    *
    * @return title
    */
    public String getTitle(){
        return title;
    }

    /**
    * 设置标题
    * 
    * @param title 要设置的标题
    */
    public void setTitle(String title){
        this.title = title;
    }

    /**
    * 获取内容
    *
    * @return content
    */
    public String getContent(){
        return content;
    }

    /**
    * 设置内容
    * 
    * @param content 要设置的内容
    */
    public void setContent(String content){
        this.content = content;
    }

    /**
    * 获取创建时间
    *
    * @return addTime
    */
    public Date getAddTime(){
        return addTime;
    }

    /**
    * 设置创建时间
    * 
    * @param addTime 要设置的创建时间
    */
    public void setAddTime(Date addTime){
        this.addTime = addTime;
    }

    /**
    * 获取作者
    *
    * @return writer
    */
    public String getWriter(){
        return writer;
    }

    /**
    * 设置作者
    * 
    * @param writer 要设置的作者
    */
    public void setWriter(String writer){
        this.writer = writer;
    }

    /**
    * 获取文章来源
    *
    * @return resource
    */
    public String getResource(){
        return resource;
    }

    /**
    * 设置文章来源
    * 
    * @param resource 要设置的文章来源
    */
    public void setResource(String resource){
        this.resource = resource;
    }

    /**
    * 获取点击量
    *
    * @return count
    */
    public Integer getCount(){
        return count;
    }

    /**
    * 设置点击量
    * 
    * @param count 要设置的点击量
    */
    public void setCount(Integer count){
        this.count = count;
    }

    /**
    * 获取是否置顶 10置顶，20不置顶
    *
    * @return isStick
    */
    public Integer getIsStick(){
        return isStick;
    }

    /**
    * 设置是否置顶 10置顶，20不置顶
    * 
    * @param isStick 要设置的是否置顶 10置顶，20不置顶
    */
    public void setIsStick(Integer isStick){
        this.isStick = isStick;
    }

    /**
    * 获取SEO关键字
    *
    * @return keyword
    */
    public String getKeyword(){
        return keyword;
    }

    /**
    * 设置SEO关键字
    * 
    * @param keyword 要设置的SEO关键字
    */
    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    /**
    * 获取内容简介
    *
    * @return description
    */
    public String getDescription(){
        return description;
    }

    /**
    * 设置内容简介
    * 
    * @param description 要设置的内容简介
    */
    public void setDescription(String description){
        this.description = description;
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
	    * 获取缩略图
	    * 
	    * @param thumbnail 要获取的缩略图
	    */
	public String getThumbnail() {
		return thumbnail;
	}

	 /**
	    * 设置缩略图
	    * 
	    * @param thumbnail 要设置的缩略图
	    */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	    * 获取排序
	    * 
	    * @param sort 要获取的排序
	    */
	public int getSort() {
		return sort;
	}
	
	 /**
	    * 设置排序
	    * 
	    * @param sort 要设置的排序
	    */
	public void setSort(int sort) {
		this.sort = sort;
	}

}