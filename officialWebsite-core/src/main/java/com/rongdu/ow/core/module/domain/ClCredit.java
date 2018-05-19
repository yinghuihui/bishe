package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 授信额度表实体
 * 
 */
 public class ClCredit implements Serializable {

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
    * 总额度
    */
    private Double total;

    /**
    * 已使用额度
    */
    private Double used;

    /**
    * 可使用额度
    */
    private Double unuse;

    /**
    * 状态 10 -正常 20-冻结
    */
    private String state;

    /**
    * 更新时间
    */
    private Date updateTime;


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
    * 获取总额度
    *
    * @return 总额度
    */
    public Double getTotal(){
        return total;
    }

    /**
    * 设置总额度
    * 
    * @param total 要设置的总额度
    */
    public void setTotal(Double total){
        this.total = total;
    }

    /**
    * 获取已使用额度
    *
    * @return 已使用额度
    */
    public Double getUsed(){
        return used;
    }

    /**
    * 设置已使用额度
    * 
    * @param used 要设置的已使用额度
    */
    public void setUsed(Double used){
        this.used = used;
    }

    /**
    * 获取可使用额度
    *
    * @return 可使用额度
    */
    public Double getUnuse(){
        return unuse;
    }

    /**
    * 设置可使用额度
    * 
    * @param unuse 要设置的可使用额度
    */
    public void setUnuse(Double unuse){
        this.unuse = unuse;
    }

    /**
    * 获取状态 10 -正常 20-冻结
    *
    * @return 状态 10 -正常 20-冻结
    */
    public String getState(){
        return state;
    }

    /**
    * 设置状态 10 -正常 20-冻结
    * 
    * @param state 要设置的状态 10 -正常 20-冻结
    */
    public void setState(String state){
        this.state = state;
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

}