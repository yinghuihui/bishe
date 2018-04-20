package com.rongdu.ow.core.module.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.User;
import com.rongdu.ow.core.module.model.UserModel;

/**
 * 用户管理Dao
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2016-12-08 15:13:39
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserMapper extends BaseMapper<User,Long> {

	List<UserModel> listModel(Map<String, Object> params);

	UserModel getModel(Long id);

	List<Map<String, Object>> queryAllDic();

	/**
	 * 手机号查询id
	 * @param phone
	 * @return
	 */
	User findByLoginName(String phone);

	/**
	 * 修改等级
	 * @param user
	 * @return
	 */
	int updateLevel(User user);

	/**
	 * 查询用户等级
	 * @param map
	 * @return
	 */
	List<User> findUserLevel(Map<String, Object> map);

	/**
	 * 据uuid 修改用户信息
	 * 
	 * @param paramMap
	 * @return
	 */
	int updateByUuid(Map<String, Object> paramMap);
	
	/**
	 * 今日注册用户数
	 * @return
	 */
	long todayCount();

	/**
	 * 统计注册数量
	 * @return
	 */
	int countRegist();
	
	User findByWxOpenId(@Param("wxOpenId")String wxOpenId);
	
	/**
	 * 根据邀请码查询用户信息
	 * @param code
	 * @return
	 */
	User queryUserByInvitation(@Param("code")String code);
	
	/**
	 * 根据登录名（手机号）查询用户信息
	 * @param phone
	 * @return
	 */
	User queryUserByLoginName(@Param("loginName")String loginName);
	
	/**
	 * 手机号码是否已经存在
	 * @param phone
	 * @return
	 */
	int isPhoneExists(@Param("phone")String phone);
	
	
	
	
}
