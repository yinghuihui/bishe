package com.rongdu.ow.core.module.model;

import com.rongdu.ow.core.module.domain.ClBorrowRepay;

public class BorrowRepayModel extends ClBorrowRepay{
private static final long serialVersionUID = 1L;

	
	/** 还款状态 -已还款 */
	public static final String STATE_REPAY_YES = "10";
	
	/** 还款状态 - 未还款 */
	public static final String STATE_REPAY_NO = "20";

	/** 还款方式 - 逾期正常还款 */
	public static final String OVERDUE_REPAYMENT = "30";
	
	
	/**
	 * 借款人手机号
	 */
	private String phone;
	/**
	 * 还款时间格式化 (yyyy-MM-dd HH:mm)
	 */
	private String repayTimeStr;
	/**
	 * 实际还款时间
	 */
	private String realRepayTime;
	
	/**
	 * 实际还款金额
	 */
	private String realRepayAmount;
}
