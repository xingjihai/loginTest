package com.main.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdbctemplate.manager.MemberMng;

/**
 * spring bean 测试类
 */
@Controller
public class SpringBeanTest {
    @Resource MemberMng MemberMngImpl;
    
    /**
     * 测试依赖注入是否为同一个bean  （结果：根据hashcode显示，为同一个bean）
     */
    @RequestMapping("/injectionTest")
    @ResponseBody
    public int injectionTest(){
        System.out.println(      MemberMngImpl.hashCode()     );
        return MemberMngImpl.hashCode();
    }
}
