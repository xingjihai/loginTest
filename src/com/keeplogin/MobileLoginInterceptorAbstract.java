package com.keeplogin;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

/**
 * 移动端登录拦截--通过Cookie解决Session过期问题。(保持登录)
 * @author wyj
 */
public abstract class MobileLoginInterceptorAbstract extends HandlerInterceptorAdapter{

    /**
     * 不需要验证登录注解
     * @author wyj
     */
    @Target({ ElementType.METHOD, ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface NonLogin {
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HandlerMethod handler = (HandlerMethod) o;
        if (handler.getBeanType().getAnnotation(NonLogin.class) != null) {  //判断类是否有注解
            return true;
        }
        if (handler.getReturnType().getMethodAnnotation(NonLogin.class) != null){  //判断方法是否有注解
            return true;
        }
        if(sessionHasLogin(request)){   //已登录则返回
            return true;
        }
        if (!checkICTKCookieAndLogin(request, response)) {
            noLoginReturnHandle(response); //未登录返回处理
            return false;
        }
        return true;
    }
    
    
    /**
     * @return false  不保持登录  true 保持登录
     */
    boolean checkICTKCookieAndLogin(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = WebUtils.getCookie(request, CookieNameICTK);
        String valLLTK  = null;
        if (cookie != null)
             valLLTK = cookie.getValue();
        if(StringUtils.isBlank(valLLTK))
            valLLTK = request.getHeader("accessToken");
        
        if(StringUtils.isBlank(valLLTK))
            return false;
        
        try {
            String s1 = ICTKTokenUtil.decode(valLLTK);
            String[] arrs = s1.split("\\|");
            String username = arrs[0];
            String token = arrs[1];
            String timestamp = arrs[2];
            return sessionDoLogin( username, token,request,response);
        } catch (Exception e) {
            cookie = new Cookie(CookieNameICTK, "");
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie); // remove cookie
            e.printStackTrace();
            return false;
        }
    }

   
    /**
     * 在session中判断是否有值,有则直接返回,避免一直进入判断cookie的方法中,分开能提高效率<br/>
     * （情况：1、未登录 2、session过期）
     * @return true  已登录
     *  false  未登录
     */
    abstract boolean sessionHasLogin(HttpServletRequest request);
    
    /**
     * 登录处理(调用登录方法或直接存进session和Cookie)
     * @param username  登录名
     * @param token  登录标识
     */
    abstract boolean sessionDoLogin(String username, String token,HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 未登录的返回处理(如返回未登录的提示)
     */
    abstract void noLoginReturnHandle(HttpServletResponse response);
    
    
    public static byte[] key128Base64=Base64.decodeBase64("qOLmGqNk2zqOFPHASLRRMg==");
    public static String CookieNameICTK = "LLTK";
    public static String COOKIE_PATH = "/";
    public static String VERSION = "v1";
    public static class ICTKTokenUtil {
        
        public static String encode(String username, String token, Long timestamp) throws UnsupportedEncodingException {
            String v = username + "|" + token + "|" + timestamp;
            byte[] bytes = CryptUtil.encryptAES(v.getBytes(), key128Base64);
            String encStr = Base58.encode(bytes);
            return encStr;
        }

        public static String decode(String encStr) throws UnsupportedEncodingException {
            byte[] v =Base58.decode(encStr);
            byte[] bytes = CryptUtil.decryptAES(v, key128Base64);
            return new String(bytes);
        }
        /**
         * @param username  登录名
         * @param token  登录标识(最好不要是密码,而是一个生成的登录标识。)
         * @param time  过期时间
         * @return 
         */
        public static void addCookie(String username, String token, int time,HttpServletResponse response){
            String cookieValue= null;
            try {
                cookieValue=ICTKTokenUtil.encode(username, token,  new Date().getTime());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            HttpUtil.addCookie(response, CookieNameICTK, cookieValue, time);
        }
    }
}
