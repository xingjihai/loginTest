package com.thirdparty.weixin.service.impl;

import org.junit.Test;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import tools.http.HttpTools;

import com.thirdparty.weixin.base.BaseSvc;
import com.thirdparty.weixin.bean.WeChatUser;
import com.thirdparty.weixin.service.WeChatUserSvc;

@Service
public class WeChatUserSvcImpl implements WeChatUserSvc{

    public WeChatUser getUserInfo(String open_id) {
        StringBuilder urlBuilder = new StringBuilder("https://api.weixin.qq.com/cgi-bin/user/info");
        urlBuilder.append("?access_token=").append(BaseSvc.get_access_token());
        urlBuilder.append("&openid=").append(open_id);
        urlBuilder.append("&lang=zh_CN");
        JSONObject resultJson = JSONObject.fromObject(HttpTools.exceteGet(urlBuilder.toString()));
        WeChatUser weChatUser=(WeChatUser) JSONObject.toBean(resultJson, WeChatUser.class);
        return weChatUser;
    }
    
    
    
    
    //----测试 ----//
    @Test
    public void test(){
        getUserInfo("ornyc1IlCg1N3kKTi-tTtpaBGQ70"); 
    }
}
