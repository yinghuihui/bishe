package com.rongdu.ow.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.ClBankCard;
import com.rongdu.ow.core.module.service.ClBankCardService;

@Controller
@Scope("prototype")
public class BankCardController extends BaseController{
	public static final Logger logger = LoggerFactory.getLogger(BankCardController.class);
    @Resource
    private ClBankCardService clBankCardService;
    
    
	@RequestMapping(value = "/modules/user/bankcard/save.htm", method = { RequestMethod.POST })
	public void save(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="cname",required=true) String name,
			@RequestParam(value="cardPhone",required=true) String cardPhone,
			@RequestParam(value="bankname",required=true) String bankname,
			@RequestParam(value="carNo",required=true) String carNo) throws UnsupportedEncodingException, IOException{
		    Map<String, Object> resultMap = new HashMap<String, Object>();
		    long userId = (long) request.getSession().getAttribute("userId");
		    ClBankCard bankCard =  new ClBankCard();
		    bankCard.setUserId(userId);
		    bankCard.setPhone(cardPhone);
		    bankCard.setCardNo(carNo);
		    bankCard.setBindTime(new Date());
		    bankCard.setBank(bankname);
		    int result = clBankCardService.bankCardSave(bankCard, name);
		    if (result > 0) {
				resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
			} else {
				resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				resultMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
			}
		    ServletUtils.writeToResponse(response,resultMap);
		    
	}
	@RequestMapping(value = "/modules/user/bankcard/findcard.htm")
		public void save(HttpServletRequest request,HttpServletResponse response){
		    //String userId = (String) request.getSession().getAttribute("userId");
		    String userId ="1";
		    Map<String, Object> paramMap = new HashMap<String, Object>();
		    paramMap.put("userId", userId);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			ClBankCard clBankCard = clBankCardService.findSelective(paramMap);
			resultMap.put(Constant.RESPONSE_DATA, clBankCard);
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "查询成功");
			ServletUtils.writeToResponse(response,resultMap);
		}
	
}
