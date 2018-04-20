package com.rongdu.ow.core.module.mapper;

import java.util.List;
import java.util.Map;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.mapper.RDBatisDao;
import com.rongdu.ow.core.module.domain.SysRole;

/**
 * 
 * 角色DAO
 * 
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月22日 下午2:51:25
 */
@RDBatisDao
public interface SysRoleMapper extends BaseMapper<SysRole, Long> {

	List<SysRole> listByUserId(Long userId);

	int deleteById(long id);

	List<Map<String, Object>> listByUserPassRoles(Map<String, Object> data);

	long saveByMap(Map<String, Object> data);

	List<SysRole> listAll();

	SysRole findNid(Map<String, Object> roleMap);
	
}
