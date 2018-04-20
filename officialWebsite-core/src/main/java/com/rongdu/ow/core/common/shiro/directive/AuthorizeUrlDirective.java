package com.rongdu.ow.core.common.shiro.directive;

import java.io.IOException;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import tool.util.StringUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 扩展自freemarker TemplateDirectiveModel类, 用于对判断当前登录用户是否被授权访问传入的URL, 如果<br>
 * 允许访问, 则将指令之间的内容输出, 反之不输出<br>
 * 
 * 注意: 该指令不能用于
 * 
 * 用户未登录的页面, 否则会抛出异常
 * @author zhangyz
 */
public class AuthorizeUrlDirective implements TemplateDirectiveModel {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] temp, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if (params.isEmpty()) {
			throw new TemplateModelException("This directive doesn't allow parameters.");
		}

		if (body != null) {
			boolean b = false;
			String url = params.get("value").toString();
			if (StringUtil.isNotBlank(url)) {
				Subject currentUser = SecurityUtils.getSubject();   
				if (currentUser.isPermitted(url)) {
					b = true;
				}
			}
			if (b) {
				body.render(env.getOut());
			}
		} else {
			throw new NullPointerException("missing body");
		}
	}
}
