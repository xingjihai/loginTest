package com.main.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.jdbc.Test;

@Controller
public class HelloContorller {
    
	@RequestMapping("/testQuery")
	@ResponseBody
	public void testQuery(){
		String countSql1="SELECT * FROM test";
		Test.testQuery(countSql1);
	}
    
    //测试路径：http://localhost:8080/spring_mvc/hello
    @RequestMapping(value="/hello")   //或者@RequestMapping("/hello") 
    public String hello(){
        System.out.println("this is @RequestMapping(hello) !");
        return "jsp/hello.jsp";
    }
    
    /*
     * 1.客户端->服务器端   get方式的参数传递-url
     * 2.uri的参数必须是跟对应的请求方法的参数一致(与struts不同)
     *   
     */
    //1    测试路径：http://localhost:8080/spring_mvc/ok?userid=1
    @RequestMapping(value="/ok")
    public String ok(@RequestParam("userid") int id){
        System.out.println("this is @RequestMapping(ok) ,the value is "+id);
        return "ok.jsp";     //返回为 ok.jsp页面
    }
    //2   测试路径：http://localhost:8080/spring_mvc/ok1?id=2
    @RequestMapping(value="/ok1")
    public String ok1(@RequestParam int id){
        System.out.println("this is @RequestMapping(ok1) ,the value is "+id);
        return "ok.jsp";
    }
    //3
    @RequestMapping(value="/ok2")
    public String ok2(int id,int age){
        System.out.println(id);
        System.out.println(age);
        return "ok";
    }
    /*
     * 服务器端的值到客户端的 参数传递   ok.jsp  的显示参数： ok! ${hello} ${string} ${string}
     */
    //1   测试路径：http://localhost:8080/spring_mvc/ok3  显示结果：ok! world2 world3 world3 
    @RequestMapping(value="/ok3")
    public String ok3(Map<String, Object> map){
        map.put("hello", "world");
        map.put("hello", "world2");
        map.put("string", "world3");
        return "ok";
    }
    //2-推荐使用     
    // 测试路径：http://localhost:8080/spring_mvc/ok4  显示结果：ok! world world3 world3 
    @RequestMapping(value="/ok4")
    public String ok4(Model model){
        model.addAttribute("hello","world");  //放入jsp页面  ${hello}标签中 
        model.addAttribute("world");//缺省值，相当于model.addAttribute("string","world"); 
        model.addAttribute("world2");
        model.addAttribute("world3");
        return "ok";
    }
    
    
    //获取req,resp  测试路径：http://localhost:8080/spring_mvc/req?user=wyj
    @RequestMapping(value="req")
    public String req(HttpServletRequest req,HttpServletResponse resp){
        System.out.println(req.getParameter("user"));
        return "ok";
    }
}
