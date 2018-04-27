package com.rongdu.ow.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.service.ClUserService;
@Controller
@Scope("prototype")
public class LoginController extends BaseController{
	
	@Resource
	private ClUserService clUserService;
	
	/**
	 * 首页
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/imgCode/generate.htm")
	public void home(HttpSession session) throws Exception {
		super.generateImgCode();
	}
	
//	@RequestMapping(value = "/api/user/pcSendSms.htm", method = RequestMethod.POST)
//	public void pcSendSms() throws IOException {	
//		String serverHost = Global.getValue("pc_server_host");
//		String originHeader=request.getHeader("Origin");
//		if (serverHost.contains(originHeader)){
//			response.setHeader("Access-Control-Allow-Origin", originHeader);
//			response.setHeader("Access-Control-Allow-Credentials", "true");
//			response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//			response.setHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
//		}
//		String code = request.getParameter("code");
//		String phone = request.getParameter("phone");
//		String type = request.getParameter("type");
//		long countDown = 0;
//		HttpSession session = request.getSession();
//		String sessionCode = (String) session.getAttribute("code");
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		String result = "10";
//		if (StringUtil.isNotBlank(code) && code.length() == 4 && code.equals(sessionCode)) {
//			result = this.check(phone, type);
//			if (result == null) { if (type.equals("register")) { countDown =
//					clSmsService.findTimeDifference(phone, type); if (countDown != 0)
//					{ result = "获取短信验证码过于频繁，请稍后再试"; } else { long msg =
//					clSmsService.sendSms(phone, type); if (msg == 1) { result = "10";
//					} else { result = "短信发送失败"; } }
//
//			} else { result = "短信类型错误"; } } resultMap.put("countDown",
//					countDown);
//
//			// 验证完成,删除session中图片验证码
//			session.removeAttribute("code");
//			if (result.equals("10")) {
//				resultMap.put(Constant.RESPONSE_CODE,
//						Constant.SUCCEED_CODE_VALUE);
//				resultMap.put(Constant.RESPONSE_CODE_MSG, "短信发送成功");
//			} else {
//				resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//				resultMap.put(Constant.RESPONSE_CODE_MSG, result);
//			}
//		} else {
//			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//			resultMap.put(Constant.RESPONSE_CODE_MSG, "图片验证码错误");
//		}
//		ServletUtils.writeToResponse(response, resultMap);
//	}

	/**
	 * PC注册
	 * @param request
	 * @param response
	 * @param loginName 登录名
	 * @param loginPwd 登录密码
	 * @param vcode 验证码
	 * @param blackBox 
	 */
	@RequestMapping(value = "/modules/user/pcRegister.htm", method = RequestMethod.POST)
	public void pcRegister(HttpServletRequest request) {
//		String serverHost = Global.getValue("register_ip");
//		String originHeader=request.getHeader("Origin");
//		if (serverHost.contains(originHeader)){
//			response.setHeader("Access-Control-Allow-Origin", originHeader);
//			response.setHeader("Access-Control-Allow-Credentials", "true");
//			response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//			response.setHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
//		}
		final String mobile = request.getParameter("mobile");
		final String password = request.getParameter("password");
		final String code = request.getParameter("code");
		System.out.println("code"+password.toUpperCase());
		Map result = null;
		result = clUserService.pcRegisterUser(request, mobile,
				password.toUpperCase(), code);
		if ((Boolean) result.get("success")) {
			result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "注册成功!");
		} else {
			result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, result.get("msg"));
		}
		ServletUtils.writeToResponse(response,result);
	}
//
//}
	@RequestMapping(value = "/module/user/login.htm", method = RequestMethod.POST)
	public void login(HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String, Object>();
//		result = userService.pcRegisterUser(request, mobile,
//				password.toUpperCase(), code, "", "","",
//				"", "pc", "","pc");
		request.getSession().setAttribute("user", "yinghh");
		request.getSession().setAttribute("isborrow", 0);
		request.getSession().setAttribute("isAuth", 0);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "登录成功!");
		ServletUtils.writeToResponse(response,result);
	}
	@RequestMapping(value = "/module/user/loginOut.htm", method = RequestMethod.GET)
	public void loginOut(HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String, Object>();
		request.getSession().removeAttribute("user"); 
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "注销成功!");
		ServletUtils.writeToResponse(response,result);
	}


	

}
