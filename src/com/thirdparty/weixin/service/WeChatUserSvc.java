package com.thirdparty.weixin.service;

import com.thirdparty.weixin.bean.WeChatUser;

public interface WeChatUserSvc {
    /**
     * 获取用户基本信息
     */
    public WeChatUser getUserInfo(String open_id);
}
