package com.rongdu.ow.core.common.context;



/**
 * 公用常量类定义
 * 
 * @version 1.0
 * @author 吴国成f
 * @created 2014年9月23日 下午2:17:20
 */
public class Constant {

	public static final String SESSION_ROLEIDS = "roleIds";

	public static final String SESSION_SYSUSER = "SysUser";

	public static final String ACCESSCODE = "accessCode";
	
	public static final String ROLE_CUSTOMER_TRIAL = "icloud_trial";

	public static final String ROLE_CUSTOMER_REVIEW = "icloud_review";
	
	public static final String ROLE_AGENT = "agent";
	
	public static final String ROLE_AGENT_TWO = "agent_two";
	

	/** 操作类型 */
	public static final String INSERT = "create";

	public static final String UPDATE = "update";
	
	
	/** 返回key */
	public static final String RESPONSE_CODE = "code";

	public static final String RESPONSE_CODE_MSG = "msg";

	public static final String RESPONSE_DATA = "data";

	public static final String RESPONSE_DATA_TOTAL = "total";

	public static final String RESPONSE_DATA_TOTALCOUNT = "totalCount";

	public static final String RESPONSE_DATA_CURRENTPAGE = "currentPage";

	public static final String RESPONSE_DATA_PAGE = "page";

	
	/** 返回消息 */
	public static final String OPERATION_SUCCESS = "操作成功";

	public static final String OPERATION_FAIL = "操作失败";
	
	
	/** 返回状态码 */
	public static final int SIGN_FAIL = 99; // 验签失败

	public static final int SUCCEED_CODE_VALUE = 200; // 成功 插入 、删除 更新 修改

	public static final int FAIL_CODE_PARAM_INSUFFICIENT = 300; // 参数列表不完整

	public static final int FAIL_CODE_VALUE = 400; // 失败 插入 、删除 更新 修改

	public static final int FAIL_CODE_PWD = 401; // 交易密码错误

	public static final int PERM_CODE_VALUE = 403; // 无权限访问

	public static final int OTHER_CODE_VALUE = 500; // 其他异常

	public static final int SESSION_CODE_VALUE = 600; // 连接超时

	public static final int NO_LOGIN_CODE_VALUE = 601; // 未登陆

	public static final int NOSESSION_CODE_VALUE = 800; // session失效

	public static final int CLIENT_EXCEPTION_CODE_VALUE = 998; // 连接异常（除请求超时）

	public static final int TIMEOUT_CODE_VALUE = 999; // 请求超时
	
}
