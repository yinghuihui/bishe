package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款进度表实体
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:46:15
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class ClBorrowProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 关联用户id
    */
    private Long userId;

    /**
    * 借款信息id
    */
    private Long borrowId;

    /**
    * 借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
    */
    private String state;

    /**
    * 进度描述
    */
    private String remark;

    /**
    * 进度生成时间
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
    * 获取关联用户id
    *
    * @return 关联用户id
    */
    public Long getUserId(){
        return userId;
    }

    /**
    * 设置关联用户id
    * 
    * @param userId 要设置的关联用户id
    */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
    * 获取借款信息id
    *
    * @return 借款信息id
    */
    public Long getBorrowId(){
        return borrowId;
    }

    /**
    * 设置借款信息id
    * 
    * @param borrowId 要设置的借款信息id
    */
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }

    /**
    * 获取借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
    *
    * @return 借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
    */
    public String getState(){
        return state;
    }

    /**
    * 设置借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
    * 
    * @param state 要设置的借款进度状态 10申请成功待审核  20自动审核通过 21自动审核不通过  22自动审核未决待人工复审 26人工复审通过 27人工复审不通过    30放款成功  31放款失败   40还款成功    50逾期  90坏账
    */
    public void setState(String state){
        this.state = state;
    }

    /**
    * 获取进度描述
    *
    * @return 进度描述
    */
    public String getRemark(){
        return remark;
    }

    /**
    * 设置进度描述
    * 
    * @param remark 要设置的进度描述
    */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
    * 获取进度生成时间
    *
    * @return 进度生成时间
    */
    public Date getCreateTime(){
        return createTime;
    }

    /**
    * 设置进度生成时间
    * 
    * @param createTime 要设置的进度生成时间
    */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

}