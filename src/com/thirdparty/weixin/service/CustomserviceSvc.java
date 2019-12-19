package com.thirdparty.weixin.service;

import java.util.List;

import com.thirdparty.weixin.bean.kfmessage.NewsMessage;

import net.sf.json.JSONObject;

/**
 * 客服发送消息接口
 * @author wyj
 */
public interface CustomserviceSvc {
    

    /**
     * 回复文本消息
     */
    public JSONObject send_text(String openId, String content);

    /**
     * 回复图文消息
     */
    public JSONObject send_news(String openId, List<NewsMessage> newsList);
}
