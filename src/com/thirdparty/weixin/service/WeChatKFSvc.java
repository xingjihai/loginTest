package com.thirdparty.weixin.service;

import java.io.File;
import java.util.List;

import net.sf.json.JSONObject;

import com.thirdparty.weixin.bean.WeChatKF;
import com.thirdparty.weixin.bean.WeChatOnlineKF;

/**
 * 微信客服接口
 * @author wyj
 *
 */
public interface WeChatKFSvc {
    /**
     * 创建客服
     * @param kf_account  完整客服帐号，格式为：帐号前缀@公众号微信号，帐号前缀最多10个字符，必须是英文、数字字符或者下划线，后缀为公众号微信号，长度不超过30个字符
     * @param nickname 客服昵称，最长16个字
     */
    public JSONObject add_kfaccount(String kf_account,String nickname);
    /**
     * 邀请绑定客服帐号
     * @param kf_account   完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param invite_wx  接收绑定邀请的客服微信号
     */
    public JSONObject invite_worker(String kf_account,String invite_wx);
    
    /**
     * 获取客服基本信息
     */
    public List<WeChatKF> get_kflist();
    /**
     * 获取在线客服基本信息
     */
    public List<WeChatOnlineKF> get_online_kflist();
    
    /**
     * 设置客服昵称
     */
    public JSONObject update_kfnc(String kf_account,String nickname);
    /**
     * 上传客服头像
     */
    public JSONObject upload_headimg(String kf_account,File file);
    
    /**
     * 删除客服账号
     */
    public JSONObject del_kfaccount(String kf_account);
    
    
    //============调用 ==========//
    /**
     * 创建并绑定客服
     * @param invite_wx 微信号
     * @param nickname  微信昵称
     * @param head_img  客服头像
     */
    public boolean add_invite_kf(String invite_wx,String nickname,File head_img);
	void test4();
	void test5();
}
