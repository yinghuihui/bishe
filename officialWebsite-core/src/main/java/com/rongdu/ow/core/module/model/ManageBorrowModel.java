package com.rongdu.ow.core.module.model;

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
