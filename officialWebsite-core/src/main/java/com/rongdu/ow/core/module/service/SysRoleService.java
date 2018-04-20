package com.rongdu.ow.core.module.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.exception.BussinessException;
import com.rongdu.ow.core.common.exception.ServiceException;
import com.rongdu.ow.core.module.domain.SysRole;

/**
 * 
 * 系统角色服务类
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午9:55:16
 */
public interface SysRoleService {

	/**
	 * 查询查询用户所拥有的角色
	 * @param userId
	 *            用户ID
	 * @return 角色List
	 */
	List<SysRole> getRoleListByUserId(long userId) throws ServiceException;

	/**
	 * 查询角色
	 * @param id
	 *            主键ID
	 * @return 角色
	 */
	SysRole getRoleById(long id)throws ServiceException ;

	Page<SysRole> getRolePageList(int currentPage,int pageSize,Map<String, Object> mapdata) throws Exception;
	/**
	 * 角色删除
	 * @param id
	 *            主键ID
	 */
	int deleteRole(long id)throws ServiceException;
	
	long addRole(SysRole role)throws BussinessException;
	
	long insertByMap(Map<String, Object> data) throws Exception;
	
	int  updateRole(Map<String, Object> arg) throws BussinessException;
	
	List<Map<String,Object>> getByUserPassRolesList(String username,String password) throws ServiceException;

	List<SysRole> listSelective(Map<String, Object> param);

	List<SysRole> listAll();

	List<SysRole> getList(Map<String, Object> param);
}
