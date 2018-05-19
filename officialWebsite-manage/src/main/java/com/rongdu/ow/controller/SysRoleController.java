package com.rongdu.ow.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.exception.ServiceException;
import com.rongdu.ow.core.common.shiro.annotation.RequiresPermission;
import com.rongdu.ow.core.common.util.JsonUtil;
import com.rongdu.ow.core.common.util.RdPage;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.SysRole;
import com.rongdu.ow.core.module.domain.SysUser;
import com.rongdu.ow.core.module.service.SysRoleService;
import com.rongdu.ow.core.module.service.SysUserService;

import tool.util.StringUtil;

/**
 * 角色Action

 */
@Scope("prototype")
@Controller
public class SysRoleController extends ManageBaseController  {
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserService sysUserService;

	/**
	 * 获取所有角色列表
	 * @param request
	 * @param response
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年10月15日
	 */
	@RequestMapping(value = "/modules/manage/system/role/list.htm")
	@RequiresPermission(code = "/modules/manage/system/role/list.htm", name = "获取所有角色列表")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		List<SysRole> sysRoleList = sysRoleService.listAll();
		res.put(Constant.RESPONSE_DATA, sysRoleList);
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, res);
	}
	
	/**
	 * 角色删除（逻辑删除）
	 * @throws ServiceException
	 * @throws Exception
	 *             异常
	 */
	@RequestMapping(value = "/modules/manage/system/role/delete.htm")
	@RequiresPermission(code = "/modules/manage/system/role/delete.htm", name = "角色删除")
	public void roleDelete(@RequestParam("key") Long id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 判断用户是否还在使用这个角色
		Map<String, Object> res = new HashMap<String, Object>();
		if (id == null) {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "角色不能为空");
			ServletUtils.writeToResponse(response, res);
			return;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("roleId", id);
		param.put("delete", "0");
		int roleNum = sysUserService.queryRoleUserIsUse(param);
		if (roleNum >= 1) {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "角色有用户在使用，删除失败");
			ServletUtils.writeToResponse(response, res);
			return;
		}
		int result = sysRoleService.deleteRole(id);
		if(result > 0){
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else{
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping(value = "/modules/manage/system/sysUsers/page.htm")
	@RequiresPermission(code = "/modules/manage/system/sysUsers/page.htm", name = "获取系统用户列表")
	public void pageSysUsers(HttpServletResponse response,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception{
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> res = new HashMap<String, Object>();
		Page<SysRole> page = (Page<SysRole>) sysRoleService.getRolePageList(current,pageSize,data);
		res.put(Constant.RESPONSE_DATA, page);
		res.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, res);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/manage/system/role/save.htm")
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "form") String data,
			@RequestParam(value = "status") String status) throws Exception{
		HashMap<String, Object> dataMap = JsonUtil.parse(data, HashMap.class);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if ("create".equalsIgnoreCase(status)) {
			SysUser loginUser = this.getLoginUser(request);
			SysRole role = new SysRole();
			role.setAddTime(new Date());
			role.setAddUser(loginUser.getUserName());
			role.setUpdateTime(new Date());
			role.setUpdateUser(loginUser.getUserName());
			role.setName(dataMap.get("name") != null ? String.valueOf(dataMap.get("name")) :"");
			role.setNid(dataMap.get("nid") != null ? String.valueOf(dataMap.get("nid")) :"");
			role.setRemark(dataMap.get("remark") != null ? String.valueOf(dataMap.get("remark")) :"");
			int d=0;
			if(StringUtil.isNotBlank(dataMap.get("isDelete"))){
				d=(int) dataMap.get("isDelete");
			}
			role.setIsDelete((byte)d);
			long n = sysRoleService.addRole(role);
			if (n > 0) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
			}
		} else if ("update".equals(status)) {
			int total = sysRoleService.updateRole(dataMap);
			if (total > 0) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "更新成功");
			} else {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "更新失败");
			}
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 查询用户所属角色
	 */
	@RequestMapping(value = "/modules/manage/system/userRole/list.htm")
	@RequiresPermission(code = "/modules/manage/system/userRole/list.htm", name = "查询用户所属角色")
	public void listUserRoles(HttpServletResponse response,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpSession session
			)
			throws Exception {
		if (username != null && password != null) {
			
			List<Map<String, Object>> roles = sysRoleService
					.getByUserPassRolesList(username, password);

			Map<String, Object> rec = new HashMap<String, Object>();

			if (roles.size() > 0) {
				rec.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				rec.put(Constant.RESPONSE_CODE_MSG, roles);
				ServletUtils.writeToResponse(response, rec);
			} else {
				rec.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				rec.put(Constant.RESPONSE_CODE_MSG, "对不起，角色信息不对");
				ServletUtils.writeToResponse(response, rec);
			}
		}
	}

}
