package com.shiroDemo;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class AdminAuthenticationFilter extends FormAuthenticationFilter{
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        return super.preHandle(request, response);
    }
}
