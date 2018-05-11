package com.rongdu.ow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.bootstrap.HttpServer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.mapper.ClBorrowMapper;
import com.rongdu.ow.core.module.model.BorrowProgressModel;
import com.rongdu.ow.core.module.model.ClBorrowModel;
import com.rongdu.ow.core.module.service.ClBorrowProgressService;
import com.rongdu.ow.core.module.service.ClBorrowService;

@Scope("prototype")
@Controller
public class BorrowProgressController extends BaseController{
	@Resource
	private ClBorrowService clBorrowService;
	@Resource
	private ClBorrowProgressService clBorrowProgressService;
	private
	@Resource ClBorrowMapper clBorrowMapper;
	
	/**
	 * 借款进度查询
	 * @param borrowId
	 * @throws Exception
	 */

	@RequestMapping(value = "/modules/user/borrow/findProgress.htm", method = {RequestMethod.POST,RequestMethod.GET})
	public void findProgress(
			//@RequestParam(value="borrowId") long borrowId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userId = String.valueOf(request.getSession().getAttribute("userId"));
//		Map<String,Object> borrowMap = new HashMap<>();
//		borrowMap.put("id", borrowId);
//		ClBorrow borrow = clBorrowService.getById(borrowId);
		Map<String,Object> params = new HashMap<>();
		params.put("userId", userId);
		ClBorrow borrow = clBorrowMapper.findRepayBorrow(params);
		Map<String,Object> data = new HashMap<>();
		//Map<String,Object> data = clBorrowProgressService.result(borrow);
		List<BorrowProgressModel> list = clBorrowService.borrowProgress(borrow, "detail");
		data.put("list", list);
		if(list != null && !list.isEmpty()){
			data.put("isBorrow", true);
		}
		data.put("orderNo", borrow.getOrderNo());
		String isShow = "0";
		if (ClBorrowModel.STATE_REPAY.equals(borrow.getState()) || ClBorrowModel.STATE_DELAY.equals(borrow.getState()) ||
				ClBorrowModel.STATE_FINISH.equals(borrow.getState())) {
			isShow = "1";
		}

		Map<String,Object> result = new HashMap<>();
		result.put(Constant.RESPONSE_DATA, data);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		ServletUtils.writeToResponse(response,result);
	}
}
