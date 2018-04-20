package com.rongdu.ow.core.common.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import com.rongdu.ow.core.common.context.Constant;

/**
 * 判断session是否失效或不存在
 * @author yhjiang
 * @version 1.0
 * @date 2017年4月15日
 * Copyright 杭州融都科技股份有限公司 arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class SessionFilter implements Filter {

    /**
     * session失效重定向的地址
     */
    private String invalidUrl;
    
	public String getInvalidUrl() {
		return invalidUrl;
	}

	public void setInvalidUrl(String invalidUrl) {
		this.invalidUrl = invalidUrl;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)throws IOException, ServletException {
		invalidUrl = "/system/session/invalid.htm";
		Subject subject = SecurityUtils.getSubject();
		if (subject == null) {
			WebUtils.issueRedirect(request, response, invalidUrl);
			return;
		}
		
		Session session= subject.getSession();
		if (session == null) {
			WebUtils.issueRedirect(request, response, invalidUrl);
			return;
		}
		try {
			Object o = session.getAttribute(Constant.SESSION_SYSUSER);
			if (o == null) {
				WebUtils.issueRedirect(request, response, invalidUrl);
				return;
			}
		} catch (Exception e) {
			WebUtils.issueRedirect(request, response, invalidUrl);
			return;
		}
		
		try {  
			filterChain.doFilter(request, response);
		} catch (ExpiredCredentialsException e) {//超时
			WebUtils.issueRedirect(request, response, invalidUrl);
		} catch (UnknownSessionException e) {//不存在
			WebUtils.issueRedirect(request, response, invalidUrl);
		}
	}

	@Override
	public void destroy() {
	}


}