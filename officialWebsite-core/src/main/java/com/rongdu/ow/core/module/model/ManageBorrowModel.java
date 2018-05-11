package com.rongdu.ow.core.module.model;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.rongdu.ow.core.module.domain.ClBorrow;

public class ManageBorrowModel extends ClBorrow{
	private static final long serialVersionUID = 1L;
	public ManageBorrowModel(Long userId, String amount, String borrowUse, String timeLimit, String realAmount,
			String cardId) {
		super(userId, amount, borrowUse, timeLimit, realAmount, cardId);
		// TODO Auto-generated constructor stub
	}
	public ManageBorrowModel(){
		
	}
	public static ManageBorrowModel instance(ClBorrow borrow) {
		ManageBorrowModel borrowModel = new ManageBorrowModel();
		BeanUtils.copyProperties(borrow, borrowModel);
		return borrowModel;
	}
	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 状态中文含义
	 */
	private String stateStr;
	/**
	 * 还款金额
	 */
	private Double repayAmount;
	/**
	 * 还款时间
	 */
	private String repayTime;
	/**
	 * 放款时间
	 * 
	 */
	private Date LoanTime;
	/**
	 * 逾期金额
	 * 
	 */
	private Double PenaltyAmout;
	/**
	 * 逾期时间
	 * 
	 */
	private Integer  PenaltyDay;
	
	/**
	 * 应还款总额 加逾期金额
	 */
	private Double repayTotal;
	
	/**
	 * 已还款总额 加逾期金额
	 */
	private Double repayYesTotal;
	
	public Double getRepayTotal() {
		return repayTotal;
	}
	public void setRepayTotal(Double repayTotal) {
		this.repayTotal = repayTotal;
	}
	public Double getRepayYesTotal() {
		return repayYesTotal;
	}
	public void setRepayYesTotal(Double repayYesTotal) {
		this.repayYesTotal = repayYesTotal;
	}
	public Double getPenaltyAmout() {
		return PenaltyAmout;
	}
	public void setPenaltyAmout(Double penaltyAmout) {
		PenaltyAmout = penaltyAmout;
	}
	public Integer getPenaltyDay() {
		return PenaltyDay;
	}
	public void setPenaltyDay(Integer penaltyDay) {
		PenaltyDay = penaltyDay;
	}
	public Date getLoanTime() {
		return LoanTime;
	}
	public void setLoanTime(Date loanTime) {
		LoanTime = loanTime;
	}
	public Double getRepayAmount() {
		return repayAmount;
	}
	public void setRepayAmount(Double repayAmount) {
		this.repayAmount = repayAmount;
	}
	public String getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStateStr() {
		this.stateStr = ClBorrowModel.manageConvertBorrowState(this.getState());
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	

}
