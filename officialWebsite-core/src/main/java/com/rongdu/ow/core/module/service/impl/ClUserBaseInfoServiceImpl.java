package com.rongdu.ow.core.module.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.UserAuth;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.ClUserAuth;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;
import com.rongdu.ow.core.module.mapper.ClUserAuthMapper;
import com.rongdu.ow.core.module.mapper.ClUserBaseInfoMapper;
import com.rongdu.ow.core.module.model.ManagerUserModel;
import com.rongdu.ow.core.module.model.UserAuthModel;
import com.rongdu.ow.core.module.service.ClUserBaseInfoService;


/**
 * 用户详情表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:47:00
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clUserBaseInfoService")
public class ClUserBaseInfoServiceImpl extends BaseServiceImpl<ClUserBaseInfo, Long> implements ClUserBaseInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClUserBaseInfoServiceImpl.class);
   
    @Resource
    private ClUserBaseInfoMapper clUserBaseInfoMapper;
    @Resource
    private ClUserAuthMapper clUserAuthMapper;
    

	@Override
	public BaseMapper<ClUserBaseInfo, Long> getMapper() {
		return clUserBaseInfoMapper;
	}
	@Override
	public int updateByUserId(ClUserBaseInfo clUserBaseInfo){
		int code = 0;
		try {
			Integer age = StringUtil.getAge(clUserBaseInfo.getIdNo());
			clUserBaseInfo.setAge(age);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sex = StringUtil.getSex(clUserBaseInfo.getIdNo());
		clUserBaseInfo.setSex(sex);
		int result = clUserBaseInfoMapper.updateByUserId(clUserBaseInfo);
		if(result >0){
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("userId", clUserBaseInfo.getUserId());
			paramMap.put("idState", UserAuthModel.IS_AUTHED);
			paramMap.put("idTime",new Date());
			code = clUserAuthMapper.updateStateByUserId(paramMap);
		}
		return code;
	}
	@Override
	public int updateworkByUserId(ClUserBaseInfo clUserBaseInfo){
		int code = 0;
		int i = clUserBaseInfoMapper.updateByUserId(clUserBaseInfo);
		if(i >0){
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("userId", clUserBaseInfo.getUserId());
			paramMap.put("workInfoState", UserAuthModel.IS_AUTHED);
			paramMap.put("workInfoTime",new Date());
			code = clUserAuthMapper.updateStateByUserId(paramMap);
		}
		return code;
	}
	public ManagerUserModel getBaseModelByUserId(Long userId){
		return clUserBaseInfoMapper.getBaseModelByUserId(userId);
	}
	
}