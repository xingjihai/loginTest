package com.shiroDemo;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.UserFilter;

import com.context.ThreadContextHolder;

public class AdminUserFilter extends UserFilter{
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        
        return true;
//        return super.preHandle(request, response);
    }
}
