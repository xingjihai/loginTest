package com.properties.client;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.properties.PropertyUtils;

@Controller
public class ClientApply {
    @Resource PropertyUtils propertys;
    
    @RequestMapping("getProperty")
    @ResponseBody
    public void getProperty(){
        String statics=propertys.getPropertiesString("statics");
        String name=propertys.getPropertiesString("student.name");
        System.out.println(statics);
        System.out.println(name);
    }
}
