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

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.service.ClBorrowService;
import com.rongdu.ow.core.module.service.ClUserBaseInfoService;
@Controller
@Scope("prototype")
public class ClBorrowController extends BaseController{
      
	public static final Logger logger = LoggerFactory.getLogger(UserbaseInfoController.class);
	@Resource
	private ClUserBaseInfoService clUserBaseInfoService;
	@Resource
	private ClBorrowService clBorrowService;
	
	
	@RequestMapping(value = "/modules/user/borrow/save.htm", method = { RequestMethod.POST })
	public void save(@RequestParam(value="camount",required=true) String camount,
			@RequestParam(value="cborrowuse",required=true) String cborrowuse,
			@RequestParam(value="ctimelimit",required=true) String ctimelimit,
			@RequestParam(value="crealamount",required=true) String crealamount,
			@RequestParam(value="ccardId",required=true) String ccardId,
			@RequestParam(value="crepayamount",required=true) String crepayamount,HttpServletRequest request,
			HttpServletResponse response){
	    long userId = (long) request.getSession().getAttribute("userId");
	    Map<String,Object> result = new HashMap<String,Object>();
		ClBorrow clborrow = new ClBorrow(userId,camount,cborrowuse,ctimelimit,crealamount,ccardId);
		ClBorrow realBorrow = clBorrowService.saveApply(clborrow);
		if (realBorrow != null && realBorrow.getId() > 0) {
			request.getSession().setAttribute("isborrow", 1);
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请成功");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "申请失败");
		}
		
		ServletUtils.writeToResponse(response,result);
	}
	@RequestMapping(value = "/modules/user/borrow/index.htm")
	public void index(HttpServletRequest request,
			HttpServletResponse response){
	    Map<String,Object> data = clBorrowService.findIndex();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	@RequestMapping(value = "/modules/user/borrow/indexRepayAmount.htm")
	public void indexRepayAmount(@RequestParam(value="realamount",required=true) String realamount,
			@RequestParam(value="timelimit",required=true) String timelimit,HttpServletRequest request,
			HttpServletResponse response){
	    Map<String,Object> data = clBorrowService.indexRepayAmount(realamount,timelimit);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
