package com.rongdu.ow.core.module.model;

import java.util.Date;

import com.rongdu.ow.core.module.domain.ClUserBaseInfo;

public class ManagerUserModel extends ClUserBaseInfo{
	private static final long serialVersionUID = 1L;
	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 登录密码
	 */
	private String loginPwd;

	/**
	 * 上次登录密码修改时间
	 */
	private Date loginpwdModifyTime;

	/**
	 * 注册时间
	 */
	private Date registTime;

	/**
	 * 银行卡号
	 */
	private String cardNo;

	/**
	 * 开户行
	 */
	private String bank;

	/**
	  * 预留手机号
	 */
	 private String bankPhone;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Date getLoginpwdModifyTime() {
		return loginpwdModifyTime;
	}

	public void setLoginpwdModifyTime(Date loginpwdModifyTime) {
		this.loginpwdModifyTime = loginpwdModifyTime;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}
	 
	 
}
