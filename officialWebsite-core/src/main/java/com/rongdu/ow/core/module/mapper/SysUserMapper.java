package com.rongdu.ow.core.module.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.SysUser;


/**
 * 
 * 用户DAO
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月22日 下午2:53:15
 */
@RDBatisDao
public interface SysUserMapper extends BaseMapper<SysUser, Long>{

	/**
	 * 编辑用户登录信息
	 * 
	 * @param sysUser
	 * @return
	 */
	Boolean updateUserLoginInfo(SysUser sysUser);

	/**
	 * 编辑用户密码
	 * 
	 * @param sysUser
	 * @return
	 */
	Boolean updateUserPassWord(SysUser sysUser);

	/**
	 * 根据ID更新 通用更新
	 * 
	 * @param map
	 * @return Boolean
	 */
	int updateSysUserById(Map<String, Object> map);

	/**
	 * 根据机构获取客户专员
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> listCustomerServiceStaff(Map<String, Object> paramMap);

	int findRoleUserIsUse(Map<String, Object> data) ;

	SysUser findUserByUserName(String userName) ;

	int findPageCount(Map<String, Object> map);

	List<Map<String, Object>> listUserInfo(Map<String, Object> mapdata);

	SysUser findUser(Map<String, Object> paramMap);

	List<Map<String, Object>> listByUserInfo(Map<String, Object> params);

	int updateState(Map<String, Object> map);
	
	/**
	 * 角色查询系统用户列表
	 * @param map
	 * @return
	 */
	List<SysUser> findSysUserByNid(Map<String, Object> map);
}
