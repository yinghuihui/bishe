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

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.service.ClUserBaseInfoService;
@Controller
@Scope("prototype")
public class CreditController extends BaseController{
   
	public static final Logger logger = LoggerFactory.getLogger(CreditController.class);
	@Resource
	private ClUserBaseInfoService clUserBaseInfoService;
	@RequestMapping(value = "/modules/user/credit/creditdata.htm")
	public void index(HttpServletRequest request,
			HttpServletResponse response){
		long userId = (long) request.getSession().getAttribute("userId");
		Map<String,Object> data = clUserBaseInfoService.getAuthForCredit(userId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
