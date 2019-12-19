package com.main.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.Person;

import net.sf.json.JSONObject;
import tools.mapper.MapperTool;

@Controller
@RequestMapping("/springmvc")
public class SpringMvcTest {
    @RequestMapping("/requestParamsTest")
    @ResponseBody
    public void requestParamsTest(String aa,HttpServletRequest request){
        System.out.println( request.getAttribute("aa") );
        System.out.println(  request.getParameter("aa") );
    }
    @RequestMapping("/postJsonPage")
    public String postJsonPage(String aa,HttpServletRequest request){
        return "WEB-INF/html/ajax";
    }
    
    /**
     * 当找不到路径时才会调起这个方法.
     * @param mappingValue
     * @param request
     */
    @RequestMapping("/{mappingValue}.do")
    public void mappingValue(@PathVariable("mappingValue") String mappingValue,@RequestBody JSONObject obj, HttpServletRequest request, HttpServletResponse response){
        System.out.println("mappingValue method is "+mappingValue);
        String message = null;
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            try {
                //TODO  可以对请求的参数 obj 做统一的处理!!!
//                Method method = this.getClass().getDeclaredMethod(mappingValue, new Class[] { JSONObject.class, HttpServletRequest.class, HttpServletResponse.class, Map.class });
                Method method = this.getClass().getDeclaredMethod(mappingValue, new Class[] { JSONObject.class, HttpServletRequest.class  });
                method.invoke(this, new Object[] { obj, request });
                return;
            } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
            }
        } catch (java.lang.NoSuchMethodException e) {
            message = "无此接口";
        } catch (IllegalAccessException e) {
            message = "调用了私有方法";
        } catch (IllegalArgumentException e) {
            message = "请求参数错误";
        } catch (InvocationTargetException e) {
            message = "反射异常";
        }
        System.out.println(message);
    }
    
    @RequestMapping("/invoke.do")
    @ResponseBody
    public void invoke(HttpServletRequest request){
        System.out.println("invoke");
    }
    
    /**
     * 客户端传入json字符串,服务端再进行解析
     * 注意:Content-Type:application/json; charset=UTF-8  才能传入
     * 参考:http://blog.csdn.net/mingtianhaiyouwo/article/details/51459764
     * @param obj
     * @param request
     */
    public void invoke2(JSONObject obj,HttpServletRequest request){
        Person student=MapperTool.fromJson(obj, Person.class);
        System.out.println("invoke2");
        System.out.println(student.getChild().getName());
    }
    
}
