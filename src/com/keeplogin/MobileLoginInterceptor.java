package com.keeplogin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 需重写的类
 * @author Administrator
 *
 */
public class MobileLoginInterceptor extends MobileLoginInterceptorAbstract{
    
    @Override
    boolean sessionHasLogin(HttpServletRequest request) {  //TODO
        return hasLogin(request); 
    }

    @Override
    boolean sessionDoLogin( String username, String token,HttpServletRequest request,HttpServletResponse response) {  //TODO
        return tokenLogin(username,token,request,response);  //需调用登录方法或直接存进session和Cookie,这里只做模拟
    }

    @Override 
    void noLoginReturnHandle(HttpServletResponse response) {  //TODO
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("未登录");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  /**模拟service层的逻辑：
   * 实际使用中不应该放这，应该放service层
   * @param request
   * @return
   */
    public static String crr_member="crr_member";
    public static String username="登录名";
    public static   Map<String, String> userMap = new HashMap<String, String>(); //模拟数据库中的登录标识,使用token能保证唯一账号登录（session过期之后）
    public static boolean hasLogin(HttpServletRequest request){  //实际使用中不应该放这，应该放service层
        if( request.getSession().getAttribute(crr_member) !=null )
            return true;
        return false;
    }
    /**
     * 使用token登录
     */
    public static boolean tokenLogin(String username, String token,HttpServletRequest request,HttpServletResponse response){
        if(  userMap.get(username)!=null&&userMap.get(username).equals(token) ){  //登录标识正确
            afterLogin(username, token, request, response);
            return true;
        }else{
            return false;
        }
    }
    /**
     * 使用账号密码登录
     */
    public static boolean passwordLogin(String username, String password,HttpServletRequest request,HttpServletResponse response){
        //...验证登录
        //验证成功后：
        String token =UUID.randomUUID().toString();
        userMap.put(username, token);
        afterLogin(username, token, request, response);
        return true;
    }
    
    /**
     * 登录后的处理
     */
    public static void afterLogin(String username, String token,HttpServletRequest request,HttpServletResponse response){
        request.getSession().setAttribute(crr_member, "已登录");
        ICTKTokenUtil.addCookie(username, token.toString(),  1000,response); //一千秒后过期
    }
    
    
    
}
