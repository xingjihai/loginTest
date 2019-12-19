package com.thirdparty.weixin.service;

import com.thirdparty.weixin.bean.InputMessage;


/**
 * 消息处理
 * @author wyj
 */
public interface MessageHandleSvc {
    /**
     * 文本消息处理 --案件处理
     */
    @Deprecated
    public String text_dispose(InputMessage inputMessage);
    /**
     * 文本消息处理 --获取咨询指南
     */
    public String text_dispose2(InputMessage inputMessage);
    
    /**
     * 图片消息处理
     */
    public void image_dispose(InputMessage inputMessage);
    /**
     * 语音消息处理
     */
    public void voice_dispose(InputMessage inputMessage);
    /**
     * 视频消息处理
     */
    public void video_dispose(InputMessage inputMessage);
    /**
     * 小视频消息处理
     */
    public void shortvideo_dispose(InputMessage inputMessage);
    /**
     * 地理位置消息处理
     */
    public void location_dispose(InputMessage inputMessage);
    /**
     * 链接消息处理
     */
    public void link_dispose(InputMessage inputMessage);
}
