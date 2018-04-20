package com.rongdu.ow.core.module.mapper;

import java.util.List;

import com.rongdu.ow.core.common.exception.PersistentDataException;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.SysRoleMenu;

/**
 * 
 * 角色菜单关系DAO
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月22日 下午2:48:38
 */
@RDBatisDao
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu,Long> {
	
	/**
	 * 删除角色菜单关联表信息（物理删除）
	 * @param roleId 角色ID
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年9月22日
	 */
	int deleteByRoleId(long roleId);
	
	/**
	 * 角色菜单关联信息查询 LIST
	 * @param roleId  角色ID
	 * @return 角色菜单关系列表
	 * @version 1.0
	 * @author 吴国成
	 * @throws PersistentDataException 
	 * @created 2014年9月22日
	 */
	List<SysRoleMenu> listRoleMenu(long roleId) throws PersistentDataException;
	
	int saveRoleMenu(long roleId,Long menuId);
	
}
