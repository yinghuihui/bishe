package com.rongdu.ow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;
import com.rongdu.ow.core.module.service.ClBankCardService;
import com.rongdu.ow.core.module.service.ClUserBaseInfoService;
@Controller
@Scope("prototype")
public class WorkInfoController extends BaseController{
	public static final Logger logger = LoggerFactory.getLogger(WorkInfoController.class);
	@Resource
	private ClUserBaseInfoService clUserBaseInfoService;
	
	@RequestMapping(value = "/modules/user/workinfo/save.htm", method = { RequestMethod.POST })
	public void save(@RequestParam(value="companyName",required=true) String companyName,
			@RequestParam(value="companyPhone",required=true) String companyPhone,
			@RequestParam(value="companyPlace",required=true) String companyPlace,HttpServletRequest request,
			HttpServletResponse response){
	Map<String, Object> resultMap = new HashMap<String, Object>();
	ClUserBaseInfo baseInfo = new ClUserBaseInfo();
	long userId = (Long) request.getSession().getAttribute("userId");
	baseInfo.setUserId(userId);
	baseInfo.setCompanyAddr(companyPlace);
	baseInfo.setCompanyPhone(companyPhone);
	baseInfo.setCompanyName(companyName);
	int result = clUserBaseInfoService.updateworkByUserId(baseInfo);
	if (result > 0) {
		resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
	} else {
		resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		resultMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
	}
	ServletUtils.writeToResponse(response, resultMap);
	
}

}
