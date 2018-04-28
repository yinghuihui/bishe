package com.rongdu.ow.core.module.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rongdu.ow.core.common.service.BaseService;
import com.rongdu.ow.core.module.domain.ClUser;

/**
 * 用户表Service
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:41:23
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface ClUserService extends BaseService<ClUser, Long>{
	/**
	 * 注册
	 * @param request
	 * @param phone
	 * @param pwd
	 * @param vcode
	 * @return
	 */
	 Map pcRegisterUser(HttpServletRequest request, String phone, String pwd, String vcode);
	 /**
	  * 登陆
	  * @param request
	  * @param loginName
	  * @param pwd
	  * @return
	  */
	 Map pcUserLogin(HttpServletRequest request, String loginName, String pwd);
}
