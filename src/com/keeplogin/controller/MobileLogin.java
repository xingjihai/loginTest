package com.keeplogin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keeplogin.MobileLoginInterceptor;
import com.keeplogin.MobileLoginInterceptorAbstract.NonLogin;

@Controller
@RequestMapping("mobile")
public class MobileLogin {
    @RequestMapping("/login")
    @ResponseBody
    @NonLogin
    public String login(HttpServletRequest request,HttpServletResponse response){
        MobileLoginInterceptor.passwordLogin("用户名","用户密码",request, response);
        return "login success";
    }
    @RequestMapping("apply")
    @ResponseBody
    public String apply(HttpServletRequest request){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr=sdf.format(date);
        if(MobileLoginInterceptor.hasLogin(request)){
            return dateStr+":  has login";
        }
        
        return dateStr+": not login";
    }
}
