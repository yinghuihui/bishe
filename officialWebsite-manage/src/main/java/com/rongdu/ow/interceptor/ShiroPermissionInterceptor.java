package com.rongdu.ow.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rongdu.ow.core.common.shiro.annotation.RequiresPermission;


@Service
public class ShiroPermissionInterceptor extends HandlerInterceptorAdapter  {  
	  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
	 if (handler instanceof HandlerMethod) {  
    	HandlerMethod newhandler=(HandlerMethod) handler;  
    	RequiresPermission permAnnotation = newhandler.getMethodAnnotation(RequiresPermission.class);  
        boolean isPermission = false;  
        Subject currentUser = SecurityUtils.getSubject();    
         //没有获得注解  及不需要权限-- 则直接运行  
        if(null!=permAnnotation){  
            String permission = permAnnotation.code();  
            //当前登录人 具有权限  
            if(currentUser.isPermitted(permission)){  
            	isPermission = true;  
            }  
        }else{  
        	isPermission = true;  
        }  
        if(isPermission){  
            //有执行方法或权限不拦截  
            return true;  
        }else{  
            throw new AuthorizationException();  //无权限异常  
        }  
	 } else {
		 return super.preHandle(request, response, handler);
	 } 
    }  
}
