package com.rongdu.ow.core.module.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.domain.ClBorrowProgress;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;
import com.rongdu.ow.core.module.mapper.ClBorrowMapper;
import com.rongdu.ow.core.module.mapper.ClBorrowProgressMapper;
import com.rongdu.ow.core.module.mapper.ClUserBaseInfoMapper;
import com.rongdu.ow.core.module.model.ManageBorrowModel;
import com.rongdu.ow.core.module.model.ManageBorrowProgressModel;
import com.rongdu.ow.core.module.service.ClBorrowProgressService;


/**
 * 借款进度表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:46:15
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clBorrowProgressService")
public class ClBorrowProgressServiceImpl extends BaseServiceImpl<ClBorrowProgress, Long> implements ClBorrowProgressService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowProgressServiceImpl.class);
   
    @Resource
    private ClBorrowProgressMapper clBorrowProgressMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private ClUserBaseInfoMapper clUserBaseInfoMapper;

	@Override
	public BaseMapper<ClBorrowProgress, Long> getMapper() {
		return clBorrowProgressMapper;
	}
	@Override
	public int isNormalBorrowProgress(Long borrowId){
		return clBorrowProgressMapper.isNormalBorrowProgress(borrowId);
	}
	@Override
	public  Page<ManageBorrowProgressModel> listModel(Map<String, Object> params, int currentPage,
			int pageSize) {
		Map<String, Object> bparams=new HashMap<String, Object>();
		if(StringUtil.isNotBlank(params)){
			if(params.containsKey("realName")){
				bparams.put("realName", params.get("realName"));
			}
			if(params.containsKey("phone")){
				bparams.put("phone", params.get("phone"));
			}
			if(params.containsKey("orderNo")){
				bparams.put("orderNo", params.get("orderNo"));
			}
			List<ManageBorrowModel> borrowList = clBorrowMapper.listModel(bparams);
			
			if(StringUtil.isNotBlank(params)&&StringUtil.isNotBlank(bparams)&&StringUtil.isNotBlank(borrowList)){
				bparams=new HashMap<String, Object>();
				List borrowIds=new ArrayList();
				if(borrowList.size()>0){
					for(ManageBorrowModel b:borrowList){
						borrowIds.add(b.getId());
					}
				}else{
					borrowIds.add("0");
				}
				if(StringUtil.isNotBlank(borrowIds)){
				    params.put("borrowIds", borrowIds);
				}
			}
		}
		
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowProgressModel> list = clBorrowProgressMapper.listModel(params);
		if (StringUtil.isNotBlank(list)) {
			for (int i = 0; i < list.size(); i++) {
				ManageBorrowProgressModel model = list.get(i);
				ClBorrow b = clBorrowMapper.findByPrimary(model.getBorrowId());
				list.get(i).setAmount(b.getAmount());
				list.get(i).setOrderNo(b.getOrderNo());
				ClUserBaseInfo info = clUserBaseInfoMapper.findByUserId(model.getUserId());
				if (StringUtil.isNotBlank(info)) {
					list.get(i).setPhone(info.getPhone());
					list.get(i).setRealName(info.getRealName());
				}
			}
		}
		
		return (Page<ManageBorrowProgressModel>)list;
	}
	
}