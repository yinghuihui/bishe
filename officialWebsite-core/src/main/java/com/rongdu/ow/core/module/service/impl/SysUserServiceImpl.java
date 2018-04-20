package com.rongdu.ow.core.module.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.exception.ServiceException;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.SysUser;
import com.rongdu.ow.core.module.domain.SysUserRole;
import com.rongdu.ow.core.module.mapper.SysRoleMapper;
import com.rongdu.ow.core.module.mapper.SysUserMapper;
import com.rongdu.ow.core.module.mapper.SysUserRoleMapper;
import com.rongdu.ow.core.module.service.SysUserService;

@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysUserRoleMapper sysUserRoleMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;

	@Override
	public BaseMapper<SysUser, Long> getMapper() {
		return sysUserMapper;
	}

	@Override
	public Boolean editUserLoginInfo(SysUser sysUser) throws ServiceException {
		try {
			return sysUserMapper.updateUserLoginInfo(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public Boolean editUserPassWord(SysUser sysUser) throws ServiceException {
		try {
			return sysUserMapper.updateUserPassWord(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public SysUser getUserById(long id) throws ServiceException {
		try {
			return sysUserMapper.findByPrimary(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int userUpdate(SysUser sysUser) throws ServiceException {
		try {
			return this.sysUserMapper.update(sysUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public Page<Map<String, Object>> getUserPageList(int currentPage, int pageSize, Map<String, Object> mapdata)
			throws ServiceException {
		try {
			PageHelper.startPage(currentPage, pageSize);

			return (Page<Map<String, Object>>) sysUserMapper.listUserInfo(mapdata);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int getUserSum(Map<String, Object> map) throws ServiceException {
		try {
			return sysUserMapper.findPageCount(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public void addUser(SysUser sysUser, String roleIdArr) throws ServiceException {
		try {
			sysUser.setPassword(sysUser.getPassword());
			// 增加用户
			sysUserMapper.save(sysUser);
			String temp = roleIdArr.replaceAll("\\[", "").replaceAll("\\]", "");
			String[] roles = temp.split(",");
			for (int i = 0; i < roles.length; i++) {
				String role = roles[i].trim();
				// 增加用户角色关系
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(Long.parseLong(role));
				sysUserRole.setUserId(sysUser.getId());
				sysUserRoleMapper.save(sysUserRole);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean updateSysUserById(Map<String, Object> map) throws ServiceException {
		try {
			Boolean result = false;
			// 首先删除角色关系
			Long userId = Long.valueOf(String.valueOf(map.get("id")));
			sysUserRoleMapper.deleteByUserId(userId);
			String temp = String.valueOf(map.get("roleId")).replaceAll("\\[", "").replaceAll("\\]", "");
			String[] roles = temp.split(",");
			for (int i = 0; i < roles.length; i++) {
				String role = roles[i].trim();
				// 增加用户角色关系
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(Long.parseLong(role));
				sysUserRole.setUserId(userId);
				sysUserRoleMapper.save(sysUserRole);
			}
			int isU = sysUserMapper.updateSysUserById(map);
			if (isU > 0) {
				result = true;
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<Map<String, Object>> getCustomerServiceStaffList(Map<String, Object> paramMap) throws ServiceException {
		try {
			return sysUserMapper.listCustomerServiceStaff(paramMap);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public SysUser getUserByUserName(String userName) throws ServiceException {
		try {
			return sysUserMapper.findUserByUserName(userName);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public int queryRoleUserIsUse(Map<String, Object> data) throws ServiceException {
		try {
			return sysUserMapper.findRoleUserIsUse(data);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e, Constant.FAIL_CODE_VALUE);
		}
	}

	@Override
	public List<Map<String, Object>> getUserInfo(Map<String, Object> params) throws ServiceException {
		List<Map<String, Object>> usersInfo;
		try {
			usersInfo = sysUserMapper.listByUserInfo(params);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return usersInfo;
	}

	@Override
	public List<SysUser> findSysUserByNid(Map<String, Object> map) {
		return sysUserMapper.findSysUserByNid(map);
	}
}
