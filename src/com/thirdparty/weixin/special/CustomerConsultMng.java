package com.thirdparty.weixin.special;

import net.sf.json.JSONObject;


/**
 * 微信进入即时咨询前的案件创建逻辑
 * @author wyj
 */
public interface CustomerConsultMng {

    /**
     * 拉取咨询类型
     */
    public String get_cases_list(String open_id);
    /**
     * 咨询类型过期判断
     * @return true过期，false没过期
     */
    public boolean cases_list_is_past(String open_id);
    /**
     * 拉取案件地区
     */
    public String get_area_list(String open_id);
    /**
     * 案件地区过期判断
     * @return true过期，false没过期
     */
    public boolean area_list_is_past(String open_id);
    
    /**
     * 拉取用户指南
     */
    public String get_user_guide(String open_id);
    
    /**
     * 根据序号保存案件类型id
     * @param sort 序号
     */
    public boolean save_cat_id(String open_id,Integer sort);
    /**
     * 根据序号保存案件地区id
     */
    public boolean save_area_id(String open_id,Integer sort);
    
    /**
     * 生成案件
     */
    public void createCases(String open_id);
    
    
    /**
     * 判断客户是否在待接入会话中
     */
    public boolean is_in_waitcase(String open_id);
    
    /**
     * 进入客服状态
     */
    public void intoService(String open_id);
    
    /**
     * 判断是否是客服状态中
     * true：是
     */
    public boolean isService(String open_id);
    
    /**
     * 返回用户指南图文消息
     */
    public JSONObject  send_user_guide_message(String open_id, Integer sort);
}
