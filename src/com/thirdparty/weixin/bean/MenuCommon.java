package com.thirdparty.weixin.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 普通菜单
 * @author wyj
 * 
 * button 或 sub_button 包裹
 */
@JsonInclude(Include.NON_EMPTY)
public class MenuCommon {
    public static final String TYPE_CLICK="click";
    public static final String TYPE_VIEW="view";
    public static final String TYPE_SCANCODE_PUSH="scancode_push";
    public static final String TYPE_SCANCODE_WAITMSG="scancode_waitmsg";
    public static final String TYPE_PIC_SYSPHOTO="pic_sysphoto";
    public static final String TYPE_PIC_PHOTO_OR_ALBUM="pic_photo_or_album";
    public static final String TYPE_PIC_WEIXIN="pic_weixin";
    public static final String TYPE_LOCATION_SELECT="location_select";
    public static final String TYPE_MEDIA_ID="media_id";
    public static final String TYPE_VIEW_LIMITED="view_limited";
    
    
    /**
        button   是   一级菜单数组，个数应为1~3个
        sub_button  否   二级菜单数组，个数应为1~5个
        type    是   菜单的响应动作类型
        name    是   菜单标题，不超过16个字节，子菜单不超过60个字节
        key click等点击类型必须    菜单KEY值，用于消息接口推送，不超过128字节
        url view类型必须    网页链接，用户点击菜单可打开链接，不超过1024字节
        media_id    media_id类型和view_limited类型必须 调用新增永久素材接口返回的合法media_id
     */
    private String type;
    private String name;
    private String key;
    private String url;
    private String media_id;
    
    private List<MenuCommon> sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public List<MenuCommon> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<MenuCommon> sub_button) {
        this.sub_button = sub_button;
    }
    
    public MenuCommon() {
    }
    public MenuCommon(String name,String type,String key,String url) {
        this.name=name;
        this.type=type;
        this.key=key;
        this.url=url;
    }
    public MenuCommon(String name,List<MenuCommon> sub_button) {
        this.name=name;
        this.sub_button=sub_button;
    }
}
