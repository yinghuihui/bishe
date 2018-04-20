package com.rongdu.ow.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.shiro.annotation.RequiresPermission;
import com.rongdu.ow.core.common.util.ListUtil;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.SysMenu;
import com.rongdu.ow.core.module.service.SysMenuService;
import com.rongdu.ow.core.module.service.SysRoleMenuService;

import tool.util.StringUtil;

@Scope("prototype")
@Controller
public class SysMenuController extends ManageBaseController {

	@Resource
	private SysMenuService sysMenuService;
	
	@Resource
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 登录用户 左侧菜单获取
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/modules/manage/system/roleMenu/find.htm")
	@RequiresPermission(code = "/modules/manage/system/roleMenu/find.htm", name = "根据角色查询菜单")
	public void findRoleMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleIds = (String)request.getSession().getAttribute(Constant.SESSION_ROLEIDS);
		List<Map<String, Object>> menus = new ArrayList<Map<String,Object>>();
		if(StringUtil.isNotBlank(roleIds)){
		menus = sysMenuService.listByRole(roleIds, "10",Integer.valueOf(0));
		menus = ListUtil.list2Tree(menus, "value", "parentId");
		menus = ListUtil.treeForExt(menus, null, null, true);
		}
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, menus);
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 角色管理-修改用户权限
	 * 
	 * @param request
	 * @param response
	 * @param menus
	 * @param roleId
	 * @throws Exception
	 */
	@RequestMapping("/modules/manage/system/roleMenu/update.htm")
    @RequiresPermission(code = "/modules/manage/system/roleMenu/update.htm", name = "角色管理-修改用户权限")
	public void updateRoleMenu(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menus") String menus,
			@RequestParam(value = "roleId") Integer roleId) throws Exception {
		List<Long> menuIds = JSONObject.parseArray(menus, Long.class);
		sysRoleMenuService.update(roleId, menuIds);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, res);
	}

	
	@RequestMapping("/modules/manage/system/menu/list.htm")
	@RequiresPermission(code = "/modules/manage/system/menu/list.htm",name = "菜单管理-查询所有菜单及其权限")
	public void findAllMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = sysMenuService.list(null,0);
		list = ListUtil.list2Tree(list, "value", "parentId");
		list = ListUtil.treeForExt(list, null, null, true);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, list);
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 新增菜单或者权限
	 * @param response
	 * @param sysMenu
	 * @throws Exception
	 */
	@RequestMapping("/modules/manage/system/menu/save.htm")
	@RequiresPermission(code = "/modules/manage/system/menu/save.htm",name = "菜单管理-新增菜单或者权限")
	public void save(HttpServletResponse response, @RequestBody SysMenu sysMenu) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String url = sysMenu.getUrl();
		String scriptid = sysMenu.getScriptid();
		boolean exist = sysMenuService.findByUrlOrScriptid(url, scriptid,null);
		if (!exist) {// 不存在
			boolean result = sysMenuService.save(sysMenu);
			if (result) {
				resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
			} else {
				resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				resultMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
			}
		}else{
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "参数存在");
		}
		ServletUtils.writeToResponse(response, resultMap);
	}
	
	/**修改菜单或权限
	 * @param response
	 * @param sysMenu
	 * @throws Exception
	 */
	@RequestMapping("/modules/manage/system/menu/update.htm")
	@RequiresPermission(code = "/modules/manage/system/menu/update.htm",name = "菜单管理-修改菜单或权限")
	public void update(HttpServletResponse response, @RequestBody SysMenu sysMenu) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String url = sysMenu.getUrl();
		String scriptid = sysMenu.getScriptid();
		boolean exist = sysMenuService.findByUrlOrScriptid(url, scriptid, sysMenu.getId());
		if (!exist) {// 不存在
			boolean result = sysMenuService.update(sysMenu);
			if (result) {
				resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				resultMap.put(Constant.RESPONSE_CODE_MSG, "修改成功");
			} else {
				resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				resultMap.put(Constant.RESPONSE_CODE_MSG, "修改失败");
			}
		}else{
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "参数存在");
		}
		ServletUtils.writeToResponse(response, resultMap);
	}
	
	
	/**
	 * 分配权限时得到当前用户权限
	 * 
	 * @param request
	 * @param response
	 * @param roleId
	 * @throws Exception
	 */
	@RequestMapping("/modules/manage/system/role/menu/list.htm")
	@RequiresPermission(code = "/modules/manage/system/role/menu/list.htm",name = "角色管理-分配权限时得到当前用户权限")
	public void findRoleMenuHas(HttpServletRequest request, HttpServletResponse response, Long roleId) throws Exception {
		List<Map<String, Object>> menus = sysMenuService.listAllByRole(roleId,"",0);
		menus = ListUtil.list2Tree(menus, "value", "parentId");
		menus = ListUtil.treeForExt(menus, null, null, true);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, menus);
		ServletUtils.writeToResponse(response, res);
	}

	
	@RequestMapping("/modules/manage/system/menu/delete.htm")
	@RequiresPermission(code = "/modules/manage/system/menu/delete.htm",name = "菜单管理-删除菜单")
	public void delete(HttpServletResponse response, @RequestParam(value = "id", required = false) Long id) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		sysMenuService.delete(id);
		resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		resultMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, resultMap);
	}


}
