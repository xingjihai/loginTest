package com.thirdparty.weixin.service;

import net.sf.json.JSONObject;


/**
 * 客服会话控制
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1458044820&token=&lang=zh_CN
 * @author wyj
 */
public interface KFSessionSvc {
    /**
     * 创建会话
     */
    
    /**
     * 关闭会话
     */
    
    /**
     * 获取客户会话状态
     */
    public JSONObject getsession(String open_id);
    /**
     *获取客服会话列表
     */
    
    /**
     *获取未接入会话列表
     */
    public JSONObject getwaitcase(String open_id);
}
