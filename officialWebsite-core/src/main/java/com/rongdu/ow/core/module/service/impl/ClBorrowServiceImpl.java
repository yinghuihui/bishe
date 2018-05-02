package com.rongdu.ow.core.module.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.exception.BussinessException;
import com.rongdu.ow.core.common.exception.SimpleMessageException;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.common.util.OrderNoUtil;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.domain.ClBorrowProgress;
import com.rongdu.ow.core.module.domain.ClCredit;
import com.rongdu.ow.core.module.mapper.ClBorrowMapper;
import com.rongdu.ow.core.module.mapper.ClBorrowProgressMapper;
import com.rongdu.ow.core.module.mapper.ClCreditMapper;
import com.rongdu.ow.core.module.model.ClBorrowModel;
import com.rongdu.ow.core.module.service.ClBorrowService;


/**
 * 借款信息表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:45:17
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clBorrowService")
public class ClBorrowServiceImpl extends BaseServiceImpl<ClBorrow, Long> implements ClBorrowService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowServiceImpl.class);
   
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private  ClBorrowProgressMapper  clBorrowProgressMapper;
    @Resource
    private ClCreditMapper clCreditMapper;

	@Override
	public BaseMapper<ClBorrow, Long> getMapper() {
		return clBorrowMapper;
	}
	@Override
	public ClBorrow saveApply(ClBorrow borrow){
		ClBorrow realBorrow = saveBorrow(borrow);
		if (realBorrow != null && realBorrow.getId() > 0) {
			long borrowId = realBorrow.getId();
			savePressState(realBorrow,ClBorrowModel.STATE_PRE,"");
			modifyCredit(realBorrow.getUserId(),realBorrow.getAmount(),"used");
		} else {
			throw new BussinessException("借款失败");
		}
		return realBorrow;
	}
	@Override
	public ClBorrow saveBorrow(ClBorrow borrow){
		String interestRate = Global.getValue("interest_rate");
		Integer period = Integer.valueOf(borrow.getTimeLimit());
		BigDecimal amount = new BigDecimal(borrow.getAmount());
		BigDecimal periodInterest = amount.multiply(new BigDecimal(interestRate));
		BigDecimal interest = periodInterest.multiply(new BigDecimal(period));
		borrow.setInterest(interest.doubleValue());
		borrow.setState(ClBorrowModel.STATE_PRE);
		borrow.setCreateTime(new Date());
		String orderNo  = OrderNoUtil.getSerialNumber();
		borrow.setOrderNo(orderNo);
		clBorrowMapper.save(borrow);
		return borrow;
	}
	@Override
	public void savePressState(ClBorrow borrow, String state,String remark){
		ClBorrowProgress borrowProgress = new ClBorrowProgress();
		borrowProgress.setUserId(borrow.getUserId());
		borrowProgress.setBorrowId(borrow.getId());
		borrowProgress.setCreateTime(new Date());
		if (state.equals(ClBorrowModel.STATE_PRE)) {
			borrowProgress.setRemark("借款"
					+ StringUtil.isNull(borrow.getAmount())
					+ "元，期限"
					+ borrow.getTimeLimit() 
					+ "天，费用"
					+ StringUtil.isNull(borrow.getInterest()) + "元，"
					+ ClBorrowModel.convertBorrowRemark(state));
		}if(state.equals(ClBorrowModel.STATE_AUTO_REFUSED)) {
			borrowProgress.setRemark(remark);
		} else {
			borrowProgress.setRemark(ClBorrowModel.convertBorrowRemark(state));
		}
		borrowProgress.setState(state);
		clBorrowProgressMapper.save(borrowProgress);
	}
	/**
	 * 信用额度修改
	 * 
	 * @param userId
	 * @param amount
	 */
	@Override
	public int modifyCredit(Long userId, double amount, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> creditMap = new HashMap<String, Object>();
		creditMap.put("userId", userId);
		ClCredit credit = clCreditMapper.findSelective(creditMap);
		if (credit != null) {
			params.put("id", credit.getId());
			if ("used".equals(type)) {
				params.put("used", amount);
				params.put("unuse", - amount);
			} else {
				params.put("used", - amount);
				if(amount + credit.getUnuse() > credit.getTotal()){
					params.put("unuse", credit.getTotal() - credit.getUsed() + amount);
				} else {
					params.put("unuse", amount);
				}
			}
			int result = clCreditMapper.updateAmount(params);
			
			if(result != 1){
				logger.info("更新额度失败，不能出现负值，type：" + type + ",userId:" + userId);
				throw new BussinessException("更新额度失败");
			}
			return result;
		} else {
			logger.info("更新额度失败，不能出现负值，type：" + type + ",userId:" + userId);
			throw new BussinessException("更新额度失败");
		}
	}
	 public Map<String,Object> findIndex(){
		 Map<String,Object> paramMap = new HashMap<>();
		 String borrowTimeLimit = Global.getValue("borrow_timeLimit");
		 String[] dayList = borrowTimeLimit.split(",");
		 paramMap.put("dayList", dayList);
		 BigDecimal loanCeiling = new BigDecimal(Global.getValue("init_credit"));
		 BigDecimal loanFloor = new BigDecimal(Global.getValue("floor_amout"));
		 BigDecimal loanStep = new BigDecimal(Global.getValue("loan_step"));
		 String [] creditList = getBorrowCredit(loanCeiling,loanFloor,loanStep);
		 paramMap.put("creditList", creditList);
		 paramMap.put("total", loanCeiling);
		 paramMap.put("unuse", loanCeiling);
		 return paramMap;
		 
	 }
	 public String[] getBorrowCredit(BigDecimal loanCeiling,BigDecimal loanFloor,BigDecimal loanStep){
	        if (loanCeiling == null || loanFloor == null || loanStep == null) {
	            throw new SimpleMessageException("该产品放款区间配置出错");
	        }
	        int size = loanCeiling.subtract(loanFloor).divide(loanStep, 0, RoundingMode.DOWN).intValue()+1;
	        if (size < 1) {
	            throw new SimpleMessageException("该放款区间配置出错");
	        }

	        String[] credis = new String[size];

	        BigDecimal amount = loanFloor;
	        for (int i = 0; i < size; i++) {
	            String a = amount.toString();
	            credis[i] = a;
	            amount = amount.add(loanStep);
	        }
	        return credis;
	    }
	 public Map<String,Object> indexRepayAmount(String camount,String timeLimit){
		 Map<String,Object> map = new HashMap<>();
		 String interestRate = Global.getValue("interest_rate");
		 BigDecimal amount = new BigDecimal(camount);
		 BigDecimal periodInterest = amount.multiply(new BigDecimal(interestRate));
		 BigDecimal interest = periodInterest.multiply(new BigDecimal(timeLimit));
		 String repayAmount = String.valueOf(interest.add(amount).setScale(2));
		 map.put("realamount", camount);
		 map.put("repayamount", repayAmount);
		 return map;
		 
	 }
}