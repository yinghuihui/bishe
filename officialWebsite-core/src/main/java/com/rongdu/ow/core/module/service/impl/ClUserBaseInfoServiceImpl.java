package com.rongdu.ow.core.module.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.UserAuth;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.ClBankCard;
import com.rongdu.ow.core.module.domain.ClUserAuth;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;
import com.rongdu.ow.core.module.mapper.ClBankCardMapper;
import com.rongdu.ow.core.module.mapper.ClUserAuthMapper;
import com.rongdu.ow.core.module.mapper.ClUserBaseInfoMapper;
import com.rongdu.ow.core.module.model.ManagerUserModel;
import com.rongdu.ow.core.module.model.UserAuthModel;
import com.rongdu.ow.core.module.service.ClBankCardService;
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
    @Resource
    private ClBankCardMapper clBankCardMapper;
    

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
	@Override
	public ManagerUserModel getBaseModelByUserId(Long userId){
		return clUserBaseInfoMapper.getBaseModelByUserId(userId);
	}
	@Override
	/**
     * 根据userId查询 用户三项认证信息和状态
     * @param userId
     * @return
     */
    public Map<String,Object> getAuthForCredit(Long userId){
		ClUserAuth  clUserAuth = clUserAuthMapper.findByUserId(userId);
		Map<String,Object> map = new HashMap<String,Object>();
		ClUserBaseInfo userBaseInfo = clUserBaseInfoMapper.findByUserId(userId);
		Map<String,Object> personInfoMap = new HashMap<String,Object>();
		personInfoMap.put("realName", userBaseInfo.getRealName());
		personInfoMap.put("idNo", userBaseInfo.getIdNo());
		personInfoMap.put("frontImg", userBaseInfo.getFrontImg());
		personInfoMap.put("backImg", userBaseInfo.getBackImg());
		personInfoMap.put("education", userBaseInfo.getEducation());
		personInfoMap.put("liveAddr", userBaseInfo.getLiveAddr());
		map.put("personInfoMap", "");
		if(clUserAuth.getIdState().equals(UserAuthModel.IS_AUTHED)){
			map.put("personInfoMap", personInfoMap);
		}
		
		Map<String,Object> workInfoMap = new HashMap<String,Object>();
		workInfoMap.put("companyName", userBaseInfo.getCompanyName());
		workInfoMap.put("companyPhone", userBaseInfo.getCompanyPhone());
		workInfoMap.put("companyAddr", userBaseInfo.getCompanyAddr());
		map.put("workInfoMap", "");
		if(clUserAuth.getWorkInfoState().equals(UserAuthModel.IS_AUTHED)){
			map.put("workInfoMap", workInfoMap);
		}

		Map<String,Object> bankCardMap = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		ClBankCard clBankCard = clBankCardMapper.findSelective(paramMap);
		bankCardMap.put("bank", clBankCard.getBank());
		bankCardMap.put("cardNo", clBankCard.getCardNo());
		bankCardMap.put("phone", clBankCard.getPhone());
		bankCardMap.put("brealName", userBaseInfo.getRealName());
		map.put("bankCardMap", "");
		if(clUserAuth.getBankCardState().equals(UserAuthModel.IS_AUTHED)){
			map.put("bankCardMap", bankCardMap);
		}
		Map<String,Object> authMap = new HashMap<String,Object>();
		authMap.put("idState", UserAuthModel.authStateStr(clUserAuth.getIdState()));
		authMap.put("bankCardState", UserAuthModel.authStateStr(clUserAuth.getBankCardState()));
		authMap.put("workInfoState", UserAuthModel.authStateStr(clUserAuth.getWorkInfoState()));
		map.put("authMap",authMap);
		return map;
		
	}
	
}