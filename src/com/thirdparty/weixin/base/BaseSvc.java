package com.thirdparty.weixin.base;

import java.util.Date;

import net.sf.json.JSONObject;
import tools.http.HttpTools;


/**微信基础接口
 * @author wyj
 */
public class BaseSvc {
    private static String access_token;
    private static Integer access_token_expires_in;
    private static Date access_token_datetime;
    
    /**
     * 获取接口调用凭据access_token
     */
    public static String get_access_token(){
        if ((access_token != null) && (new Date().getTime() - access_token_datetime.getTime() < access_token_expires_in * 1000)) {
            return access_token;
        }
        // 如果access_token为null或者超时
        StringBuilder urlBuilder = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token");
        urlBuilder.append("?grant_type=client_credential");
        urlBuilder.append("&appid=").append(WeixinConfig.APPID);
        urlBuilder.append("&secret=").append(WeixinConfig.SECRET);
        JSONObject resultJson = JSONObject.fromObject(HttpTools.exceteGet(urlBuilder.toString()));
        access_token = resultJson.getString("access_token");
        access_token_expires_in = resultJson.getInt("expires_in") * 2 / 3;  //凭证有效期 *(2/3）
        access_token_datetime = new Date();
        return access_token;
    }
    
    
    
    public static String get_open_id(String code){
        StringBuilder urlBuilder = new StringBuilder("https://api.weixin.qq.com/sns/oauth2/access_token");
        urlBuilder.append("?appid=").append(WeixinConfig.APPID);
        urlBuilder.append("&secret=").append(WeixinConfig.SECRET);
        urlBuilder.append("&code=").append(code);
        urlBuilder.append("&grant_type=authorization_code");
        String resultJsonStr = HttpTools.exceteGet(urlBuilder.toString());
        try {
            return JSONObject.fromObject(resultJsonStr).getString("openid");
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 主动刷新access_token
     */
    public static void update_access_token(){
        access_token=null;
        get_access_token();
    }
}
