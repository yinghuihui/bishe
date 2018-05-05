package com.rongdu.ow.core.module.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.exception.BussinessException;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.common.util.DateUtil;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.ClBankCard;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.domain.ClBorrowProgress;
import com.rongdu.ow.core.module.domain.ClBorrowRepay;
import com.rongdu.ow.core.module.domain.ClBorrowRepayLog;
import com.rongdu.ow.core.module.mapper.ClBorrowMapper;
import com.rongdu.ow.core.module.mapper.ClBorrowProgressMapper;
import com.rongdu.ow.core.module.mapper.ClBorrowRepayLogMapper;
import com.rongdu.ow.core.module.mapper.ClBorrowRepayMapper;
import com.rongdu.ow.core.module.model.BorrowRepayModel;
import com.rongdu.ow.core.module.model.ClBorrowModel;
import com.rongdu.ow.core.module.service.ClBankCardService;
import com.rongdu.ow.core.module.service.ClBorrowRepayService;
import com.rongdu.ow.core.module.service.ClBorrowService;
import com.rongdu.ow.core.module.service.ClCreditService;

import tool.util.NumberUtil;


/**
 * 还款计划表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:47:49
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clBorrowRepayService")
public class ClBorrowRepayServiceImpl extends BaseServiceImpl<ClBorrowRepay, Long> implements ClBorrowRepayService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowRepayServiceImpl.class);
   
    @Resource
    private ClBorrowRepayMapper clBorrowRepayMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
    @Resource
    private ClBorrowProgressMapper clBorrowProgressMapper;
    @Resource
    private ClBorrowRepayLogMapper clBorrowRepayLogMapper;
    @Resource
    private ClBankCardService clBankCardService;
    @Resource
    private ClCreditService clCreditService;
    @Resource
    private ClBorrowService clBorrowService;

	@Override
	public BaseMapper<ClBorrowRepay, Long> getMapper() {
		return clBorrowRepayMapper;
	}
	@Override
    public boolean genRepayPlan(ClBorrow borrow) {
            //放款成功,保存还款计划
            ClBorrowRepay br = new ClBorrowRepay();
         
            br.setAmount(borrow.getAmount() + borrow.getInterest());
     
            br.setBorrowId(borrow.getId());
            br.setUserId(borrow.getUserId());
            String repay = DateUtil.dateStr2(DateUtil.rollDay(DateUtil.getNow(), (Integer.parseInt(borrow.getTimeLimit())) - 1));
            repay = repay + " 23:59:59";
            br.setRepayTime(DateUtil.valueOf(repay, "yyyy-MM-dd HH:mm:ss"));
            br.setState(BorrowRepayModel.STATE_REPAY_NO);
            br.setPenaltyAmout(0.0);
            br.setPenaltyDay(0);
            br.setCreateTime(DateUtil.getNow());
  
            int result = clBorrowRepayMapper.save(br);

            if (result > 0) {
                // 调用连连支付接口进行授权
                //authApply(br);
                return true;
            }
            return false;
	}
	@Override
	public List<ClBorrowRepay> listSelective(Map<String,Object> paramMap){
		return clBorrowRepayMapper.listSelective(paramMap);
	}
    @Override
	public int updateLate(ClBorrowRepay clBorrowRepay){
    	return clBorrowRepayMapper.updateLate(clBorrowRepay);
    }
    @Override
    public List<ClBorrowRepay> findUnRepay(Map<String,Object> paramMap){
    	return clBorrowRepayMapper.findUnRepay(paramMap);
    }
    @Override
	public Map<String, Object> confirmRepay(
			ClBorrowRepay borrowRepay,
			Date repayDate, 
			String state,
			Double amount,
			String orderNo,
			String repayWay,
			String cardNo,
			Double penaltyAmout) {
        Map<String, Object> result = new HashMap<String, Object>();
        logger.debug("进入确认还款...");
        if (ClBorrowModel.STATE_FINISH.equals(state)) {
                //原先单期验证
                if (borrowRepay.getPenaltyAmout() < penaltyAmout) {
                    result.put("Code", Constant.FAIL_CODE_VALUE);
                    result.put("Msg", "逾期罚金不能大于原逾期罚金");
                    return result;
                }
        }
        logger.info("进入确认还款...借款,更新还款信息");


        //完成 还款
        boolean isPayOff = finishBorrow(borrowRepay, repayDate, state, amount, repayWay, orderNo, cardNo);
//        Integer penaltyDay = Integer.valueOf(borrowRepay.getPenaltyDay());
         ClBorrow borrow = clBorrowMapper.findByPrimary(borrowRepay.getBorrowId());
        if (isPayOff) {
        	clBorrowService.modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");
            //clCreditService.creditImprove(borrow.getAmount(), penaltyDay, borrow.getUserId());
        }
        
//        if (penaltyDay>0) {
//        	borrowRepay.setPenaltyDay((penaltyDay-borrowRepay.getPenaltyDay()));
//        	borrowRepay.setPenaltyAmout(borrowRepay.getPenaltyAmout()-borrowRepay.getPenaltyAmout().doubleValue());
//        	clBorrowRepayMapper.update(borrowRepay);
//		}

        result.put("Code", Constant.SUCCEED_CODE_VALUE);
        result.put("Msg", "还款成功");


        return result;
		
	}
    /**
     * 还款成功完成 借款
     *
     * @param borrowRepay
     * @param repayDate
     * @param state
     * @param amount
     * @param repayWay
     * @param orderNo
     * @param cardNo
     * @return true 还清 false 未还清
     */
    @Override
    public boolean finishBorrow(ClBorrowRepay borrowRepay, Date repayDate, String state, Double amount, String repayWay, String orderNo,String cardNo) {
            // 更新还款信息
            int msg = updateBorrowReplay(borrowRepay, repayDate, amount, repayWay, orderNo);
            if (msg <= 0) {
                throw new BussinessException("更新还款信息出错" + borrowRepay.getBorrowId());
            }
            // 更新借款表和借款进度状态
            msg = updateBorrow(borrowRepay.getBorrowId(), borrowRepay.getUserId(), state);
            if (msg <= 0) {
                throw new BussinessException("更新借款表和借款进度状态出错" + borrowRepay.getBorrowId());
            }
            return true;
        
	}
    /**
     * 更新借款表和借款进度状态
     *
     * @param borrowId
     * @param userId
     * @param state
     * @return
     */
    public int updateBorrow(long borrowId, long userId, String state) {
        logger.info("更新借款表和借款状态表id=" + borrowId + ",state=" + state);
        int i = 0;
        // 更新借款状态
        Map<String, Object> stateMap = new HashMap<String, Object>();
        stateMap.put("id", borrowId);
        stateMap.put("state", state);
        i = clBorrowMapper.updateSelective(stateMap);
        if (i > 0) {
            // 添加借款进度
            ClBorrowProgress bp = new ClBorrowProgress();
            bp.setBorrowId(borrowId);
            bp.setUserId(userId);
            bp.setRemark(ClBorrowModel.convertBorrowRemark(state));
            bp.setState(state);
            bp.setCreateTime(DateUtil.getNow());
            return clBorrowProgressMapper.save(bp);
        }
        return i;
    }
    
  
    /**
  	 * 更新还款计划和还款记录表
  	 * 
  	 * @param borrowRepay
  	 * @param repayDate
  	 * @param amount
  	 * @return
  	 */
  	public int updateBorrowReplay(ClBorrowRepay borrowRepay, Date repayDate, Double amount, String repayWay, String orderNo) {
  		// 更新还款计划状态
  		int i = 0;
  		logger.info("进入确认还款...借款,更新还款计划状态");
  		Map<String, Object> paramMap = new HashMap<String, Object>();
  		paramMap.put("id", borrowRepay.getId());
  		paramMap.put("state", BorrowRepayModel.STATE_REPAY_YES);
  		
  		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
  		Date repayPlanTime = DateUtil.valueOf(time.format(borrowRepay.getRepayTime()));
  		Date repay_time = DateUtil.valueOf(time.format(DateUtil.getNow()));
  		
  		if (StringUtil.isNotBlank(borrowRepay.getPenaltyDay()) && borrowRepay.getPenaltyAmout() > 0) {
  			//实际还款时间在应还款时间之前或当天（不对比时分秒），重置逾期金额和天数
  			if (!repay_time.after(repayPlanTime)) {
  				borrowRepay.setPenaltyDay(0);
  				borrowRepay.setPenaltyAmout(Double.valueOf(0));
  				paramMap.put("penaltyDay","0");
  				paramMap.put("penaltyAmout", 0.00);
  			}
  		}
  	    i=clBorrowRepayMapper.updateParam(paramMap);
  		if(i>0){
  			// 生成还款记录
  			ClBorrowRepayLog log = new ClBorrowRepayLog();
  			log.setBorrowId(borrowRepay.getBorrowId());
  			log.setRepayId(borrowRepay.getId());
  			log.setUserId(borrowRepay.getUserId());
  			log.setAmount(amount);// 实际还款金额
  			log.setRepayTime(repayDate);// 实际还款时间
  			log.setPenaltyDay(borrowRepay.getPenaltyDay());
             // 实际还款时间在应还款时间之前或当天（不对比时分秒），重置逾期金额和天数
  		    if (!repay_time.after(repayPlanTime)) {
  				log.setPenaltyAmout(0.00);
  				log.setPenaltyDay(0);
  			} else {
  				// 金额减免时 罚金可页面填写
  				double penaltyAmout = borrowRepay.getPenaltyAmout();
  				log.setPenaltyAmout(penaltyAmout);
  			}
            Map<String,Object> bankMap = new HashMap<>();
            bankMap.put("userId", borrowRepay.getUserId());
  		    ClBankCard bankCard = clBankCardService.findSelective(bankMap);
  		    
  			log.setSerialNumber(orderNo);
  			log.setRepayAccount(bankCard.getCardNo());
  			log.setRepayWay(repayWay);
  			log.setCreateTime(repayDate);
  			return clBorrowRepayLogMapper.save(log);
  		}
  		return i;
  	}
 
}