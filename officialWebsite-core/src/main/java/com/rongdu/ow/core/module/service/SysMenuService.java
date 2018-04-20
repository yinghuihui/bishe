package com.rongdu.ow.core.module.service;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.module.domain.SysMenu;

/**
 * 
 * 菜单服务类
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 上午9:54:35
 */
public interface SysMenuService {

	/**
	 * 修改菜单
	 * @param sysMenu
	 * @return
	 */
	boolean update(SysMenu sysMenu);
	
	/**
	 * 新增菜单
	 * @param sysMenu
	 * @return
	 */
	boolean save(SysMenu sysMenu);
	
	/**逻辑删除
	 * @param id
	 * @return
	 */
	boolean delete(Long id);


	/**
	 * 查找角色对应的菜单或者权限
	 * roleIds 角色
	 * type 菜单或权限 
	 * @return
	 */
	List<Map<String, Object>> listByRole(String roleIds, String type, Integer isDelete);

	/**
	 * 查询所有菜单及权限
	 * 用户已拥有用 checked 标识
	 * roleIds 角色
	 * type 菜单或是权限
	 * @return
	 */
	List<Map<String, Object>> listAllByRole(Long roleId, String type, Integer isDelete);

	/**
	 * 查询所有菜单
	 * @return
	 */
	List<Map<String, Object>> list(String type, Integer isDelete);

	/**
	 * 根据url, scriptid查询是否存在
	 * @param url
	 * @return
	 */
	boolean findByUrlOrScriptid(String url,String scriptid,Long id);

	/**根据用户名查询用户权限
	 * 
	 * @param userName
	 * @return
	 */
	List<SysMenu> permByUserName(String userName);

}
