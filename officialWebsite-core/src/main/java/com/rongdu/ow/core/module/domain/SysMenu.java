package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 菜单表
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 菜单名称
    */
    private String name;

    /**
    * 父级ID
    */
    private Integer parentId;

    /**
    * 请求路径
    */
    private String url;

    /**
    * 图标
    */
    private String iconCls;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 添加时间
    */
    private Date addTime;

    /**
    * 添加者
    */
    private String addUser;

    /**
    * 修改时间
    */
    private Date updateTime;

    /**
    * 修改者
    */
    private String updateUser;

    /**
    * 备注
    */
    private String remark;

    /**
    * 是否删除：0不删除，1删除
    */
    private Integer isDelete;

    /**
    * 10:菜单 20:权限
    */
    private String type;

    /**
    * 脚本名称
    */
    private String scriptid;


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
    * 获取菜单名称
    *
    * @return 菜单名称
    */
    public String getName(){
        return name;
    }

    /**
    * 设置菜单名称
    * 
    * @param name 要设置的菜单名称
    */
    public void setName(String name){
        this.name = name;
    }

    /**
    * 获取父级ID
    *
    * @return 父级ID
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
    * 获取请求路径
    *
    * @return 请求路径
    */
    public String getUrl(){
        return url;
    }

    /**
    * 设置请求路径
    * 
    * @param url 要设置的请求路径
    */
    public void setUrl(String url){
        this.url = url;
    }

    /**
    * 获取图标
    *
    * @return 图标
    */
    public String getIconCls(){
        return iconCls;
    }

    /**
    * 设置图标
    * 
    * @param iconCls 要设置的图标
    */
    public void setIconCls(String iconCls){
        this.iconCls = iconCls;
    }

    /**
    * 获取排序
    *
    * @return 排序
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
    * 获取添加时间
    *
    * @return 添加时间
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
    * 获取添加者
    *
    * @return 添加者
    */
    public String getAddUser(){
        return addUser;
    }

    /**
    * 设置添加者
    * 
    * @param addUser 要设置的添加者
    */
    public void setAddUser(String addUser){
        this.addUser = addUser;
    }

    /**
    * 获取修改时间
    *
    * @return 修改时间
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
    * 获取修改者
    *
    * @return 修改者
    */
    public String getUpdateUser(){
        return updateUser;
    }

    /**
    * 设置修改者
    * 
    * @param updateUser 要设置的修改者
    */
    public void setUpdateUser(String updateUser){
        this.updateUser = updateUser;
    }

    /**
    * 获取备注
    *
    * @return 备注
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
    * 获取是否删除：0不删除，1删除
    *
    * @return 是否删除：0不删除，1删除
    */
    public Integer getIsDelete(){
        return isDelete;
    }

    /**
    * 设置是否删除：0不删除，1删除
    * 
    * @param isDelete 要设置的是否删除：0不删除，1删除
    */
    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }

    /**
    * 获取10:菜单 20:权限
    *
    * @return 10:菜单 20:权限
    */
    public String getType(){
        return type;
    }

    /**
    * 设置10:菜单 20:权限
    * 
    * @param type 要设置的10:菜单 20:权限
    */
    public void setType(String type){
        this.type = type;
    }

    /**
    * 获取脚本名称
    *
    * @return 脚本名称
    */
    public String getScriptid(){
        return scriptid;
    }

    /**
    * 设置脚本名称
    * 
    * @param scriptid 要设置的脚本名称
    */
    public void setScriptid(String scriptid){
        this.scriptid = scriptid;
    }

}
