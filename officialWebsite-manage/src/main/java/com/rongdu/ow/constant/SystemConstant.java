package com.rongdu.ow.constant;

public class SystemConstant {

	/*********************** 菜单：是否是菜单 ****************************************/
	/** 不是菜单 */
	public static final byte MENU_NO = 0;
	/** 是菜单 */
	public static final byte MENU_IS = 1;
	/** 所有菜单 */
	public static final byte MENU_ALL = -1;


	/*********************** 用户状态 ****************************************/
	/** 用户状态：正常状态 */
	public static final byte USER_STATUS_NORMAL = 0;


	/*********************** security用户组处理常量类 ****************************************/
	/** system用户登录名 */
	public static final String SYSTEM_LOGIN_NAME = "mlms";
	/** system用户初始化密码 */
	public static final String SYSTEM_PASSWORD_DEFAULT = "123456";
	
	/**
	 * 加密salt值
	 */
	public static final String SALT = "rongdumlms";

	/** 默认角色 */
	public static final String ROLE_DEFAULT = "ROLE_DEFAULT";
	
	
	/**小视配置*/
	public static final String TEST_XIAOSHI = "verify_type";
	
	public static final String LINKFACEHOST_REAL = "linkfacehost_real";
	public static final String OCR_HOST = "ocr_host";
	public static final String VERIFY_HOST = "verify_host";
	

	/**
	 * 状态 - 启用
	 */
	public static final String STATE_ENALBE = "10";
	
	/**
	 * 状态 - 禁用
	 */
	public static final String STATE_DISABLE = "20";
	

	/** 检测配置 */
	public static final String TEST_CONFIGURATION = "xxxxx";
	
	
	/**
	 * 场景  - 运营商认证
	 */
	public static final String SCENE_OPERATOR_CERT = "operator_cert";
	
	/**
	 * 场景  - 联系人认证
	 */
	public static final String SCENE_CONTACTS_CERT = "contacts_cert";

	/**
	 * 场景  - OCR认证
	 */
	public static final String SCENE_OCR_CERT = "ocr_cert";
	
	/**
	 * 场景  - 基本信息认证
	 */
	public static final String SCENE_BASE_INFO_CERT = "base_info_cert";
	
	/**
	 * 场景  - 银行卡认证
	 */
	public static final String SCENE_BANK_CERT = "bank_cert";
	
	/**
	 * 场景 - 借款申请
	 */
	public static final String SCENE_BORROW_APPLY = "borrow_apply";
	
	
}
