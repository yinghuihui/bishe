package com.rongdu.ow.job;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.exception.ServiceException;
import com.rongdu.ow.core.common.util.JsonUtil;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.domain.ClBorrowRepay;
import com.rongdu.ow.core.module.model.BorrowRepayModel;
import com.rongdu.ow.core.module.model.ClBorrowModel;
import com.rongdu.ow.core.module.service.ClBorrowProgressService;
import com.rongdu.ow.core.module.service.ClBorrowRepayService;
import com.rongdu.ow.core.module.service.ClBorrowService;
import com.rongdu.ow.domain.QuartzInfo;
import com.rongdu.ow.domain.QuartzLog;
import com.rongdu.ow.service.QuartzInfoService;
import com.rongdu.ow.service.QuartzLogService;

import tool.util.BeanUtil;
import tool.util.DateUtil;


@Component
@Lazy(value = false)
public class QuartzLate implements Job {

    private static final Logger logger = Logger.getLogger(QuartzLate.class);

    /**
     * 定时计算逾期
     *
     * @throws ServiceException
     */
    @SuppressWarnings("unchecked")
	public String late() throws ServiceException {
    	ClBorrowRepayService clBorrowRepayService = (ClBorrowRepayService) BeanUtil.getBean("clBorrowRepayService");
        ClBorrowProgressService clBorrowProgressService = (ClBorrowProgressService) BeanUtil.getBean("clBorrowProgressService");
        ClBorrowService clBorrowService = (ClBorrowService) BeanUtil.getBean("clBorrowService");
        logger.info("进入逾期计算...");
        String quartzRemark = null;
        int succeed = 0;
        int fail = 0;
        int total = 0;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("state", BorrowRepayModel.STATE_REPAY_NO);
        List<ClBorrowRepay> list = clBorrowRepayService.listSelective(paramMap);
        long badDebtDay = Long.parseLong(Global.getValue("bad_debt_day"));//逾期多少天自动标记为坏账
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                try {
                  
                        long day = DateUtil.daysBetween(list.get(i).getRepayTime(), new Date());
                        if (day > list.get(i).getPenaltyDay()) {
                            if (day > 0) {
                                ClBorrow borrow = clBorrowService.findByPrimary(list.get(i).getBorrowId());
                                String overdueRate = Global.getValue("overdue_rate");
                                BigDecimal penaltyAmount = BigDecimal.valueOf(list.get(i).getPenaltyAmout());
                                //不超过金额的20%
                                BigDecimal penaltyAmountMax = BigDecimal.valueOf(borrow.getAmount()).multiply(new BigDecimal(0.2));
                                if (penaltyAmount.compareTo(penaltyAmountMax) < 0) {
                                    penaltyAmount = BigDecimal.valueOf(borrow.getAmount()).multiply(new BigDecimal(overdueRate).multiply(new BigDecimal(day)));
                                    if (penaltyAmount.compareTo(penaltyAmountMax) > 0) {
                                        penaltyAmount = penaltyAmountMax;
                                    }
                                }

                                Map<String,Object> data = new HashMap<>();
                                ClBorrowRepay br = new ClBorrowRepay();
                                br.setId(list.get(i).getId());
                                br.setPenaltyAmout(penaltyAmount.doubleValue());
                                br.setPenaltyDay((int) Math.abs(day));

                                logger.info("还款计划id--" + br.getId() + " ==> 已经逾期 " + br.getPenaltyDay() + " 天,逾期费用 " + br.getPenaltyAmout() + "元");

                                int msg = clBorrowRepayService.updateLate(br);
                                if (msg > 0) {
                                    logger.debug("--修改逾期信息成功--");
                                    //保存逾期进度
                                    int count = clBorrowProgressService.isNormalBorrowProgress(list.get(i).getBorrowId());
                                    if (count <= 0) {
                                        logger.debug("---------添加逾期进度---------");
                                        clBorrowService.savePressState(borrow, ClBorrowModel.STATE_DELAY, "");
                                        data = new HashMap<>();
                                        data.put("id", list.get(i).getBorrowId());
                                        data.put("state", ClBorrowModel.STATE_DELAY);
                                        msg = clBorrowService.updateSelective(data);
                                        logger.debug("---------添加逾期结束---------");
                                    }

                                    //催收计划
                                    logger.debug("---------修改催收计划start-------");
                                   // UrgeRepayOrder uro = urgeRepayOrderService.findByBorrowId(list.get(i).getBorrowId());
//                                    if (uro==null) {
//                                        urgeRepayOrderService.saveOrder(list.get(i).getBorrowId());
//                                        clSmsService.overdue(list.get(i).getBorrowId());//逾期第一天发送短信通知
//                                    } else {
//                                        Map<String, Object> uroMap = new HashMap<>();
//                                        uroMap.put("penaltyAmout", br.getPenaltyAmout());
//                                        uroMap.put("penaltyDay", br.getPenaltyDay());
//                                        uroMap.put("id", uro.getId());
                                        if (day >= badDebtDay) {
                                            //修改催款计划
//                                            uroMap.put("state", UrgeRepayOrderModel.STATE_ORDER_BAD);
                                            //添加借款进度
                                            clBorrowService.savePressState(borrow, ClBorrowModel.STATE_BAD, "");
                                            //修改借款信息
                                            data = new HashMap<>();
                                            data.put("id", list.get(i).getBorrowId());
                                            data.put("state", ClBorrowModel.STATE_BAD);
                                            msg = clBorrowService.updateSelective(data);
                                        }
//                                        msg = urgeRepayOrderService.updateLate(uroMap);
//                                    }
                                    logger.debug("---------修改催收计划end-------");
                                } else {
                                    logger.error("定时计算逾期任务修改数据失败");
                                }
                            }
                        }
                        succeed++;
                        total++;
                    
                } catch (Exception e) {
                    fail++;
                    total++;
                    logger.error(e.getMessage(), e);
                }

            }
        }

        logger.info("逾期计算结束...");
        quartzRemark = "执行总次数" + total + ",成功" + succeed + "次,失败" + fail + "次";
        return quartzRemark;
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
        QuartzLog ql = new QuartzLog();
        Map<String, Object> qiData = new HashMap<>();
        QuartzInfo qi = quartzInfoService.findByCode("doLate");
        try {
            qiData.put("id", qi.getId());
            ql.setQuartzId(qi.getId());
            ql.setStartTime(DateUtil.getNow());

            String remark = late();

            ql.setTime(DateUtil.getNow().getTime() - ql.getStartTime().getTime());
            ql.setResult("10");
            ql.setRemark(remark);
            qiData.put("succeed", qi.getSucceed() + 1);

        } catch (Exception e) {
            ql.setResult("20");
            qiData.put("fail", qi.getFail() + 1);
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("保存定时任务日志");
            quartzLogService.save(ql);
            quartzInfoService.update(qiData);
        }
    }

    /**
     * 处理多期
     *
     * @param borrowRepay
     */
//    @SuppressWarnings("unchecked")
//	private void multiplePeriod(BorrowRepay borrowRepay) throws Exception {
//
//        BorrowRepayDetailService borrowRepayDetailService = (BorrowRepayDetailService) BeanUtil.getBean("borrowRepayDetailService");
//        BorrowRepayService borrowRepayService = (BorrowRepayService) BeanUtil.getBean("borrowRepayService");
//        ClBorrowService clBorrowService = (ClBorrowService) BeanUtil.getBean("clBorrowService");
//        ElFeeService elFeeService = (ElFeeService) BeanUtil.getBean("elFeeServiceImpl");
//        ClRpProductService clRpProductService = (ClRpProductService) BeanUtil.getBean("clRpProductServiceImpl");
//        ClRpProduct product = clRpProductService.findByType(borrowRepay.getType());
//        Borrow borrow = clBorrowService.findByPrimary(borrowRepay.getBorrowId());
//        UrgeRepayOrderService urgeRepayOrderService = (UrgeRepayOrderService) BeanUtil.getBean("urgeRepayOrderService");
//        ClSmsService clSmsService = (ClSmsService) BeanUtil.getBean("clSmsService");
//
//        BigDecimal totalPenaltyAmount = BigDecimal.ZERO;
//
//        int totalPenaltyDay = 0;
//        int day;
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("repayId", borrowRepay.getId());
//        paramMap.put("states", new String[]{BorrowRepayDetailModel.STATE_REPAY_NO, BorrowRepayDetailModel.STATE_REPAY_OVERDUE});
//        //根据还款计划id查询分期还款计划表
//        List<BorrowRepayDetail> list = borrowRepayDetailService.listSelective(paramMap);
//        for (BorrowRepayDetail borrowRepayDetail : list) {
//            day = DateUtil.daysBetween(new Date(), borrowRepayDetail.getRepayTime());
//            //当天已经计算过的不再重复计算
//            if (Math.abs(day) > borrowRepayDetail.getPenaltyDay()) {
//                //有逾期
//                if (day < 0) {
//                    //更新逾期天数、罚息金额、逾期状态
//                    borrowRepayDetail.setPenaltyDay(Math.abs(day));
//
//                    BigDecimal penaltyAmount = borrowRepayDetail.getPenaltyAmout();
//                    BigDecimal penaltyAmountMax = BigDecimal.valueOf(borrow.getAmount()).multiply(product.getPenaltyAmoutCeiling());
//                    if (penaltyAmount.compareTo(penaltyAmountMax) < 0) {
//                        Map<String, Object> param = JsonUtil.parse(borrow.getProductParams(), Map.class);
//                        param.put(ElConstant.PARAM_SYS_CURRENT_AMOUNT, borrowRepayDetail.getCapital());
//                        param.put(ElConstant.PARAM_SYS_PENALTY_DAY, Math.abs(day));
//
//                        penaltyAmount = elFeeService.calculateUseExtra(ElConstant.FEE_PENALTY_AMOUNT, String.valueOf(list.size()), product.getId(), param);
//
//                        if (penaltyAmount.compareTo(penaltyAmountMax) > 0) {
//                            penaltyAmount = penaltyAmountMax;
//                        }
//                    }
//
//                    borrowRepayDetail.setPenaltyAmout(penaltyAmount);
//                    borrowRepayDetail.setState("30");
//                    borrowRepayDetailService.updateById(borrowRepayDetail);
//                    //增加还款计划总逾期天数和金额
//                    totalPenaltyDay += Math.abs(day);
//                    totalPenaltyAmount = totalPenaltyAmount.add(penaltyAmount);
//                    
//                    //添加修改催收订单信息
//                    UrgeRepayOrder uro = urgeRepayOrderService.findByRepayDetailId(borrowRepayDetail.getId());
//                    if (uro==null) {
//                    	urgeRepayOrderService.saveOrderByRepayDetail(borrow,borrowRepayDetail);
//                    	clSmsService.overdue(borrow.getId());//逾期第一天发送短信通知
//					}else {
//						long badDebtDay = Long.parseLong(Global.getValue("bad_debt_day"));//逾期多少天自动标记为坏账
//						Map<String, Object> uroMap = new HashMap<>();
//                        uroMap.put("penaltyAmout", borrowRepayDetail.getPenaltyAmout());
//                        uroMap.put("penaltyDay", borrowRepayDetail.getPenaltyDay());
//                        uroMap.put("id", uro.getId());
//                        if (day >= badDebtDay) {
//                            //修改催款计划
//                            uroMap.put("state", UrgeRepayOrderModel.STATE_ORDER_BAD);
//                            //添加借款进度
//                            clBorrowService.savePressState(borrow, BorrowModel.STATE_BAD, "");
//                            //修改借款信息
//                            Map<String, Object> data = new HashMap<>();
//                            data.put("id", borrow.getId());
//                            data.put("state", BorrowModel.STATE_BAD);
//                            clBorrowService.updateSelective(data);
//                        }
//                        urgeRepayOrderService.updateLate(uroMap);
//					}
//                }
//            }
//        }
//        //有逾期则更新逾期状态
//        if (totalPenaltyDay > 0) {
//            //计算还款计划总逾期天数和金额
//            List<Map<String, Object>> penaltyDataList = borrowRepayDetailService.calculatePenaltyData(borrowRepay.getId());
//            borrowRepay.setPenaltyDay(String.valueOf(penaltyDataList.get(0).get("penaltyDay")));
//            borrowRepay.setPenaltyAmout(((BigDecimal) penaltyDataList.get(0).get("penaltyAmout")).doubleValue());
//            //更新还款计划主表状态
//            borrowRepayService.updateById(borrowRepay);
//            //更新借款表逾期状态
//            if (!"50".equals(borrow.getState())) {
//                borrow.setState("50");
//                clBorrowService.updateById(borrow);
//            }
//
//            clBorrowService.savePressState(borrow, BorrowModel.STATE_DELAY, "");
//        }
//    }

//    public static void main(String[] args) {
//        int a = 2;
//        System.out.println(new Integer(1).equals(a));
//
//        System.out.println(com.rongdu.cashloan.core.common.util.DateUtil.getMaxDayOfCurrentMonth(com.rongdu.cashloan.core.common.util.DateUtil.rollYear(com.rongdu.cashloan.core.common.util.DateUtil.rollMon(new Date(), 2), -2)));
//
//
//        FelEngine el = new ElEngine();
//        FelContext context = el.getContext();
//
//        context.set("amount", 26000);
//
//        context.set("interest_rate", 0.01);
//        context.set("period", 24);
//        for (int i = 0; i < 24; i++) {
//
//
//            System.out.println(i + 1 + " 还款:" + el.eval("PMT(interest_rate, period, amount)"));
//
//            context.set("current_period", i + 1);
//            System.out.println(i + 1 + " 利息:" + new BigDecimal(el.eval("(amount*interest_rate+PMT(interest_rate,period,amount))*POWER((1+interest_rate),(current_period-1))-PMT(interest_rate,period,amount)").toString()).setScale(2, RoundingMode.HALF_UP));
//
//            System.out.println(i + 1 + "本金:" + new BigDecimal(el.eval("-(amount*interest_rate+PMT(interest_rate,period,amount))*POWER((1+interest_rate),(current_period-1))").toString()).setScale(2, RoundingMode.HALF_UP));
//
//
//        }
//
//        List<BorrowRepayDetail> details = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            BorrowRepayDetail detail = new BorrowRepayDetail();
//            detail.setPenaltyDay(i);
//            details.add(detail);
//            System.out.println(detail.toString());
//        }
//
//        System.out.println(details.stream().mapToInt((da) -> da.getPenaltyDay()).sum());
//    }

}
