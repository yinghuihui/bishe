package com.rongdu.ow.core.module.service;

import java.util.List;

import com.rongdu.ow.core.common.exception.ServiceException;


/**
 * 
 * 角色菜单关联信息service接口
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午9:55:37
 */
public interface SysRoleMenuService {
	
	/**
	 * 修改角色对应菜单
	 * @param roleId
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	boolean update(long roleId,List<Long> list) throws ServiceException;
	
}
