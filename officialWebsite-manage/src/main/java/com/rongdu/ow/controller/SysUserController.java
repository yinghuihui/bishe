package com.rongdu.ow.controller;

import java.util.Arrays;
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
import com.rongdu.ow.core.common.shiro.annotation.RequiresPermission;
import com.rongdu.ow.core.common.util.JsonUtil;
import com.rongdu.ow.core.common.util.PasswordHelper;
import com.rongdu.ow.core.common.util.RdPage;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.SysUser;
import com.rongdu.ow.core.module.service.SysRoleService;
import com.rongdu.ow.core.module.service.SysUserService;
import com.rongdu.ow.constant.SystemConstant;


/**
 * 用户action
 * @version 1.0
 * @author 吴国成
 * @created 2014年9月23日 下午1:53:06
 */
@Scope("prototype")
@Controller
public class SysUserController extends ManageBaseController  {
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/manage/system/user/password/update.htm")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> userMap = JsonUtil.parse(user, Map.class);
		String oldPassword = String.valueOf(userMap.get("oldPassword"));
		String newPassword = String.valueOf(userMap.get("newPassword"));
		String newPassword2 =String.valueOf(userMap.get("newPassword2"));
		SysUser sysUser = this.getLoginUser(request);
		oldPassword = PasswordHelper.encryptPassword(SystemConstant.SALT, oldPassword);
		newPassword2 = PasswordHelper.encryptPassword(SystemConstant.SALT, newPassword2);
		newPassword = PasswordHelper.encryptPassword(SystemConstant.SALT, newPassword);
		if (!sysUser.getPassword().equals(oldPassword)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "原密码输入不正确");
		} else if (!newPassword.equals(newPassword2)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "两个新密码不一致");
		} else if (oldPassword.equals(newPassword)) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "新密码不能和旧密码相同");
		} else {
			sysUser.setPassword(newPassword);// 密码加密
			sysUserService.editUserPassWord(sysUser);
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "密码修改成功");
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 增加用户
	 * @param request
	 * @param response
	 * @param user
	 * @param status
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/manage/system/user/save.htm")
	@RequiresPermission(code = "/modules/manage/system/user/save.htm",name = "增加用户")
	public void save(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "status", required = false) String status) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> userMap = JsonUtil.parse(user, Map.class);
		// 获取当前登录用户信息
		SysUser userinfo = this.getLoginUser(request);
		String loginUserName = userinfo.getName();
		Date curDate = new Date();
		String officeId = String.valueOf(userMap.get("officeId"));
		if ("create".equalsIgnoreCase(status)) {// 新建
			SysUser sysUser = new SysUser();
			sysUser.setName(String.valueOf(userMap.get("name")));// 真实姓名
			sysUser.setNumber(String.valueOf(userMap.get("number")));// 工编号
			// 用户名验证
			String userName = String.valueOf(userMap.get("userName"));
			SysUser user2 = sysUserService.getUserByUserName(userName);
			if (null != user2) {
				responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "用户名已存在，不能重复注册!");
			} else {
				sysUser.setUserName(userName); // 登录名
				sysUser.setOfficeId(officeId);// 所属组织
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("parentId", 0);
				String email = String.valueOf(userMap.get("email"));
				if (StringUtil.isMail(email)) {
					sysUser.setEmail(email);
				}
				if(userMap.get("phone") != null){
					sysUser.setPhone(String.valueOf(userMap.get("phone")));
				}
				if(userMap.get("remark") != null){
					sysUser.setRemark(String.valueOf(userMap.get("remark")));
				}
				String mobile = String.valueOf(userMap.get("mobile"));
				if (StringUtil.isPhone(mobile)) {
					sysUser.setMobile(mobile);
				}
				sysUser.setAddTime(curDate);
				sysUser.setAddUser(loginUserName);
				sysUser.setUpdateTime(curDate);
				sysUser.setUpdateUser(loginUserName);
				sysUser.setSalt(SystemConstant.SALT);
				String encryptPassword = PasswordHelper.encryptPassword(SystemConstant.SALT, SystemConstant.SYSTEM_PASSWORD_DEFAULT);
				sysUser.setPassword(encryptPassword); // 账号初始密码
				sysUser.setStatus(SystemConstant.USER_STATUS_NORMAL); // 用户状态：正常
				if (StringUtil.isNotBlank((String)userMap.get("officeOver"))) {
					sysUser.setOfficeOver(String.valueOf(userMap.get("officeOver")));
				}
				if (StringUtil.isNotBlank((String)userMap.get("position"))) {
					sysUser.setPosition(Byte.valueOf(String.valueOf(userMap.get("position"))));
				}
				sysUserService.addUser(sysUser, String.valueOf(userMap.get("roleId")));// 增加用户
				responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
			}
		} else if ("update".equalsIgnoreCase(status)) {// 更新
			// 用户名验证
			String userName = String.valueOf(userMap.get("userName"));
			SysUser user2 = sysUserService.getUserByUserName(userName);
			Long id = Long.valueOf((Integer) userMap.get("id"));
			SysUser oldUser = sysUserService.getUserById(id);
			if (null != user2&&!oldUser.getUserName().equals(userName)) {
				responseMap.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
				responseMap.put(Constant.RESPONSE_CODE_MSG, "用户名已存在，不能重复注册!");
			} else {
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("parentId", 0);
				if (!StringUtil.isBlank((String) userMap.get("officeOver"))) {
					userMap.put("officeOver", String.valueOf(userMap.get("officeOver")));
				}
				if (StringUtil.isBlank(String.valueOf(userMap.get("position")))) {
					userMap.put("position", 0);
				}
				SysUser updateUser = getLoginUser(request);
				userMap.put("updateUser", updateUser.getUserName());
				boolean istrue = sysUserService.updateSysUserById(userMap);// 更新用户信息及对应的角色信息
				if (istrue) {
					responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
				}
			}
		} else {
			responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	/**
	 * 用户修改 -- 锁定及解锁 密码重置
	 * @param request
	 * @param response
	 * @param status
	 */
	@RequestMapping("/modules/manage/system/user/update.htm")
//	@RequiresPermission(code = "/modules/manage/system/user/update.htm",name = "用户修改")
	public void update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids[]", required = false) String ids[],
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "password",required = false) String password) throws Exception{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		int successcount = 0;
		for (String id : ids) {
			long userid = Long.parseLong(id);
			SysUser sysUser = sysUserService.getUserById(userid);
			if(sysUser != null){
				if ("lock".equals(status)) {
					sysUser.setStatus((byte)1);
				} else if ("unlock".equals(status)) {
					sysUser.setStatus((byte)0);
				} else if ("editpassword".equals(status)) {
					//String newPassword = PasswordHelper.encryptPassword(SystemConstant.SALT, SystemConstant.SYSTEM_PASSWORD_DEFAULT);
					String newPassword = PasswordHelper.encryptPassword(SystemConstant.SALT, password);
					sysUser.setPassword(newPassword);
				}
				sysUser.setUpdateTime(new Date());
				int count = sysUserService.userUpdate(sysUser);
				if(count > 0){
					successcount ++;
				}
			}
		}
		if (successcount == ids.length) {
			responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "修改成功");
		} else {
			responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			responseMap.put(Constant.RESPONSE_CODE_MSG, "修改失败");
		}
		ServletUtils.writeToResponse(response, responseMap);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/modules/manage/system/user/list.htm")
	@RequiresPermission(code = "/modules/manage/system/user/list.htm",name = "获取用户列表")
	public void getSysUserList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> reposedata = new HashMap<String, Object>();
		Map<String, Object> paramap = JsonUtil.parse(user, Map.class);
		data.put("number", null == user ? null : paramap.get("number"));
		data.put("name", null == user ? null : paramap.get("name"));
		data.put("status", null == user ? null : paramap.get("status"));
		data.put("roleId", null == user ? null : paramap.get("roleId"));
		Page<Map<String, Object>> pages = sysUserService.getUserPageList(current,pageSize,data);
		reposedata.put(Constant.RESPONSE_DATA, pages);
		reposedata.put(Constant.RESPONSE_DATA_PAGE, new RdPage(pages));
		reposedata.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		reposedata.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, reposedata);
	}
	
	
	/**
	 * @description 根据角色Id查找用户
	 * @param response
	 * @param request
	 * @param session
	 * @param roleId
	 * @throws Exception
	 * @author fzc
	 * @return void
	 * @since  1.0.0
	 */
	@RequestMapping("/modules/manage/system/user/info/find.htm")
	@RequiresPermission(code = "/modules/manage/system/user/info/find.htm",name = "查找用户")
	public void findUserInfo(
			HttpServletResponse response,
			HttpServletRequest request, 
			HttpSession session,
			@RequestParam(value = "roleName") String roleName)throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleName", roleName);
		
		String officeOver = getLoginUser(request).getOfficeOver();
		params.put("officeOver", Arrays.asList(officeOver.split(",")));
		
		List<Map<String,Object>> users = sysUserService.getUserInfo(params);
		
		responseMap.put("data", users);
		responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		responseMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		ServletUtils.writeToResponse(response, responseMap);
	}
}