package com.thirdparty.weixin.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.thirdparty.weixin.bean.MenuCommon;
import com.thirdparty.weixin.bean.MenuConditionalField;

/**微信菜单接口
 * @author wyj
 */
public interface MenuCommonSvc {
    /**
     * 创建微信菜单
     */
    public JSONObject menu_create(List<MenuCommon> Menulist,MenuConditionalField conditionalField);
    /**
     * 获取微信菜单
     */
    public List<MenuCommon> menu_get();
    /**
     * 删除微信菜单(删除所有自定义菜单（包括默认菜单和全部个性化菜单）)
     */
    public boolean menu_delete();
}
