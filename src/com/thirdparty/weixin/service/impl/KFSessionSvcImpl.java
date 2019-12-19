package com.thirdparty.weixin.service.impl;

import net.sf.json.JSONObject;
import tools.http.HttpTools;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.thirdparty.weixin.base.BaseSvc;
import com.thirdparty.weixin.service.KFSessionSvc;

@Service
public class KFSessionSvcImpl implements KFSessionSvc{

    public JSONObject getsession(String open_id) {
        StringBuffer url=new StringBuffer("https://api.weixin.qq.com/customservice/kfsession/getsession?");
        url.append("access_token=").append(BaseSvc.get_access_token());
        url.append("&openid=").append(open_id);
        JSONObject returnObject=JSONObject.fromObject(HttpTools.exceteGet(url.toString()));
        return returnObject;
    }
    
    public JSONObject getwaitcase(String open_id) {
        StringBuffer url=new StringBuffer("https://api.weixin.qq.com/customservice/kfsession/getwaitcase?");
        url.append("access_token=").append(BaseSvc.get_access_token());
        JSONObject returnObject=JSONObject.fromObject(HttpTools.exceteGet(url.toString()));
        return returnObject;
    }
    
    
    
    
    //----测试----------//
    @Test
    public void test(){
        System.out.println(getsession("ornyc1IlCg1N3kKTi-tTtpaBGQ70").toString());
    }




   
}
