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

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.JsonUtil;
import com.rongdu.ow.core.common.util.RdPage;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.model.ClBorrowModel;
import com.rongdu.ow.core.module.model.ManageBorrowModel;
import com.rongdu.ow.core.module.model.ManageBorrowProgressModel;
import com.rongdu.ow.core.module.service.ClBorrowProgressService;
import com.rongdu.ow.core.module.service.ClBorrowService;

@Controller
@Scope("prototype")
public class ManageBorrowController extends ManageBaseController{
	private static final Logger logger = LoggerFactory.getLogger(ManageBorrowController.class);
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private ClBorrowProgressService clBorrowProgressService;
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
	/**
	 * 借款还款信息列表     
	 * @param searchParams
	 * @param current
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/borrowRepayList.htm",method={RequestMethod.GET,RequestMethod.POST})
	public void borrowRepayList(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List stateList;
		if (params != null) {
			//放款列表
			String type= StringUtil.isNull(params.get("type"));
			if("repay".equals(type)){
				stateList = Arrays.asList(
						ClBorrowModel.STATE_PASS,
						ClBorrowModel.STATE_REPAY_FAIL,
						ClBorrowModel.STATE_REPAY,
						ClBorrowModel.STATE_FINISH,
						ClBorrowModel.STATE_DELAY, 
						ClBorrowModel.STATE_BAD);
			    params.put("stateList", stateList);
				String state = StringUtil.isNull(params.get("state"));
				if (null != state &&!StringUtil.isBlank(state)) {
					params.put("state", state);
				}
			}
			String state = StringUtil.isNull(params.get("state"));
			if (null != state &&!StringUtil.isBlank(state)) {
				//还款列表
				if(state.equals(ClBorrowModel.STATE_FINISH)){//40
					stateList = Arrays.asList(
							ClBorrowModel.STATE_FINISH);
					params.put("stateList", stateList);
					params.put("state", "");
				}
				//逾期中列表  
				if(state.equals(ClBorrowModel.STATE_DELAY)){//50
					stateList = Arrays.asList(ClBorrowModel.STATE_DELAY);
					params.put("stateList", stateList);
					params.put("state", "");
				}
				//坏账列表  
				if(state.equals(ClBorrowModel.STATE_BAD)){//90
					stateList = Arrays.asList(ClBorrowModel.STATE_BAD);
					params.put("stateList", stateList);
					params.put("state", "");
				}
				
			}
		}
		Page<ManageBorrowModel> page = clBorrowService.listBorrowModel(params,current,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	/**
	 * 借款还款信息详细页面      
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/borrowRepayContent.htm",method={RequestMethod.GET,RequestMethod.POST})
	public void borrowRepayContent(@RequestParam(value="borrowId") long borrowId){
		ManageBorrowModel model = clBorrowService.getModelByBorrowId(borrowId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, model);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	/**
	 * 查询借款记录 
	 * @param userId
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/manage/borrow/listBorrowLog.htm")
	public void listBorrowLog(
			@RequestParam(value="userId") long userId,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> params = new HashMap<>();
		params.put("userId", userId);
		Page<ManageBorrowModel> page = clBorrowService.listBorrowModel(params, current, pageSize);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", page.getResult());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
	
	
	/**
	 *借款进度列表
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 */
	@RequestMapping(value="/modules/manage/borrow/progress/list.htm",method={RequestMethod.GET,RequestMethod.POST})
	public void progresslist(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int currentPage,
			@RequestParam(value = "pageSize") int pageSize){
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ManageBorrowProgressModel> page =clBorrowProgressService.listModel(params,currentPage,pageSize);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, page);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
}
