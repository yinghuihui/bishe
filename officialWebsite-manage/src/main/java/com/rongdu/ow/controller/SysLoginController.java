package com.rongdu.ow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.exception.ImgCodeException;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.SysUser;
import com.rongdu.ow.core.module.service.SysRoleService;
import com.rongdu.ow.core.module.service.SysUserRoleService;
import com.rongdu.ow.core.module.service.SysUserService;

/**
 * 
 * 登陆处理Action, 实际登陆处理交由Spring Security框架, 该Action的作用仅仅为辅助

 */
@Scope("prototype")
@Controller
public class SysLoginController extends ManageBaseController {

	private Logger logger = LoggerFactory.getLogger(SysLoginController.class);

	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysUserRoleService sysUserRoleService;

	@RequestMapping(value = "/system/user/login.htm", method = RequestMethod.POST)
	public void loginAjax(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "accessCode", required = false) String accessCode,
			HttpServletResponse response,
			HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			//shiro
			Subject user = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(true);
			user.login(token);
			SysUser sysUser = sysUserService.getUserByUserName(username);
			List<Long> roleIds = sysUserRoleService.getSysUserRoleList(sysUser.getId());
			//shiro
			session.setAttribute(Constant.ACCESSCODE,accessCode);
			session.setAttribute(Constant.SESSION_ROLEIDS, StringUtil.toString(roleIds));
			session.setAttribute(Constant.SESSION_SYSUSER, sysUser);
			res.put(Constant.RESPONSE_DATA, sysUser);
			
			//图片验证码校验
			checkImgCode(request.getParameter("code"),session.getAttribute("code"));
			
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "登录成功");
		} catch (IncorrectCredentialsException ex){
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "密码错误请重新输入");
		} catch (LockedAccountException ex) {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "该用户已锁定，请联系管理员！");
		} catch (ExpiredCredentialsException ex) {
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, ex.getMessage());
		}catch (UnknownAccountException ex){
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "账号不存在请核对后重新输入");
		} catch (AuthenticationException ex) {
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "登录失败");
		} catch (ImgCodeException ex) {
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "图片验证码错误");
		}
		
		ServletUtils.writeToResponse(response, res);

	}

	private void checkImgCode(String code, Object sessionCode) {
		if (StringUtil.isBlank(code)||code.length()!=4||!code.equals(sessionCode)) {
			throw new ImgCodeException("图片验证码错误");
		}
	}
	
	/**
	 * 生成图片验证码
	 * @param search
	 * @throws Exception
	 */
	@RequestMapping(value = "/system/user/imgCode/generate.htm")
	public void generate() throws Exception {
		super.generateImgCode();
	}
	
	
	@RequestMapping(value = "/system/user/logout.htm")
	public void logout(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
		Subject user = SecurityUtils.getSubject();
		user.logout();
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "注销成功");
		ServletUtils.writeToResponse(response, res);
	}
	
	
	@RequestMapping(value = "/system/session/invalid.htm")
	public void sessionInvalid(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SESSION_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "登陆超时");
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value = "/system/user/forceLogoutUrl.htm")
	public void forceLogoutUrl(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
		Subject user = SecurityUtils.getSubject();
		user.logout();
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.NO_LOGIN_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "请重新登陆");
		ServletUtils.writeToResponse(response, res);
	}
	
	/*@RequestMapping(value = "/system/user/logout.htm")
	public void logout(HttpServletResponse response) {
		Map<String, Object> res = new HashMap<String, Object>();
	@RequestMapping(value = "/system/user/logout.htm")
	public void logout() {
		session.removeAttribute("SysUser");
		session.removeAttribute(Constant.ROLEID);
		session.removeAttribute("SPRING_SECURITY_CONTEXT");
	    session.invalidate();
	    SecurityUtils.getSubject().logout();
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "成功");
		ServletUtils.writeToResponse(response, res);
	}*/
	
	
}
