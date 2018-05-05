package com.rongdu.ow.controller;

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

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.JsonUtil;
import com.rongdu.ow.core.common.util.RdPage;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.model.ClBorrowModel;
import com.rongdu.ow.core.module.model.ManageBorrowModel;
import com.rongdu.ow.core.module.service.ClBorrowService;

@Controller
@Scope("prototype")
public class ManageBorrowController extends ManageBaseController{
	private static final Logger logger = LoggerFactory.getLogger(ManageBorrowController.class);
	@Resource
	private ClBorrowService clBorrowService;
	/**
	 * 人工复审查询
	 * @param searchParams
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/reviewList.htm",method={RequestMethod.GET,RequestMethod.POST})
	public void reviewList(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List stateList;
		
		Page<ManageBorrowModel> page = null;
		if (params != null) {
			String state = StringUtil.isNull(params.get("state"));
			if(state.equals(ClBorrowModel.STATE_PASS)){
				page = clBorrowService.listReview(params, currentPage, pageSize);
			}else{
				page = clBorrowService.listModel(params, currentPage, pageSize);
			}
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 后台人工复审功能
	 * @param borrowId
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/borrow/verifyBorrow.htm")
	public void verifyBorrow(@RequestParam(value = "borrowId") Long borrowId,
			@RequestParam(value = "state") String state,
			@RequestParam(value = "remark") String remark) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>(); 
		try{
		    int msg =clBorrowService.manualVerifyBorrow(borrowId, state, remark);
			if(msg==1){
				result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "审核成功");
			}else{
				result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				result.put(Constant.RESPONSE_CODE_MSG, "审核失败");
			}
		} catch (Exception e) {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		}
		ServletUtils.writeToResponse(response,result);
	}
}
