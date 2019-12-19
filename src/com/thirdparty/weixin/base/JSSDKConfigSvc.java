package com.thirdparty.weixin.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;
import tools.http.HttpTools;
import tools.http.SHA1Sign;

import org.apache.commons.codec.digest.DigestUtils;

import com.thirdparty.weixin.bean.WxJsAPI;


/**
 * JS-SDK 使用参数配置
 * @author wyj
 */
public class JSSDKConfigSvc {
    private static String jsapi_ticket;
    private static Integer jsapi_ticket_expires_in;
    private static Date jsapi_ticket_datetime;
    /**
     * jsapi_ticket 临时票据<br>
     * <b>说明</b>:
     * jsapi_ticket是公众号用于调用微信JS接口的临时票据。
     */
    private static String get_jsapi_ticket(){
        if ((jsapi_ticket != null) && (new Date().getTime() - jsapi_ticket_datetime.getTime() < jsapi_ticket_expires_in * 1000)) {
            return jsapi_ticket;
        }
        StringBuilder urlBuilder = new StringBuilder("https://api.weixin.qq.com/cgi-bin/ticket/getticket");
        urlBuilder.append("?access_token=").append(BaseSvc.get_access_token());
        urlBuilder.append("&type=jsapi");
        JSONObject resultJson = JSONObject.fromObject(HttpTools.exceteGet(urlBuilder.toString()));
        jsapi_ticket = resultJson.getString("ticket");
        jsapi_ticket_expires_in = resultJson.getInt("expires_in") * 2 / 3;
        jsapi_ticket_datetime = new Date();
        return jsapi_ticket;
    }
    
    
    /**
     * 生成配置参数
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
     * 附录1-JS-SDK使用权限签名算法
     * @param url  调用的页面路径
     */
    public static  WxJsAPI getWxJsConfig( String url) {
        String jsapi_ticket=get_jsapi_ticket();
        String nonce_str = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String signature = "";
        
        Map<String, Object> conditionMap=new HashMap<String, Object>();
        conditionMap.put("jsapi_ticket", jsapi_ticket);
        conditionMap.put("noncestr", nonce_str);
        conditionMap.put("timestamp", timestamp);
        conditionMap.put("url", url);
        
        try {
            signature=SHA1Sign.getSHA1(conditionMap);
        } catch (Exception e) {
            throw new RuntimeException("signJstickerr " + e.getMessage());
        }
        WxJsAPI wxJsAPI = new WxJsAPI();
        wxJsAPI.setNonceStr(nonce_str);
        wxJsAPI.setTimestamp(timestamp);
        wxJsAPI.setSignature(signature);
        wxJsAPI.setJsapiTicket(jsapi_ticket);
        String appId = WeixinConfig.APPID;
        wxJsAPI.setAppId(appId);
        wxJsAPI.setUrl(url);
        return wxJsAPI;
    }
}
