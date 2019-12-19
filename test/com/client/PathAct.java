package com.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tools.io.path.PathTool;


@Controller
public class PathAct {
    @RequestMapping("/pathTest")
    @ResponseBody
    public String  pathTest(HttpServletRequest request,String aa){
        System.out.println(  PathTool.getFullURL(request)   );
        System.out.println(  PathTool.getContextDiskPath(request)   );
        System.out.println(  PathTool.getBaseUrl(request,null)   );
//        PathTool.showAllParameter(request);
        return aa;
    }
}
