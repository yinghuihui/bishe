package com.rongdu.ow.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Global;

/**
 * 
 * @author chenjianwei@erongdu.com
 * @version 1.0.0
 * @date 2017年8月10日 上午10:50:13 Copyright 杭州融都科技股份有限公司 金融创新事业部 此处填写项目英文简称 All
 *       Rights Reserved 官方网站：www.erongdu.com 未经授权不得进行修改、复制、出售及商业使用
 */

@Controller
public class PageController extends BaseController {

	/**
	 * 首页
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/home.htm")
	public String home(HttpSession session) throws Exception {
		return "/home";
	}

	/**
	 * 注册
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/User/register.htm")
	public ModelAndView register(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("/User/register");
		mav.addObject("registerIp",Global.getValue("register_ip"));
		return mav;
	}

	/**
	 * 新闻中心
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/AboutUs/newsCenter.htm")
	public String newCenter(HttpSession session) throws Exception {
		return "/AboutUs/newsCenter";
	}

	/**
	 * 关于我们
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/AboutUs/platform.htm")
	public ModelAndView platform(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("/AboutUs/platform");
		return mav;
	}

	/**
	 * 详情
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/AboutUs/detail.htm")
	public String detail(HttpSession session) throws Exception {
		return "/AboutUs/detail";
	}
	
	/**
	 * 联系我们
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/AboutUs/contactUs.htm")
	public String contactUs(HttpSession session) throws Exception {
		return "/AboutUs/contactUs";
	}
	
	/**
	 * 认证页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/User/credit.htm")
	public String credit(HttpSession session) throws Exception {
		return "/User/credit";
	}
	/**
	 * 借款首页
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/User/borrowIndex.htm")
	public String borrowIndex(HttpSession session) throws Exception {
		return "/User/borrowindex";
	}

}
