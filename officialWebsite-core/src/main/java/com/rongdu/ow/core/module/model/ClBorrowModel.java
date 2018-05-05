package com.rongdu.ow.core.module.model;

import com.rongdu.ow.core.module.domain.ClBorrow;

public class ClBorrowModel extends ClBorrow{
	private static final long serialVersionUID = 1L;
	/**
	 * 10-待审核
	 */
	/** 申请成功待审核*/
	public static final String STATE_PRE = "10";
	/** 自动审核通过 */
	//public static final String STATE_AUTO_PASS = "20";
	/** 自动审核不通过*/
	//public static final String STATE_AUTO_REFUSED = "21";
	/** 自动审核未决待人工复审*/
	//public static final String STATE_NEED_REVIEW = "22";
	/** 人工复审通过 */
	public static final String STATE_PASS = "26";
	/** 人工复审不通过*/
	public static final String STATE_REFUSED = "27";
	
	/** 放款成功 */
	public static final String STATE_REPAY = "30";
	/** 待放款审核*/
	//public static final String WAIT_AUDIT_LOAN = "301"; 
	/** 放款审核通过*/
	//public static final String AUDIT_LOAN_PASS = "302"; 
	/** 放款审核不通过*/
	//public static final String AUDIT_LOAN_FAIL = "303"; 
	/** 放款失败 */
	public static final String STATE_REPAY_FAIL = "31";
	
	/** 还款成功 */
	public static final String STATE_FINISH = "40";
	/** 还款成功-金额减免 */
	//public static final String STATE_REMISSION_FINISH = "41";
	/** 还款处理中*/
	public static final String STATE_REPAY_PROCESSING = "43";
	/** 逾期 */
	public static final String STATE_DELAY = "50";
	/** 坏账*/
	public static final String STATE_BAD = "90";
	public ClBorrowModel(Long userId, String amount, String borrowUse, String timeLimit, String realAmount,
			String cardId) {
		super(userId, amount, borrowUse, timeLimit, realAmount, cardId);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 借款状态中文描述转换
	 * 
	 * @param state
	 * @return
	 */
	public static String convertBorrowRemark(String state) {
		String remarkStr = " - ";
		if (ClBorrowModel.STATE_PRE.equals(state)) {
			remarkStr = "系统审核中,请耐心等待";
//		} else if (ClBorrowModel.STATE_AUTO_PASS.equals(state)) {
//			remarkStr = "恭喜通过风控审核";
//		} else if (ClBorrowModel.STATE_AUTO_REFUSED.equals(state)) {
//			remarkStr = "很遗憾,您未通过审核";
//		} else if (ClBorrowModel.STATE_NEED_REVIEW.equals(state)) {
//			remarkStr = "系统复审中,请耐心等待";
		} else if (ClBorrowModel.STATE_PASS.equals(state)) {
			remarkStr = "恭喜通过风控复审";
		} else if (ClBorrowModel.STATE_REFUSED.equals(state)) {
			remarkStr = "很遗憾,您未通过复审";
		} else if (ClBorrowModel.STATE_REPAY.equals(state)) {
			remarkStr = "已打款,请查看您的提现银行卡";
		} else if (ClBorrowModel.STATE_REPAY_FAIL.equals(state)) {
			remarkStr = "放款失败";
		} else if (ClBorrowModel.STATE_FINISH.equals(state)) {
			remarkStr = "还款成功";
//		} else if (ClBorrowModel.STATE_REMISSION_FINISH.equals(state)) {
//			remarkStr = "借款人无法支付全部借款金额,减免还款成功";
//		} else if(STATE_REPAY_PROCESSING.equals(state)){
//			remarkStr = ("还款处理中");
		} else if (ClBorrowModel.STATE_DELAY.equals(state)) {
			remarkStr = "您的借款已逾期";
		} else if (ClBorrowModel.STATE_BAD.equals(state)) {
			remarkStr = "经长时间催收,没有结果";
//		}else if (ClBorrowModel.WAIT_AUDIT_LOAN.equals(state)) {
//			remarkStr = "放款审核中,请等待";
//		}else if (ClBorrowModel.AUDIT_LOAN_PASS.equals(state)) {
//			remarkStr = "恭喜您,放款审核通过";
//		} else if (ClBorrowModel.AUDIT_LOAN_FAIL.equals(state)) {
//			remarkStr = "很遗憾,审核放款不通过";
		}
		return remarkStr;
	}
	
	/** 
	 * 响应给管理后台的借款/借款进度状态中文描述转换
	 * @return stateStr  
	 */
	public static String manageConvertBorrowState(String state) {
		String stateStr = " - ";
		if(STATE_PRE.equals(state)){
			stateStr=("待审核");
//		}else if(STATE_AUTO_PASS.equals(state)){
//			stateStr=("自动审核通过");
//		}else if(STATE_AUTO_REFUSED.equals(state)){
//			stateStr=("自动审核不通过 ");
//		}else if(STATE_NEED_REVIEW.equals(state)){
//			stateStr=("待人工复审");
		}else if(STATE_PASS.equals(state)){
			stateStr=("人工复审通过");
		}else if(STATE_REFUSED.equals(state)){
			stateStr=("人工复审不通过");
		}else if(STATE_REPAY.equals(state)){
			stateStr=("放款成功");
		}else if(STATE_REPAY_FAIL.equals(state)){
			stateStr=("放款失败");
		}else if(STATE_FINISH.equals(state)){
			stateStr=("还款成功");
//		}else if(STATE_REMISSION_FINISH.equals(state)){
//			stateStr=("还款成功-金额减免");
		}else if(STATE_REPAY_PROCESSING.equals(state)){
			stateStr=("还款中");
		}else if(STATE_DELAY.equals(state)){
			stateStr=("已逾期");
		}else if(STATE_BAD.equals(state)){
			stateStr=("已坏账");
//		}else if(WAIT_AUDIT_LOAN.equals(state)){
//			stateStr=("待放款审核");
//		}else if(AUDIT_LOAN_PASS.equals(state)){
//			stateStr=("放款审核通过");
//		}else if(AUDIT_LOAN_FAIL.equals(state)){
//			stateStr=("放款审核不通过");
		}
		return stateStr;
	}
	
}
