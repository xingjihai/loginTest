package com.freemarker;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiroDemo.User;

@Controller
@RequestMapping("freemarkerTest")
public class FreemarkerTest {
    
    @RequestMapping("getPage")
    public String getPage(Model model){
        User user=new User();
        user.setUsername("小明");
        user.setPassword("123456");
        model.addAttribute("user", user);
        model.addAttribute("hello", "hello world!!  世界好222");
        model.addAttribute("number", 456454.554);
        model.addAttribute("date", new Date());
        model.addAttribute("timestamp", new Date().getTime());
        return "hello";
    }
    
    @RequestMapping("getUser")
    @ResponseBody
    public User getUser(Model model){
        User user=new User();
        user.setUsername("小明");
        user.setPassword("123456");
        return user;
    }
}
