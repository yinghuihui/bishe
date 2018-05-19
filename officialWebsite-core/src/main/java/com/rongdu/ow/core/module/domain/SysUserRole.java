package com.rongdu.ow.core.module.domain;

import java.io.Serializable;

/**
 * 
 * 用户角色关联表
 */
public class SysUserRole implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	/**
	 * 角色主键
	 */

	private Long roleId;
	/**
	 * 用户主键
	 */
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	

}
