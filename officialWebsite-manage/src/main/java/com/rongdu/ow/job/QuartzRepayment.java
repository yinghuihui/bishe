package com.rongdu.ow.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.util.DateUtil;
import com.rongdu.ow.core.common.util.OrderNoUtil;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.domain.ClBorrowRepay;
import com.rongdu.ow.core.module.model.BorrowRepayModel;
import com.rongdu.ow.core.module.model.ClBorrowModel;
import com.rongdu.ow.core.module.service.ClBankCardService;
import com.rongdu.ow.core.module.service.ClBorrowProgressService;
import com.rongdu.ow.core.module.service.ClBorrowRepayService;
import com.rongdu.ow.core.module.service.ClBorrowService;
import com.rongdu.ow.domain.QuartzInfo;
import com.rongdu.ow.domain.QuartzLog;
import com.rongdu.ow.service.QuartzInfoService;
import com.rongdu.ow.service.QuartzLogService;


import tool.util.BeanUtil;
import tool.util.BigDecimalUtil;
import tool.util.StringUtil;

/**
 * 自动扣款还款
 * 
 * @author gc
 * @version 1.0.0
 * @date 2017年3月21日 下午3:28:37 
 * Copyright 杭州融都科技股份有限公司 All Rights Reserved
 * 官方网站：www.erongdu.com  
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Component
@Lazy(value = false)
public class QuartzRepayment implements Job {

	private static final Logger logger = Logger.getLogger(QuartzRepayment.class);

	private String repayment(){
		ClBorrowRepayService clBorrowRepayService = (ClBorrowRepayService) BeanUtil.getBean("clBorrowRepayService");
        ClBorrowProgressService clBorrowProgressService = (ClBorrowProgressService) BeanUtil.getBean("clBorrowProgressService");
        ClBorrowService clBorrowService = (ClBorrowService) BeanUtil.getBean("clBorrowService");
		ClBankCardService bankCardService = (ClBankCardService)BeanUtil.getBean("clBankCardService");
		logger.info("进入代扣还款任务...");
		//int doRepaymentMax = Global.getInt("do_repayment_max");//代扣最大次数

		// 查询待还计划
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String doRepaymentToday = Global.getValue("do_repayment_today"); // 是否代扣今天待还的
		if ("10".equals(doRepaymentToday)) { // 是
			paramMap.put("repayTime", DateUtil.rollDay(DateUtil.getDayStartTime(DateUtil.getNow()), 1));
		} else { // 否
			paramMap.put("repayTime", DateUtil.getNow());
		}
		paramMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
		List<ClBorrowRepay> borrowRepayList = clBorrowRepayService.findUnRepay(paramMap);
		logger.info("代扣还款任务，待处理的还款计划总数为：" + borrowRepayList.size());

		String quartzRemark = null;
		int succeed = 0;
		int fail = 0;
		int total = 0;
		for (ClBorrowRepay borrowRepay : borrowRepayList) {

			
				logger.info("代扣还款任务，还款计划borrowReapyId：" + borrowRepay.getId() + "开始处理");
				try {
					Long userId = borrowRepay.getUserId();
					Long borrowId = borrowRepay.getBorrowId();
					// 查询用户、用户详情、借款及用户银行卡信息

					ClBorrow borrow = clBorrowService.getById(borrowId);
					String state = borrow.getState();

					// 已坏账的不用再代扣
					if (ClBorrowModel.STATE_BAD.equals(state)) {
						continue;
					}
					//还款处理中
					if (ClBorrowModel.STATE_REPAY_PROCESSING.equals(state)) {
						continue;
					}

					// 扣款失败无异步通知 故先查询订单是否已经在支付处理中
//					List<String> scenes = new ArrayList<String>();
//					scenes.add(PayLogModel.SCENES_REPAYMENT);
//					scenes.add(PayLogModel.SCENES_ACTIVE_REPAYMENT);
//					PayLog currentPayLog = payLogService.findByScenes(borrowId, scenes);
//
//					// 查询三方
//					if (null != currentPayLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(currentPayLog.getState())) {
//						if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(currentPayLog.getState())) {
//							continue;
//						}
//						TradeStateEnum tradeStateEnum = paymentCheckService.payCheck(currentPayLog, DateUtil.getNow(), 0);//支付校验
//						payLogService.update(currentPayLog.getId(), tradeStateEnum.getState(), tradeStateEnum.getInfo());//保存查询记录
//						if(TradeStateEnum.TRADE_STATE_SUCCESS == tradeStateEnum){
//							pay(borrowRepay, currentPayLog.getOrderNo(), currentPayLog.getCardNo(),
//									borrowRepay.getAmount(), borrowRepay.getPenaltyAmout(), currentPayLog.getScenes(),
//									0L);
//							continue;
//						}else if(TradeStateEnum.TRADE_STATE_HAND == tradeStateEnum){
//							continue;
//						}
//					}

					//无记录 或 付款失败
					String orderNo = OrderNoUtil.getSerialNumber();
					double penaltyAmount = borrowRepay.getPenaltyAmout();
//					BankCard bankCard = bankCardService.getBankCardByUserId(userId);
					Double amount = BigDecimalUtil.add(borrowRepay.getAmount(), penaltyAmount);  //计算实际还款金额
//					UserBaseInfo user = userBaseInfoService.findByUserId(userId);
//					CollectModel collectModel = baofooHelper.collect(orderNo, user, borrowId, bankCard, amount);
//					TradeStateEnum tradeState = collectModel.getTradeState();
//					if(TradeStateEnum.TRADE_STATE_SUCCESS == tradeState){
						pay(borrowRepay, orderNo, "", amount,
								penaltyAmount, "",0L);
//					}
					succeed++;
					total++;
				} catch (Exception e) {
					fail++;
					total++;
					logger.error(e.getMessage(), e);
				}
			 /*else {
				List<BorrowRepayDetail> details = borrowRepayDetailService.findUnpayDetailByRepayId(borrowRepay.getId());

				for (BorrowRepayDetail detail : details
						) {
					logger.info("代扣还款任务，还款分期计划borrowReapyId：" + detail.getId() + "开始处理");
					try {
						Long userId = detail.getUserId();
						Long borrowId = detail.getBorrowId();
						// 查询用户、用户详情、借款及用户银行卡信息

						Borrow borrow = clBorrowService.getById(borrowId);
						String state = borrow.getState();

						// 达到单笔代扣次数上限的不用再代扣
						int doRepaymentCount = payLogService.doRepaymentCountByDetailId(detail.getId());
						if (doRepaymentMax > 0 && doRepaymentCount >= doRepaymentMax) {
							continue;
						}

						// 已坏账的不用再代扣
						if (BorrowModel.STATE_BAD.equals(state)) {
							continue;
						}
						//还款处理中
						if (BorrowModel.STATE_REPAY_PROCESSING.equals(state)) {
							continue;
						}

						// 扣款失败无异步通知 故先查询订单是否已经在支付处理中
						List<String> scenes = new ArrayList<String>();
						scenes.add(PayLogModel.SCENES_REPAYMENT);
						scenes.add(PayLogModel.SCENES_ACTIVE_REPAYMENT);
//						PayLog currentPayLog = payLogService.findByScenes(borrowId, scenes);

						Map<String, Object> payLogMap = new HashMap<String, Object>();
						payLogMap.put("userId", detail.getUserId());
						payLogMap.put("borrowId", detail.getBorrowId());
						payLogMap.put("type", PayLogModel.TYPE_COLLECT);
						payLogMap.put("sceness", scenes);
						payLogMap.put("detailId", detail.getId());
						PayLog currentPayLog = payLogService.findLatestOneByDetailId(payLogMap);

						// 查询三方
						if (null != currentPayLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(currentPayLog.getState())) {
							if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(currentPayLog.getState())) {
								continue;
							}
							TradeStateEnum tradeStateEnum = paymentCheckService.payCheck(currentPayLog, DateUtil.getNow(), 0);//支付校验
							payLogService.update(currentPayLog.getId(), tradeStateEnum.getState(), tradeStateEnum.getInfo());//保存查询记录
							if(TradeStateEnum.TRADE_STATE_SUCCESS == tradeStateEnum){
								pay(borrowRepay, currentPayLog.getOrderNo(), currentPayLog.getCardNo(),
										detail.getAmount().doubleValue(), detail.getDeductPenaltyAmout().doubleValue(),
										currentPayLog.getScenes(),detail.getId());
								continue;
							}else if(TradeStateEnum.TRADE_STATE_HAND == tradeStateEnum){
								continue;
							}
						}

						//无记录 或 付款失败
						String orderNo = OrderNoUtil.getSerialNumber();
						double penaltyAmount = detail.getPenaltyAmout().doubleValue();
						BankCard bankCard = bankCardService.getBankCardByUserId(userId);
						Double amount = BigDecimalUtil.add(detail.getAmount().doubleValue(), penaltyAmount);  //计算实际还款金额
						UserBaseInfo user = userBaseInfoService.findByUserId(userId);
						CollectModel collectModel = baofooHelper.collect(orderNo, user, borrowId, bankCard, amount);
						TradeStateEnum tradeState = collectModel.getTradeState();
						if(TradeStateEnum.TRADE_STATE_SUCCESS == tradeState){
							pay(borrowRepay, collectModel.getTrans_id(), collectModel.getAcc_no(),
									detail.getAmount().doubleValue(), detail.getDeductPenaltyAmout().doubleValue(),
									currentPayLog.getScenes(),detail.getId());
						}
						succeed++;
						total++;
					} catch (Exception e) {
						fail++;
						total++;
						logger.error(e.getMessage(), e);
					}
				}
			}*/
		}

		quartzRemark = "处理总数" + total + "个，成功" + succeed + "个，失败" + fail + "个";
		logger.info("代扣还款任务，执行完毕，" + quartzRemark);
		return quartzRemark;
	}
	
	private void pay(ClBorrowRepay borrowRepay, String orderNo, String cardNo, double amount, double penaltyAmount,
			String scences, long detailId) {
		ClBorrowRepayService borrowRepayService = (ClBorrowRepayService)BeanUtil.getBean("clBorrowRepayService");
		//ClSmsService clSmsService = (ClSmsService)BeanUtil.getBean("clSmsService");
            // 查找对应的还款计划
//		if (PayLogModel.SCENES_PREPAY.equals(scences)) {
			borrowRepayService.confirmRepay(borrowRepay,
					DateUtil.getNow(),
					ClBorrowModel.STATE_FINISH,
					amount,
					orderNo,
					"10",
					cardNo,
					penaltyAmount);
			// 发送代扣还款成功短信提醒
//			clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId());
//		} else {
//			borrowRepayService.confirmRepay(borrowRepay,
//					DateUtil.getNow(),
//					BorrowModel.STATE_FINISH,
//					amount,
//					orderNo,
//					BorrowRepayLogModel.REPAY_WAY_CHARGE,
//					cardNo,
//					penaltyAmount);
//			clSmsService.repayInformMany(borrowRepay.getUserId(), borrowRepay.getBorrowId(),detailId);
//		}

	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
		QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
		// 查询当前任务信息
		QuartzInfo quartzInfo = quartzInfoService.findByCode("doRepayment");
		Map<String, Object> qiData = new HashMap<>();
		qiData.put("id", quartzInfo.getId());

		QuartzLog quartzLog = new QuartzLog();
		quartzLog.setQuartzId(quartzInfo.getId());
		quartzLog.setStartTime(DateUtil.getNow());
		try {
			String remark = repayment();
			quartzLog.setTime(DateUtil.getNow().getTime() - quartzLog.getStartTime().getTime());
			quartzLog.setResult("10");
			quartzLog.setRemark(remark);
			qiData.put("succeed", quartzInfo.getSucceed() + 1);
		} catch (Exception e) {
			quartzLog.setResult("20");
			qiData.put("fail", quartzInfo.getFail() + 1);
			logger.error(e.getMessage(), e);
		} finally {
			logger.info("保存代扣还款定时任务执行记录");
			quartzLogService.save(quartzLog);
			quartzInfoService.update(qiData);
		}
	}
	
	/**
	 * 连连代扣
	 */
//	@SuppressWarnings("unused")
//	private void repayMentLianLian(){
//		logger.info("进入代扣还款任务...");
//		CloanUserService cloanUserService = (CloanUserService) BeanUtil.getBean("cloanUserService");
//		UserBaseInfoService userBaseInfoService = (UserBaseInfoService) BeanUtil.getBean("userBaseInfoService");
//		BankCardService bankCardService = (BankCardService) BeanUtil.getBean("bankCardService");
//		ClBorrowService clBorrowService = (ClBorrowService) BeanUtil.getBean("clBorrowService");
//		BorrowRepayService borrowRepayService = (BorrowRepayService) BeanUtil.getBean("borrowRepayService");
//
//		BorrowRepayDetailService borrowRepayDetailService = (BorrowRepayDetailService) BeanUtil.getBean("borrowRepayDetailService");
//		PayLogService payLogService = (PayLogService) BeanUtil.getBean("payLogService");
//		ClSmsService clSmsService = (ClSmsService) BeanUtil.getBean("clSmsService");
//		int doRepaymentMax = Global.getInt("do_repayment_max");//代扣最大次数
//		
//		// 查询待还计划
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		String doRepaymentToday = Global.getValue("do_repayment_today"); // 是否代扣今天待还的
//		if("10".equals(doRepaymentToday)){ // 是
//			paramMap.put("repayTime", DateUtil.rollDay(DateUtil.getDayStartTime(DateUtil.getNow()), 1));
//		} else { // 否
//			paramMap.put("repayTime", DateUtil.getNow());
//		}
//		paramMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
//		List<BorrowRepay> borrowRepayList = borrowRepayService.findUnRepay(paramMap);
//		logger.info("代扣还款任务，待处理的还款计划总数为：" + borrowRepayList.size());
//		
//		String quartzRemark = null;
//		int succeed = 0;
//		int fail = 0;
//		int total = 0;
//		for (BorrowRepay borrowRepay : borrowRepayList) {
//			if (borrowRepay.getType().equals(ProductConstant.TYPE_SINGLE)) {
//				logger.info("单期代扣还款任务，还款计划borrowReapyId：" + borrowRepay.getId() + "开始处理");
//				try {
//					// 查询用户、用户详情、借款及用户银行卡信息
//					User user = cloanUserService.getById(borrowRepay.getUserId());
//					UserBaseInfo baseInfo = userBaseInfoService.findByUserId(borrowRepay.getUserId());
//					Borrow borrow = clBorrowService.getById(borrowRepay.getBorrowId());
//					BankCard bankCard = bankCardService.getBankCardByUserId(borrowRepay.getUserId());
//
//					// 达到单笔代扣次数上限的不用再代扣
//					int doRepaymentCount = payLogService.doRepaymentNum(borrow.getId());
//					if(doRepaymentMax > 0 && doRepaymentCount >= doRepaymentMax){
//						continue;
//					}
//
//					// 已坏账的不用再代扣
//					if(BorrowModel.STATE_BAD.equals(borrow.getState())){
//						continue;
//					}
//
//					// 扣款失败无异步通知 故先查询订单是否已经在支付处理中
//					Map<String, Object> payLogMap = new HashMap<String, Object>();
//					payLogMap.put("userId", borrowRepay.getUserId());
//					payLogMap.put("borrowId", borrowRepay.getBorrowId());
//					payLogMap.put("type", PayLogModel.TYPE_COLLECT);
//					payLogMap.put("scenes", PayLogModel.SCENES_REPAYMENT);
//					PayLog repaymentLog = payLogService.findLatestOne(payLogMap);
//
//					// 支付记录存在且不是支付失败，需要查询支付方得到准确结果
//					if (null != repaymentLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(repaymentLog.getState())) {
//						if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(repaymentLog.getState())) {
//							continue;
//						}
//
//						String orderNo = OrderNoUtil.getSerialNumber();
//						QueryRepaymentModel queryRepayment = new QueryRepaymentModel(orderNo);
//						queryRepayment.setNo_order(repaymentLog.getOrderNo());
//						queryRepayment.setDt_order(DateUtil.dateStr3(repaymentLog.getPayReqTime()));
//						LianLianHelper helper = new LianLianHelper();
//						queryRepayment = (QueryRepaymentModel) helper.queryRepayment(queryRepayment);
//
//						if (queryRepayment.checkReturn() && LianLianConstant.RESULT_SUCCESS.equals(queryRepayment.getResult_pay())) {
//							// 查找对应的还款计划
//							Map<String, Object> param = new HashMap<String, Object>();
//							param.put("id", borrowRepay.getId());
//							param.put("repayTime", DateUtil.getNow());
//							param.put("repayWay",BorrowRepayLogModel.REPAY_WAY_CHARGE);
//							param.put("repayAccount", bankCard.getCardNo());
//							param.put("amount", borrowRepay.getAmount());
//							param.put("serialNumber", repaymentLog.getOrderNo());
//							param.put("penaltyAmout",borrowRepay.getPenaltyAmout());
//							param.put("state", "10");
//							if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
//								borrowRepayService.confirmRepay(param);
//							}
//
//							// 更新订单状态
//							Map<String, Object> payLogParamMap = new HashMap<String, Object>();
//							payLogParamMap.put("state",PayLogModel.STATE_PAYMENT_SUCCESS);
//							payLogParamMap.put("updateTime", DateUtil.getNow());
//							payLogParamMap.put("id", repaymentLog.getId());
//							payLogService.updateSelective(payLogParamMap);
//
//							// 发送代扣还款成功短信提醒
//							clSmsService.repayInform(borrowRepay.getUserId(), borrowRepay.getBorrowId());
//							continue;
//						} else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_PROCESSING.equals(queryRepayment.getResult_pay())) {
//							continue;
//						} else {
//							// 更新订单状态
//							Map<String, Object> payLogParamMap = new HashMap<String, Object>();
//							payLogParamMap.put("state",PayLogModel.STATE_PAYMENT_FAILED);
//							payLogParamMap.put("updateTime", DateUtil.getNow());
//							payLogParamMap.put("id", repaymentLog.getId());
//							payLogService.updateSelective(payLogParamMap);
//						}
//					}
//
//					Date payReqTime = DateUtil.getNow();
//					String orderNo = OrderNoUtil.getSerialNumber();
//					RepaymentModel repayment = new RepaymentModel(orderNo);
//					repayment.setUser_id(user.getUuid());
//					repayment.setBusi_partner(LianLianConstant.GOODS_VIRTUAL);
//					repayment.setDt_order(DateUtil.dateStr3(payReqTime));
//					repayment.setName_goods("还款" + borrow.getOrderNo());
//					repayment.setInfo_order("repayment_" + borrow.getOrderNo());
//
//					double amount = BigDecimalUtil.add(borrowRepay.getAmount(), borrowRepay.getPenaltyAmout());  //计算实际还款金额
//					if ("dev".equals(Global.getValue("app_environment"))) {
//						repayment.setMoney_order("0.01");
//					} else {
//						repayment.setMoney_order(StringUtil.isNull(amount));
//					}
//
//					repayment.setAmount(amount);
//					RiskItems riskItems = new RiskItems();
//					riskItems.setFrms_ware_category("2010");
//					riskItems.setUser_info_mercht_userno(user.getUuid());
//					riskItems.setUser_info_bind_phone(baseInfo.getPhone());
//					riskItems.setUser_info_dt_register(DateUtil.dateStr3(user.getRegistTime()));
//					riskItems.setUser_info_full_name(baseInfo.getRealName());
//					riskItems.setUser_info_id_no(baseInfo.getIdNo());
//					riskItems.setUser_info_identify_type("1");
//					riskItems.setUser_info_identify_state("1");
//					repayment.setRisk_item(JSONObject.toJSONString(riskItems));
//					repayment.setSchedule_repayment_date(DateUtil.dateStr2(borrowRepay.getRepayTime()));
//					repayment.setRepayment_no(borrow.getOrderNo());
//					repayment.setNo_agree(bankCard.getAgreeNo());
//					repayment.setNotify_url(Global.getValue("server_host") + "/pay/lianlian/repaymentNotify.htm");
//
//
//					LianLianHelper helper = new LianLianHelper();
//					//请求连连代扣，repayment里面有返回值
//					repayment = (RepaymentModel) helper.repayment(repayment);
//
//					PayLog payLog = new PayLog();
//					payLog.setOrderNo(repayment.getOrderNo());
//					payLog.setUserId(borrowRepay.getUserId());
//					payLog.setBorrowId(borrowRepay.getBorrowId());
//					payLog.setAmount(repayment.getAmount());
//					payLog.setCardNo(bankCard.getCardNo());
//					payLog.setBank(bankCard.getBank());
//					payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
//					payLog.setType(PayLogModel.TYPE_COLLECT);
//					payLog.setScenes(PayLogModel.SCENES_REPAYMENT);
//					payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
//					payLog.setCode(repayment.getRet_code());
//					payLog.setRemark(repayment.getRet_msg());
//					payLog.setPayReqTime(payReqTime);
//					payLog.setCreateTime(DateUtil.getNow());
//					payLogService.save(payLog);
//
//					succeed++;
//					total++;
//
//					//8104就是没有该还款计划的code
//					if(repayment.getRet_code().equals("8104")){
//						//重新上传还款计划
//						logger.info("借款订单号："+borrow.getId()+"无扣款计划信息，重新生成还款计划");
//						borrowRepayService.authSignApply(borrowRepay.getUserId());
//					}
//				} catch (Exception e) {
//					fail++;
//					total++;
//					logger.error(e.getMessage(), e);
//				}
//			}
//
//			if (borrowRepay.getType().equals(ProductConstant.TYPE_MULTIPLE)) {
//
//				List<BorrowRepayDetail> details = borrowRepayDetailService.findUnpayDetailByRepayId(borrowRepay.getId());
//
//				for (BorrowRepayDetail detail : details
//					 ) {
//					logger.info("分期代扣还款任务，还款计划borrowReapyDetailId：" + detail.getId() + "开始处理");
//					try {
//						// 查询用户、用户详情、借款及用户银行卡信息
//						User user = cloanUserService.getById(detail.getUserId());
//						UserBaseInfo baseInfo = userBaseInfoService.findByUserId(detail.getUserId());
//						Borrow borrow = clBorrowService.getById(detail.getBorrowId());
//						BankCard bankCard = bankCardService.getBankCardByUserId(detail.getUserId());
//
//						// 达到单笔代扣次数上限的不用再代扣
//						int doRepaymentCount = payLogService.doRepaymentCountByDetailId(detail.getId());
//						if(doRepaymentMax > 0 && doRepaymentCount >= doRepaymentMax){
//							continue;
//						}
//
//						// 已坏账的不用再代扣
//						if(BorrowModel.STATE_BAD.equals(borrow.getState())){
//							continue;
//						}
//
//						// 扣款失败无异步通知 故先查询订单是否已经在支付处理中
//						Map<String, Object> payLogMap = new HashMap<String, Object>();
//						payLogMap.put("userId", detail.getUserId());
//						payLogMap.put("borrowId", detail.getBorrowId());
//						payLogMap.put("type", PayLogModel.TYPE_COLLECT);
//						payLogMap.put("scenes", PayLogModel.SCENES_REPAYMENT);
//						payLogMap.put("detailId", detail.getId());
//						PayLog repaymentLog = payLogService.findLatestOneByDetailId(payLogMap);
//
//						// 支付记录存在且不是支付失败，需要查询支付方得到准确结果
//						if (null != repaymentLog && !PayLogModel.STATE_PAYMENT_FAILED.equals(repaymentLog.getState())) {
//							if (PayLogModel.STATE_PAYMENT_SUCCESS.equals(repaymentLog.getState())) {
//								continue;
//							}
//
//							String orderNo = OrderNoUtil.getSerialNumber();
//							QueryRepaymentModel queryRepayment = new QueryRepaymentModel(orderNo);
//							queryRepayment.setNo_order(repaymentLog.getOrderNo());
//							queryRepayment.setDt_order(DateUtil.dateStr3(repaymentLog.getPayReqTime()));
//							LianLianHelper helper = new LianLianHelper();
//							queryRepayment = (QueryRepaymentModel) helper.queryRepayment(queryRepayment);
//
//							if (queryRepayment.checkReturn() && LianLianConstant.RESULT_SUCCESS.equals(queryRepayment.getResult_pay())) {
//								// 查找对应的还款计划
//								Map<String, Object> param = new HashMap<String, Object>();
//								param.put("id", detail.getId());
//								param.put("repayTime", DateUtil.getNow());
//								param.put("repayWay",BorrowRepayLogModel.REPAY_WAY_CHARGE);
//								param.put("repayAccount", bankCard.getCardNo());
//								param.put("amount", detail.getAmount());
//								param.put("serialNumber", repaymentLog.getOrderNo());
//								param.put("penaltyAmout",detail.getDeductPenaltyAmout());
//								param.put("state", "10");
//								if (!borrowRepay.getState().equals(BorrowRepayModel.STATE_REPAY_YES)) {
//									borrowRepayService.confirmRepay(param);
//								}
//
//								// 更新订单状态
//								Map<String, Object> payLogParamMap = new HashMap<String, Object>();
//								payLogParamMap.put("state",PayLogModel.STATE_PAYMENT_SUCCESS);
//								payLogParamMap.put("updateTime", DateUtil.getNow());
//								payLogParamMap.put("id", repaymentLog.getId());
//								payLogService.updateSelective(payLogParamMap);
//
//								// 发送代扣还款成功短信提醒
//								clSmsService.repayInform(detail.getUserId(), detail.getBorrowId());
//								continue;
//							} else if (queryRepayment.checkReturn() && LianLianConstant.RESULT_PROCESSING.equals(queryRepayment.getResult_pay())) {
//								continue;
//							} else {
//								// 更新订单状态
//								Map<String, Object> payLogParamMap = new HashMap<String, Object>();
//								payLogParamMap.put("state",PayLogModel.STATE_PAYMENT_FAILED);
//								payLogParamMap.put("updateTime", DateUtil.getNow());
//								payLogParamMap.put("id", repaymentLog.getId());
//								payLogService.updateSelective(payLogParamMap);
//							}
//						}
//
//						Date payReqTime = DateUtil.getNow();
//						String orderNo = OrderNoUtil.getSerialNumber();
//						RepaymentModel repayment = new RepaymentModel(orderNo);
//						repayment.setUser_id(user.getUuid());
//						repayment.setBusi_partner(LianLianConstant.GOODS_VIRTUAL);
//						repayment.setDt_order(DateUtil.dateStr3(payReqTime));
//						repayment.setName_goods("还款" + borrow.getOrderNo());
//						repayment.setInfo_order("repayment_" + borrow.getOrderNo());
//
//						double amount = detail.getAmount().add(detail.getDeductPenaltyAmout()).doubleValue();  //计算实际还款金额
//						if ("dev".equals(Global.getValue("app_environment"))) {
//							repayment.setMoney_order("0.01");
//						} else {
//							repayment.setMoney_order(StringUtil.isNull(amount));
//						}
//
//						repayment.setAmount(amount);
//						RiskItems riskItems = new RiskItems();
//						riskItems.setFrms_ware_category("2010");
//						riskItems.setUser_info_mercht_userno(user.getUuid());
//						riskItems.setUser_info_bind_phone(baseInfo.getPhone());
//						riskItems.setUser_info_dt_register(DateUtil.dateStr3(user.getRegistTime()));
//						riskItems.setUser_info_full_name(baseInfo.getRealName());
//						riskItems.setUser_info_id_no(baseInfo.getIdNo());
//						riskItems.setUser_info_identify_type("1");
//						riskItems.setUser_info_identify_state("1");
//						repayment.setRisk_item(JSONObject.toJSONString(riskItems));
//						repayment.setSchedule_repayment_date(DateUtil.dateStr2(detail.getRepayTime()));
//						repayment.setRepayment_no(borrow.getOrderNo());
//						repayment.setNo_agree(bankCard.getAgreeNo());
//						repayment.setNotify_url(Global.getValue("server_host") + "/pay/lianlian/repaymentNotify.htm");
//
//
//						LianLianHelper helper = new LianLianHelper();
//						//请求连连代扣，repayment里面有返回值
//						repayment = (RepaymentModel) helper.repayment(repayment);
//
//						PayLog payLog = new PayLog();
//						payLog.setOrderNo(repayment.getOrderNo());
//						payLog.setUserId(detail.getUserId());
//						payLog.setBorrowId(detail.getBorrowId());
//						payLog.setAmount(repayment.getAmount());
//						payLog.setCardNo(bankCard.getCardNo());
//						payLog.setBank(bankCard.getBank());
//						payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
//						payLog.setType(PayLogModel.TYPE_COLLECT);
//						payLog.setScenes(PayLogModel.SCENES_REPAYMENT);
//						payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
//						payLog.setCode(repayment.getRet_code());
//						payLog.setRemark(repayment.getRet_msg());
//						payLog.setPayReqTime(payReqTime);
//						payLog.setCreateTime(DateUtil.getNow());
//						payLogService.save(payLog);
//
//						succeed++;
//						total++;
//
//						//8104就是没有该还款计划的code
//						if(repayment.getRet_code().equals("8104")){
//							//重新上传还款计划
//							logger.info("借款订单号："+borrow.getId()+"无扣款计划信息，重新生成还款计划");
//							borrowRepayService.authSignApply(detail.getUserId());
//						}
//					} catch (Exception e) {
//						fail++;
//						total++;
//						logger.error(e.getMessage(), e);
//					}
//				}
//			}
//		}
//		
//		quartzRemark = "处理总数"+total+"个，成功"+succeed+"个，失败"+fail+"个";
//		logger.info("代扣还款任务，执行完毕，" + quartzRemark);
//	
}