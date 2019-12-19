package com.sitemesh.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 网页布局和修饰的框架
 * 参考：http://www.cnblogs.com/luotaoyeah/p/3776879.html
 */
@Controller
public class SiteMeshAction {
    
    @RequestMapping("/sitemesh/test")
    public String test(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "decorate/be_decorated";
    }
    
    @RequestMapping("/getDate")
    @ResponseBody
    public Date test2(){
        Date date=new Date();
        return date;
    }
}
