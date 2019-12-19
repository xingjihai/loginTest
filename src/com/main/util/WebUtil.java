package com.main.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**参数接收工具类
 * @author wyj 20171016
 */
public class WebUtil {
    public Map<String,Object> paramsMap;
    
    public WebUtil(Map<String,Object> paramsMap) {
        this.paramsMap=paramsMap;
    }
    
    /**
     * Map方式 ,返回request的参数,
     */
    public static Map<String,Object> getParamsMap(HttpServletRequest request){
        Map<String,Object> paramMap=new HashMap<>();
        Enumeration<String> params= request.getParameterNames();
        while (params.hasMoreElements()) {
            String paraName = (String) params.nextElement();
            paramMap.put(paraName, request.getParameter(paraName));
        }
        return paramMap;
    }
    
    /**
     *  判空
     *  @return true 空
     */
    public static boolean isBlank(Object obj){
        if(obj instanceof String){
            return StringUtils.isBlank((String)obj);
        }else{  //Number or Date
            return obj==null;
        }
    }
    /**
     *  根据key获取paramsMap并判空
     *  @return true 空
     */
    public  boolean isBlankByKey(String key){
        return isBlank( paramsMap.get(key) ); 
    }

    public Map<String, Object> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, Object> paramsMap) {
        this.paramsMap = paramsMap;
    }
    
    
    public static void main(String[] args) {
        //使用示例：
        Map<String,Object> paramsMap=new HashMap<>();
        //... paramsMap为controller层通过getParamsMap方法获取的Map
        WebUtil WebUtil=new WebUtil(paramsMap);
        WebUtil.getInteger("number");
    }
    
    
    // ======= 简化转换 TODO待拓展
    public Integer getInteger(String key){
        try {
            return Integer.parseInt( (String) paramsMap.get(key));
        } catch (Exception e) {
            return null;
        }
    }
    public String getString(String key){
        try {
            return (String) paramsMap.get(key);
        } catch (Exception e) {
            return null;
        }
    }
}
