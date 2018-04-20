package com.rongdu.ow.core.module.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.exception.ServiceException;
import com.rongdu.ow.core.module.domain.SysRoleMenu;
import com.rongdu.ow.core.module.mapper.SysRoleMenuMapper;
import com.rongdu.ow.core.module.service.SysRoleMenuService;

@Service(value = "sysRoleMenuServiceImpl")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Resource
	private SysRoleMenuMapper sysRoleMenuMapper;

	public SysRoleMenuMapper getSysRoleMenuDao() {
		return sysRoleMenuMapper;
	}

	public void setSysRoleMenuDao(SysRoleMenuMapper sysRoleMenuDao) {
		this.sysRoleMenuMapper = sysRoleMenuDao;
	}

	@Override
	public boolean update(long roleId, List<Long> menuIds) throws ServiceException {
		int deleteResult = sysRoleMenuMapper.deleteByRoleId(roleId);
		boolean saveResult = true;
		for (Long menuId : menuIds) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setMenuId(menuId);
			roleMenu.setRoleId(roleId);
			int save = sysRoleMenuMapper.save(roleMenu);
			if(saveResult&& save==1){
				saveResult = false;
			}
		}
		return (deleteResult==1)&&saveResult;
	}


}
