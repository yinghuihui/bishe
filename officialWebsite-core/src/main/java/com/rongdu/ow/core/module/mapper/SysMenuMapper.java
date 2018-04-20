package com.rongdu.ow.core.module.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.SysMenu;

/**
 * 
 * 系统菜单DAO
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月22日 下午2:57:58
 */
@RDBatisDao
public interface SysMenuMapper extends BaseMapper<SysMenu,Long>{

	/**
	 * 查找角色对应的菜单或者权限
	 * roleIds 角色
	 * type 菜单或是权限
	 * @return
	 */
	List<Map<String, Object>> listByRole(Map<String, Object> paramMap);
	
	/**
	 * 查询所有菜单 
	 * 用户已拥有用 checked 标识
	 * roleIds 角色
	 * type 菜单或是权限
	 * @return
	 */
	List<Map<String, Object>> listAllByRole(Map<String, Object> paramMap);
	
	/**
	 * 根据用户名获取用户权限
	 * @param userName
	 * @return
	 */
	List<SysMenu> permByUserName(String userName);

	/**
	 *  查询url 时候存在
	 * @param paramMap
	 * @return
	 */
	List<SysMenu> findByUrlOrScriptid(Map<String, Object> paramMap);
	
}
