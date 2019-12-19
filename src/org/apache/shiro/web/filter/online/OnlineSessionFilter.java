/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.apache.shiro.web.filter.online;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.ShiroConstants;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.OnlineSession;
import org.apache.shiro.session.mgt.eis.OnlineSessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.google.common.net.HttpHeaders;
import com.notary.framework.common.web.context.ThreadContextHolder;
import com.notary.framework.entity.ShiroUser;
import com.notary.framework.entity.UserInfo;
import com.notary.framework.system.controller.common.AjaxResponseMessage;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-3-20 下午3:17
 * <p>Version: 1.0
 */
public class OnlineSessionFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(OnlineSessionFilter.class);
    /**
     * 强制退出后重定向的地址
     */
    private String forceLogoutUrl;

    private OnlineSessionDAO onlineSessionDAO;

    public String getForceLogoutUrl() {
        return forceLogoutUrl;
    }

    public void setForceLogoutUrl(String forceLogoutUrl) {
        this.forceLogoutUrl = forceLogoutUrl;
    }

    public void setOnlineSessionDAO(OnlineSessionDAO onlineSessionDAO) {
        this.onlineSessionDAO = onlineSessionDAO;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null) {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());
        if (session instanceof OnlineSession) {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);
            //把user id设置进去
            if (onlineSession.getUserId() == null) {
                ShiroUser user = UserInfo.getCurrentUser();
                if (user != null) {
                    onlineSession.setUserId(user.getUserId());
                    onlineSession.setUsername(user.getUserName());
                    onlineSession.markAttributeChanged();
                }
            }

            if (onlineSession.getStatus() == OnlineSession.OnlineStatus.force_logout) {
                request.setAttribute("force_logout", true);
                return false;
            }
        }
        // 存放request，实现 session anywhere
        ThreadContextHolder.remove();
        ThreadContextHolder.setHttpRequest((HttpServletRequest) request);
        return true;
    }

    /**
     * 修改多地登录，暂时不退出系统
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject != null) {
            subject.logout();
        }
        if(request.getAttribute("force_logout") != null && (Boolean)request.getAttribute("force_logout")){
            try{
                if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                        .getHeader(HttpHeaders.X_REQUESTED_WITH))) {
                    saveRequestAndRedirectToLogin(request, response);
                    return true;
                }
                ServletOutputStream  stream = response.getOutputStream();
                ((HttpServletResponse)response).setStatus(HttpStatus.I_AM_A_TEAPOT.value());
                response.setContentType("text/json;charset=utf-8");
                stream.println(AjaxResponseMessage.getFailed().setMessage("force_logout").toString());
                stream.close();
                return false;
            }catch(Exception e){
                logger.error(e.getMessage());
            }
        }else{
            saveRequestAndRedirectToLogin(request, response);
        }
        return true;
    }

    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, getForceLogoutUrl());
    }

}
