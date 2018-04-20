package com.rongdu.ow.core.module.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.exception.BussinessException;
import com.rongdu.ow.core.common.exception.ServiceException;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.common.util.PasswordHelper;
import com.rongdu.ow.core.module.domain.SysRole;
import com.rongdu.ow.core.module.mapper.SysRoleMapper;
import com.rongdu.ow.core.module.service.SysRoleService;

@SuppressWarnings("rawtypes")
@Service(value = "sysRoleServiceImpl")
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {
	@Resource
	private SysRoleMapper sysRoleMapper;

	@Override
	public List<SysRole> getRoleListByUserId(long userId) throws ServiceException {
		try {
			return this.sysRoleMapper.listByUserId(userId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public SysRole getRoleById(long id) throws ServiceException {
		try {
			return this.sysRoleMapper.findByPrimary(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int deleteRole(long id) throws ServiceException {
		try {
			return this.sysRoleMapper.deleteById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public Page<SysRole> getRolePageList(int currentPage, int pageSize, Map<String, Object> mapdata)
			throws ServiceException {
		try {
			PageHelper.startPage(currentPage, pageSize);
			return (Page<SysRole>) sysRoleMapper.listSelective(mapdata);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public long addRole(SysRole role) throws BussinessException {
		try {
			return this.sysRoleMapper.save(role);
		} catch (Exception e) {
			throw new BussinessException("保存失败,参数有误或角色名称或唯一标识已存在");
		}
	}

	@Override
	public long insertByMap(Map<String, Object> data) throws ServiceException {
		try {
			return this.sysRoleMapper.saveByMap(data);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int updateRole(Map<String, Object> arg) throws BussinessException {
		try {
			return this.sysRoleMapper.updateSelective(arg);
		} catch (Exception e) {
			throw new BussinessException("保存失败,参数有误或唯一标识已存在");
		}
	}

	@Override
	public List<Map<String, Object>> getByUserPassRolesList(String username, String password) throws ServiceException {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("username", username);
		data.put("password", PasswordHelper.encryptPassword("rongdumlms", password));
		try {
			List<Map<String, Object>> roles = sysRoleMapper.listByUserPassRoles(data);

			if (roles == null) {
				throw new ServiceException("获取角色数据失败", 400);
			}
			return roles;

		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public BaseMapper<SysRole, Long> getMapper() {
		return sysRoleMapper;
	}

	@Override
	public List<SysRole> listSelective(Map<String, Object> param) {
		return sysRoleMapper.listSelective(param);
	}

	@Override
	public List<SysRole> listAll() {
		return sysRoleMapper.listAll();
	}

	@Override
	public List<SysRole> getList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysRoleMapper.listSelective(param);
	}
}
