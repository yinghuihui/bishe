package com.rongdu.ow.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.ClBankCard;
import com.rongdu.ow.core.module.domain.ClUser;
import com.rongdu.ow.core.module.domain.ClUserAuth;
import com.rongdu.ow.core.module.model.ManagerUserModel;
import com.rongdu.ow.core.module.service.ClBankCardService;
import com.rongdu.ow.core.module.service.ClBorrowService;
import com.rongdu.ow.core.module.service.ClUserAuthService;
import com.rongdu.ow.core.module.service.ClUserBaseInfoService;
import com.rongdu.ow.core.module.service.ClUserService;

@Controller
@Scope("prototype")
public class ManageUserController extends ManageBaseController{
	private static final Logger logger = LoggerFactory.getLogger(ManageUserController.class);
	@Resource
	private ClUserService clUserService;
	@Resource
	private ClUserBaseInfoService clUserBaseInfoService;
	@Resource
	private ClBankCardService clBankCardService;
	@Resource
	private ClUserAuthService clUserAuthService;
	@Resource
	private ClBorrowService clBorrowService;
	/**
	 * 用户详细信息
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/cl/cluser/detail.htm",method={RequestMethod.GET,RequestMethod.POST})   
	public void detail(@RequestParam(value = "userId") Long userId){
		String serverHost = Global.getValue("server_host");
		HashMap<String, Object> map = new HashMap<String,Object>();
		ClUser user = clUserService.getById(userId);
		if (user != null && user.getId() != null) {
			//用户基本信息
			ManagerUserModel model = clUserBaseInfoService.getBaseModelByUserId(userId);
			//model.setLivingImg(model.getLivingImg()!=null?serverHost +"/readFile.htm?path="+ model.getLivingImg():"");
			model.setFrontImg(model.getFrontImg()!=null? model.getFrontImg():"");
			model.setBackImg(model.getBackImg()!=null? model.getBackImg():"");
			//model.setOcrImg(model.getOcrImg()!=null?serverHost +"/readFile.htm?path="+ model.getOcrImg():"");
			
//			if (StringUtil.isNotBlank(model.getWorkingImg())) {
//				String workImgStr = model.getWorkingImg();
//				List<String> workImgList = Arrays.asList(workImgStr.split(";"));
//				for (int i = 0; i < workImgList.size(); i++) {
//					String workImg = workImgList.get(i);
//					workImgList.set(i, serverHost +"/readFile.htm?path="+ workImg);
//				}
//				map.put("workImgArr", workImgList);
//			}
			
			//银行卡信息
			Map<String,Object> bankMap = new HashMap<>();
			bankMap.put("userId", user.getId());
			ClBankCard bankCard=clBankCardService.findSelective(bankMap);
			if (null != bankCard) {
				model.setBank(bankCard.getBank());
				model.setCardNo(bankCard.getCardNo());
				model.setBankPhone(bankCard.getPhone());
			}
			
			map.put("userbase", model);
			// 构造查询条件Map
			HashMap<String, Object> authMap = new HashMap<String,Object>();
			authMap.put("userId",user.getId());
			
			// 认证信息
			ClUserAuth authModel = clUserAuthService.findSelective(authMap);
			map.put("userAuth", authModel);
				
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, map);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}