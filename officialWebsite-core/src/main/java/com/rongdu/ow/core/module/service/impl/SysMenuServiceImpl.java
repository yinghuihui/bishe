package com.rongdu.ow.core.module.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.SysMenu;
import com.rongdu.ow.core.module.mapper.SysMenuMapper;
import com.rongdu.ow.core.module.service.SysMenuService;

@Service(value = "sysMenuServiceImpl")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu,Long> implements
		SysMenuService {

	@Resource
	private SysMenuMapper sysMenuMapper;

	@Override
	public BaseMapper<SysMenu, Long> getMapper() {
		return sysMenuMapper;
	}

	@Override
	public boolean save(SysMenu sysMenu) {
		sysMenu.setAddTime(new Date());
		int result = sysMenuMapper.save(sysMenu);
		return result == 1;
	}

	@Override
	public boolean update(SysMenu sysMenu) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id",sysMenu.getId());
		paramMap.put("name",sysMenu.getName());
		paramMap.put("parentId",sysMenu.getParentId());
		paramMap.put("url",sysMenu.getUrl());
		paramMap.put("iconCls",sysMenu.getIconCls());
		paramMap.put("isDelete",sysMenu.getIsDelete());
		paramMap.put("sort",sysMenu.getSort());
		paramMap.put("type",sysMenu.getType());
		paramMap.put("remark",sysMenu.getRemark());
		paramMap.put("scriptid",sysMenu.getScriptid());
		paramMap.put("updateTime",DateUtil.getNow());
		paramMap.put("updateUser",sysMenu.getUpdateUser());
		int result = sysMenuMapper.updateSelective(paramMap);
		return result == 1;
	}
	
	@Override
	public boolean findByUrlOrScriptid(String url, String scriptid,Long id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("url",url);
		paramMap.put("scriptid",scriptid);
		paramMap.put("id",id);
		List<SysMenu>  list = sysMenuMapper.findByUrlOrScriptid(paramMap);
		if (list.size()> 0 ) {
			return true;
		}else{
			return false;
		}
	}
	

	@Override
	public boolean delete(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("isDelete", 1);
		int result = sysMenuMapper.updateSelective(paramMap);
		return result == 1;
	}

	@Override
	public List<Map<String, Object>> listByRole(String roleIds, String type, Integer isDelete) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isDelete", isDelete);
		paramMap.put("roleIds", roleIds);
		paramMap.put("type", type);
		List<Map<String, Object>> menus = sysMenuMapper.listByRole(paramMap);
		return menus;
	}

	@Override
	public List<Map<String, Object>> listAllByRole(Long roleIds, String type, Integer isDelete) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isDelete", isDelete.toString());
		paramMap.put("roleIds", roleIds);
		paramMap.put("type", type);
		List<Map<String, Object>> menus = sysMenuMapper.listAllByRole(paramMap);
		return menus;
	}

	@Override
	public List<Map<String, Object>> list(String type, Integer isDelete) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isDelete", isDelete);
		paramMap.put("type", type);
		List<Map<String, Object>> menus = sysMenuMapper.listByRole(paramMap);
		return menus;
	}

	@Override
	public List<SysMenu> permByUserName(String userName) {
		return sysMenuMapper.permByUserName(userName);
	}
}