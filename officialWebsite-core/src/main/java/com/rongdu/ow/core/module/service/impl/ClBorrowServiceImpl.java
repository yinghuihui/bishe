package com.rongdu.ow.core.module.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.exception.BussinessException;
import com.rongdu.ow.core.common.exception.SimpleMessageException;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.common.util.DateUtil;
import com.rongdu.ow.core.common.util.OrderNoUtil;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.domain.ClBorrowProgress;
import com.rongdu.ow.core.module.domain.ClBorrowRepay;
import com.rongdu.ow.core.module.domain.ClBorrowRepayLog;
import com.rongdu.ow.core.module.domain.ClCredit;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;
import com.rongdu.ow.core.module.mapper.ClBorrowMapper;
import com.rongdu.ow.core.module.mapper.ClBorrowProgressMapper;
import com.rongdu.ow.core.module.mapper.ClBorrowRepayLogMapper;
import com.rongdu.ow.core.module.mapper.ClBorrowRepayMapper;
import com.rongdu.ow.core.module.mapper.ClCreditMapper;
import com.rongdu.ow.core.module.mapper.ClUserBaseInfoMapper;
import com.rongdu.ow.core.module.model.BorrowProgressModel;
import com.rongdu.ow.core.module.model.ClBorrowModel;
import com.rongdu.ow.core.module.model.ManageBorrowModel;
import com.rongdu.ow.core.module.service.ClBorrowRepayService;
import com.rongdu.ow.core.module.service.ClBorrowService;

import tool.util.BigDecimalUtil;


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
    @Resource
    private ClBorrowRepayService clBorrowRepayService;
    @Resource
    private ClUserBaseInfoMapper clUserBaseInfoMapper;
    @Resource
    private ClBorrowRepayMapper clBorrowRepayMapper;
    @Resource
    private ClBorrowRepayLogMapper clBorrowRepayLogMapper;

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
		}if(state.equals(ClBorrowModel.STATE_REFUSED)) {
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
	 @Override
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
	 @Override
	 public Page<ManageBorrowModel> listModel(Map<String,Object> params,int currentPage, int pageSize){
		 PageHelper.startPage(currentPage, pageSize);
		 List<ManageBorrowModel> list = clBorrowMapper.listModel(params);
		 return (Page<ManageBorrowModel>) list;
	 }
	 @Override
	 public Page<ManageBorrowModel> listReview(Map<String,Object> params,int currentPage, int pageSize){
		 PageHelper.startPage(currentPage, pageSize);
		 List<ManageBorrowModel> list = clBorrowMapper.listReview(params);
		 return (Page<ManageBorrowModel>) list;
	 }
	 public int manualVerifyBorrow(Long borrowId, String state, String remark){
		 int code = 0;
			ClBorrow borrow = clBorrowMapper.findByPrimary(borrowId);
			if (borrow != null) {
				if(!borrow.getState().equals(ClBorrowModel.STATE_PRE)){
					logger.error("人工审核失败,当前状态不是待审核");
					throw new BussinessException("人工审核失败,当前状态不是待审核");
				}
				Map<String,Object> map = new HashMap<>();
				map.put("id", borrowId);
				map.put("state", state);
				map.put("remark", remark);
				map.put("preState", ClBorrowModel.STATE_PRE);
				code = clBorrowMapper.reviewState(map);
				if (code!=1) {
					throw new BussinessException("人工审核失败,当前状态不是待审核");
				}
				savePressState(borrow, state,"");
				if (ClBorrowModel.STATE_REFUSED.equals(state)) {
					// 审核不通过返回信用额度
					modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");
				}
				// 人工复审成功 放款
				if (ClBorrowModel.STATE_PASS.equals(state)) {
					underLineLoan(borrow);
//					if (!"10".equals(Global.getValue("manual_loan")))  { //系统配置的是否放款审核
//						//borrowLoan(borrow, new Date());
//						underLineLoan(borrow);
//					}else {
//						//到待放款审核状态	
//						int result = modifyState(borrow.getId(), ClBorrowModel.WAIT_AUDIT_LOAN,ClBorrowModel.STATE_PASS);
//						logger.info("人工复审通过 待放款审核状态result: "+result);
//						if(result == 1){
//							savePressState(borrow, ClBorrowModel.WAIT_AUDIT_LOAN,"");
//						}
//					}
				}
			} else {
				logger.error("审核失败，当前标不存在");
				throw new BussinessException("审核失败，当前标不存在");
			}
			return code;
	 }
	 public void  underLineLoan(ClBorrow borrow){
			Map<String, Object> map = new HashMap<>();
			map.put("id", borrow.getId());
			map.put("state", ClBorrowModel.STATE_REPAY);
			clBorrowMapper.updatePayState(map);

			// 放款进度添加
			ClBorrowProgress bp = new ClBorrowProgress();
			bp.setUserId(borrow.getUserId());
			bp.setBorrowId(borrow.getId());
			bp.setState(ClBorrowModel.STATE_REPAY);
			bp.setRemark(ClBorrowModel.convertBorrowRemark(bp.getState()));
			bp.setCreateTime(DateUtil.getNow());
			clBorrowProgressMapper.save(bp);

			ClBorrow borrowb = clBorrowMapper.findByPrimary(borrow.getId());
			
			// 生成还款计划并授权
			clBorrowRepayService.genRepayPlan(borrowb);
		}
	 @Override
	 public ClBorrow findByPrimary(Long borrowId){
		 return clBorrowMapper.findByPrimary(borrowId);
	 }
	 @Override
	 public int updateSelective(Map<String,Object> params){
		 return clBorrowMapper.updateSelective(params);
	 }
	 @Override
	 public Page<ManageBorrowModel> listBorrowModel(Map<String, Object> params, int currentPage, int pageSize) {
			PageHelper.startPage(currentPage, pageSize);
			List<ManageBorrowModel> list = clBorrowMapper.listBorrowModel(params);
			return (Page<ManageBorrowModel>) list;
		}
	 
	 /**
		 * 借款详细信息
		 */
		@SuppressWarnings("static-access")
		@Override
		public ManageBorrowModel getModelByBorrowId(long borrowId) {
			ManageBorrowModel model = new ManageBorrowModel();
			ClBorrow borrow = clBorrowMapper.findByPrimary(borrowId);
			if (borrow == null) {
				logger.error("查询的借款标不存在");
			} else {
				model = model.instance(borrow);
				// model.setBorrowId(borrow.getId());
				ClUserBaseInfo userBaseInfo = clUserBaseInfoMapper.findByUserId(borrow.getUserId());
				if (userBaseInfo != null) {
					model.setPhone(userBaseInfo.getPhone());
					model.setRealName(userBaseInfo.getRealName());
				}

				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("borrowId", borrowId);
				paramMap.put("state", BorrowProgressModel.PROGRESS_LOAN_SUCCESS);
				ClBorrowProgress bp = clBorrowProgressMapper.findFirst(paramMap);
				if (bp != null) {
					model.setLoanTime(bp.getCreateTime());
				}
				paramMap = new HashMap<String, Object>();
				paramMap.put("borrowId", borrowId);
				ClBorrowRepay borrowRepay = clBorrowRepayMapper.findSelective(paramMap);
				if (borrowRepay != null) {
					model.setPenaltyAmout(borrowRepay.getPenaltyAmout());
					model.setPenaltyDay(borrowRepay.getPenaltyDay());
					if(borrowRepay.getAmount() != null){
						model.setRepayTotal(BigDecimalUtil.add(borrowRepay.getAmount(),borrowRepay.getPenaltyAmout()));
					} else {
						model.setRepayTotal(0.0);
					}
				}
				paramMap = new HashMap<String, Object>();
				paramMap.put("borrowId", borrowId);


//				BigDecimal amount = BigDecimal.ZERO;
//				BigDecimal repayYesTotal = BigDecimal.ZERO;
//				List<ClBorrowRepayLog> logs = clBorrowRepayLogMapper.listSelective(paramMap);
//				if (CollectionUtils.isNotEmpty(logs)) {
//					for (ClBorrowRepayLog log: logs
//							) {
//						model.setRepayTime(DateUtil.dateStr(log.getRepayTime(),DateUtil.DATEFORMAT_STR_001));
//						amount = amount.add(BigDecimal.valueOf(log.getAmount()));
//						repayYesTotal = repayYesTotal.add(BigDecimal.valueOf(log.getAmount())).add(BigDecimal.valueOf(log.getPenaltyAmout()));
//					}
//				}
//
//				model.setRepayAmount(amount.doubleValue());
//				model.setRepayYesTotal(repayYesTotal.doubleValue());

				ClBorrowRepayLog borrowRepaylog = clBorrowRepayLogMapper.findSelective(paramMap);
				if (borrowRepaylog != null) {
					model.setRepayTime(DateUtil.dateStr(borrowRepaylog.getRepayTime(),DateUtil.DATEFORMAT_STR_001));
					model.setRepayAmount(borrowRepaylog.getAmount());
					if(borrowRepay.getAmount() != null){
						model.setRepayYesTotal(borrowRepaylog.getAmount());
					} else {
						model.setRepayYesTotal(0.0);
					}
				 }
				
//				paramMap = new HashMap<String, Object>();
//				paramMap.put("borrowId", borrowId);
//				UrgeRepayOrder order=urgeRepayOrderMapper.findSelective(paramMap);
//				if(order!=null){
//					model.setLevel(order.getLevel());
//				 }
				}
			
			return model;
		}
		
		/**
		 * 借款进度显示
		 * 
		 * @param borrow
		 * @param pageFlag
		 *            detail代表详情页，index首页，首页不显示审核不通过和放款成功的进度，显示可以借款的信息
		 * @return
		 */
		public List<BorrowProgressModel> borrowProgress(ClBorrow borrow,
				String pageFlag) {
			List<BorrowProgressModel> list = new ArrayList<BorrowProgressModel>();
			Map<String, Object> bpMap = new HashMap<String, Object>();
			bpMap.put("borrowId", borrow.getId());
			List<BorrowProgressModel> pgList;
			//int day = getAgainBorrowDays(borrow.getUserId());
            int day = 30;
			// 待审核
			if (ClBorrowModel.STATE_PRE.equals(borrow.getState())) {
				bpMap.put("state", borrow.getState());
				pgList = clBorrowProgressMapper.listProgress(bpMap);
				Calendar cal = Calendar.getInstance(); 
				cal.setTime(pgList.get(0).getCreateTime());
				cal.add(Calendar.SECOND, +1);
				BorrowProgressModel progress = new BorrowProgressModel();
				progress.setUserId(borrow.getUserId());
				progress.setBorrowId(borrow.getId());
				progress.setRemark("已进入风控审核状态，请耐心等待。");
				progress.setStr("审核中");
				progress.setState(progress.getStr());
				progress.setType("10");
				progress.setCreateTime(cal.getTime());
				list.add(progress);

				progress = pgList.get(0);
				progress.setStr(progress.getState());
				progress.setState(progress.getState());
				progress.setType("10");
				list.add(progress);
			}

			// 审核不通过 （自动审核不通过，人工复审不通过）借款记录
			if ("detail".equals(pageFlag)
					&& (ClBorrowModel.STATE_REFUSED.equals(borrow.getState()))) {
				bpMap.put("state", borrow.getState());
				pgList = clBorrowProgressMapper.listProgress(bpMap);
				
				int size = pgList.size();
				BorrowProgressModel progress = pgList.get(size - 1);
				progress.setStr(progress.getState());
				progress.setState(progress.getState());
				progress.setType("20");
				if (day>0) {
					progress.setRemark(progress.getRemark()+"，请等待"+day+"天后可再次申请借款");
				}else if(ClBorrowModel.STATE_REFUSED.equals(borrow.getState())){
					progress.setRemark("很遗憾，您未通过审核");
				}
				list.add(progress);

				progress = pgList.get(0);
				progress.setStr(progress.getState());
				progress.setState(progress.getState());
				progress.setType("10");
				
				
				list.add(progress);
			}
			
		

			// 打款中（放款失败）
			if (ClBorrowModel.STATE_REPAY_FAIL.equals(borrow.getState())
					|| ClBorrowModel.STATE_REPAY_FAIL.equals(borrow.getState())
					|| ClBorrowModel.STATE_PASS.equals(borrow.getState())) {
				bpMap.put("state", borrow.getState());
				pgList = clBorrowProgressMapper.listProgress(bpMap);
				boolean passFlag = true;
				for (int i = pgList.size() - 1; i >= 0; i--) {
					BorrowProgressModel progress = pgList.get(i);
					progress.setType("10");
					if (i == 0) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
					}

					if (passFlag
							&& ( BorrowProgressModel.PROGRESS_PERSON_PASS.equals(progress.getState()))) {
						
						Calendar cal = Calendar.getInstance();
						cal.setTime(progress.getCreateTime());
						cal.add(Calendar.SECOND, +1);
						BorrowProgressModel progress2 = new BorrowProgressModel();
						progress2.setUserId(Long.valueOf(borrow.getUserId()));
						progress2.setBorrowId(borrow.getId());
						progress2.setStr("打款中");
						progress2.setState("打款中");
						progress2.setMsg("打款中，请查看您的提现银行卡");
						progress2.setRemark("打款中，请查看您的提现银行卡");
						progress2.setType("10");
						progress2.setCreateTime(cal.getTime());
						list.add(progress2);
						
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
						
					}

//					if (BorrowProgressModel.PROGRESS_LOAN_FAIL.equals(progress
//							.getState())) {
//						progress.setMsg("打款中，请查看您的提现银行卡。");
//						
//						Map<String, Object> params = new HashMap<String, Object>();
//						params.put("borroId", progress.getBorrowId());
//						params.put("type", PayLogModel.TYPE_PAYMENT);
//						params.put("scenes", PayLogModel.SCENES_LOANS);
//						PayLog payLog = payLogMapper.findLatestOne(params);
//						if(payLog != null){
//							String changeBankCardCode = Global.getValue("lianlian_change_bank_card_code");
//							String changeBankCardRemark = Global.getValue("lianlian_change_bank_card_remark");
//							String payLogCode = payLog.getCode();
//							String payLogRemark = payLog.getRemark();
//							if(StringUtil.isNotBlank(payLogCode) 
//									&& StringUtil.isNotBlank(changeBankCardCode)
//									&& changeBankCardCode.contains(payLogCode)){
//								logger.warn("userId:" + progress.getUserId() + "， payLogCode:"+ payLogCode +" ，因银行卡原因打款失败，需要更换银行卡");
//								progress.setMsg("因银行卡原因打款失败，请更换您的银行卡");
//							}else if (StringUtil.isNotBlank(payLogRemark) 
//									&& StringUtil.isNotBlank(changeBankCardRemark)
//									&& payLogRemark.contains(changeBankCardRemark)){
//								logger.warn("userId:" + progress.getUserId() + "， payLogRemark:"+ payLogRemark +" ，因银行卡原因打款失败，需要更换银行卡");
//								progress.setMsg("因银行卡原因打款失败，请更换您的银行卡");
//							}
//						}
//						
//						progress.setStr(progress.getState());
//						progress.setState(progress.getStr());
//						list.add(progress);
//					}
				}
			}

			// 待还款（放款成功）
			if (ClBorrowModel.STATE_REPAY.equals(borrow.getState())) {
				bpMap.put("state", borrow.getState());
				pgList = clBorrowProgressMapper.listProgress(bpMap);
				boolean passFlag = true;
				for (int i = pgList.size() - 1; i >= 0; i--) {
					BorrowProgressModel progress = pgList.get(i);
					progress.setType("10");
					if (i == 0) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
					}

					if (passFlag && (BorrowProgressModel.PROGRESS_PERSON_PASS
									.equals(progress.getState()))) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
						passFlag = false;
					}

					if (BorrowProgressModel.PROGRESS_LOAN_SUCCESS.equals(progress.getState())) {
							double repayAmount = borrow.getRealAmount() + borrow.getInterest();

							Calendar cal = Calendar.getInstance();
							cal.setTime(progress.getCreateTime());
							cal.add(Calendar.SECOND, +1);
							progress = new BorrowProgressModel();
							progress.setUserId(Long.valueOf(borrow.getUserId()));
							progress.setBorrowId(borrow.getId());
							progress.setStr("待还款");
							progress.setRemark("您需要在" + borrow.getTimeLimit() + "天后还款" + repayAmount + "元");
							Map<String,Object> paramMap = new HashMap<>();
							paramMap.put("borrowId", borrow.getId());
							ClBorrowRepay repay = clBorrowRepayMapper.findSelective(paramMap);
							if (repay!=null) {
								day = DateUtil.daysBetween(new Date(),
										repay.getRepayTime());
								if (day > 0) {
									progress.setRemark("您需要在" + day + "天后还款" + repayAmount + "元");
								} else if (day == 0) {
									progress.setRemark("您需要在今天还款" + repayAmount + "元");
								}
							}

							if ("1".equals(borrow.getTimeLimit())) {
								progress.setRemark("您需要在今天还款" + repayAmount+ "元");
							}
							progress.setState("待还款");
							progress.setType("10");
							progress.setCreateTime(cal.getTime());
							list.add(progress);

							progress = pgList.get(i);
							progress.setStr("已打款");
							progress.setState(progress.getState());
							list.add(progress);
//						} else {
//							double repayAmount = detail.getAmount().add(detail.getDeductPenaltyAmout()).doubleValue();
//
//							Calendar cal = Calendar.getInstance();
//							cal.setTime(progress.getCreateTime());
//							cal.add(Calendar.SECOND, +1);
//							progress = new BorrowProgressModel();
//							progress.setUserId(Long.valueOf(borrow.getUserId()));
//							progress.setBorrowId(borrow.getId());
//							progress.setStr("待还款");
//							progress.setRemark("您需要在" + DateUtil.daysBetween(new Date(), detail.getRepayTime()) + "天后还款" + repayAmount + "元");
//							Map<String,Object> paramMap = new HashMap<>();
//							paramMap.put("borrowId", borrow.getId());
//							day = DateUtil.daysBetween(new Date(),
//									detail.getRepayTime());
//							if (day > 0) {
//								progress.setRemark("您需要在" + day + "天后还款" + repayAmount + "元");
//							} else if (day == 0) {
//								progress.setRemark("您需要在今天还款" + repayAmount + "元");
//							}
//
//							if ("1".equals(borrow.getTimeLimit())) {
//								progress.setRemark("您需要在今天还款" + repayAmount+ "元");
//							}
//							progress.setState("待还款");
//							progress.setType("10");
//							progress.setCreateTime(cal.getTime());
//							list.add(progress);
//
//							progress = pgList.get(i);
//							progress.setStr("已打款");
//							progress.setState(progress.getStr());
//							list.add(progress);
//						}
						
					}
				}
			}
			
			
			// 还款成功
			if ("detail".equals(pageFlag)
					&& (ClBorrowModel.STATE_FINISH.equals(borrow.getState()))) {
				bpMap.put("state", borrow.getState());
				pgList = clBorrowProgressMapper.listProgress(bpMap);
				boolean passFlag = true;
				for (int i = pgList.size() - 1; i >= 0; i--) {
					BorrowProgressModel progress = pgList.get(i);
					progress.setType("10");
					if (i == 0) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
					}

					if (passFlag
							&& (BorrowProgressModel.PROGRESS_PERSON_PASS
									.equals(progress.getState()))) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
						passFlag = false;
					}

					if (BorrowProgressModel.PROGRESS_LOAN_SUCCESS.equals(progress
							.getState())
							|| BorrowProgressModel.PROGRESS_REPAY_SUCCESS
									.equals(progress.getState())) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
					}
				}
			}

			// 逾期
			int signState = Integer.parseInt(ClBorrowModel.STATE_DELAY);
			if (ClBorrowModel.STATE_DELAY.equals(borrow.getState())
					|| ClBorrowModel.STATE_BAD.equals(borrow.getState())) {
				bpMap.put("state", ClBorrowModel.STATE_BAD);
				pgList = clBorrowProgressMapper.listProgress(bpMap);
				boolean passFlag = true;
				boolean overdueFlag = true;

				for (int i = pgList.size() - 1; i >= 0; i--) {
					BorrowProgressModel progress = pgList.get(i);
					progress.setType("10");
					int prState = Integer.parseInt(progress.getState());
					if (i == 0) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
					}

					if (passFlag
							&& (BorrowProgressModel.PROGRESS_PERSON_PASS
									.equals(progress.getState()))) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
						passFlag = false;
					}

					if (BorrowProgressModel.PROGRESS_LOAN_SUCCESS.equals(progress
							.getState())) {
						progress.setStr(progress.getState());
						progress.setState(progress.getState());
						list.add(progress);
					}

					if (overdueFlag && prState >= signState) {
						progress = pgList.get(pgList.size() - 1);
						progress.setStr(progress.getState());
						progress.setState(progress.getState());

						Calendar cal = Calendar.getInstance();
						cal.setTime(progress.getCreateTime());
						cal.add(Calendar.SECOND, +1);
						progress.setRemark("您已逾期,请尽快还款");
						progress.setState("已逾期");
						progress.setType("10");
						progress.setCreateTime(cal.getTime());
						list.add(progress);
						overdueFlag = false;
					}
				}
			}
			return list;
		}
}