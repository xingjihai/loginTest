package com.thirdparty.weixin.service;

import com.thirdparty.weixin.bean.InputMessage;

/**
 * 事件推送处理
 * 文档地址： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140454&token=&lang=zh_CN
 * @author wyj
 * 与消息的区分：MsgType=event
 */
public interface EventPushHandleSvc {
    /**
     * 事件推送入口(全部事件的入口)
     */
    public void event_entrance(InputMessage inputMessage);
    
    /**
     * 关注事件处理
     */
    public void subscribe_dispose(InputMessage inputMessage);
    /**
     * 取消关注事件处理
     */
    public void unsubscribe_dispose(InputMessage inputMessage);
    /**
     * 点击菜单拉取消息时的事件推送
     */
    public void click_dispose(InputMessage inputMessage);
    /**
     * 点击菜单跳转链接时的事件处理
     */
    public void view_dispose(InputMessage inputMessage);
}
