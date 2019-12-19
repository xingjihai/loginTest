package com.thirdparty.weixin.bean;

/**
 * 微信在线客服
 * @author wyj
 */
public class WeChatOnlineKF {
    /**
        kf_account   完整客服帐号，格式为：帐号前缀@公众号微信号
        status  客服在线状态，目前为：1、web 在线
        kf_id   客服编号
        accepted_case   客服当前正在接待的会话数
     */
    private String kf_account;
    private Integer status;
    private String kf_id;
    private Integer accepted_case;
    public String getKf_account() {
        return kf_account;
    }
    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getKf_id() {
        return kf_id;
    }
    public void setKf_id(String kf_id) {
        this.kf_id = kf_id;
    }
    public Integer getAccepted_case() {
        return accepted_case;
    }
    public void setAccepted_case(Integer accepted_case) {
        this.accepted_case = accepted_case;
    }
    
}
