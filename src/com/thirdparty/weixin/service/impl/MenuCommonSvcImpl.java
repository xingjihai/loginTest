package com.thirdparty.weixin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import tools.http.HttpTools;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.thirdparty.weixin.base.BaseSvc;
import com.thirdparty.weixin.bean.MenuCommon;
import com.thirdparty.weixin.bean.MenuConditionalField;
import com.thirdparty.weixin.service.MenuCommonSvc;
import com.thirdparty.weixin.special.config.ConstantFor12348;

@Service
public class MenuCommonSvcImpl implements MenuCommonSvc{

    public JSONObject menu_create(List<MenuCommon> menuList,MenuConditionalField conditionalField) {
        JSONObject menuJson=getMenuJson(menuList, conditionalField);
        String url="";
        if(conditionalField==null){
            url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+BaseSvc.get_access_token();
        }else{
            url = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token="+BaseSvc.get_access_token();
        }
        JSONObject returnObj = JSONObject.fromObject(HttpTools.excetePost(url.toString(),menuJson.toString() ));
        return returnObj;
    }
    
    
    /**
     * 根据List<MenuCommon> 构造json字符串<br>
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
     
     普通菜单--
     {
      "button": [
           {   
              "type":"click",     
              "name":"一级菜单",
              "key":"V1001_TODAY_MUSIC"
            },
            {
               "name":"一级菜单",
               "sub_button":[
               {    
                   "type":"view",
                   "name":"二级菜单",
                   "url":"http://www.soso.com/"
                },
                {
                   "type":"view",
                   "name":"二级菜单",
                   "url":"http://v.qq.com/"
                }
                ]
            }
       ]
     }
     
     个性化菜单--
     https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
    {
        "button":[
        {   
            "type":"click",
            "name":"今日歌曲",
            "key":"V1001_TODAY_MUSIC" 
        },
        { 
            "name":"菜单",
            "sub_button":[
            {   
                "type":"view",
                "name":"搜索",
                "url":"http://www.soso.com/"
            },
                     {
                             "type":"miniprogram",
                             "name":"wxa",
                             "url":"http://mp.weixin.qq.com",
                             "appid":"wx286b93c14bbf93aa",
                             "pagepath":"pages/lunar/index.html",
                       },
                    {
                "type":"click",
                "name":"赞一下我们",
                "key":"V1001_GOOD"
                }]
         }],
        "matchrule":{
          "tag_id":"2",
          "sex":"1",
          "country":"中国",
          "province":"广东",
          "city":"广州",
          "client_platform_type":"2",
          "language":"zh_CN"
          }
    }
     */
    private  JSONObject getMenuJson(List<MenuCommon> menuList,MenuConditionalField conditionalField){
        JSONObject bottonMenu=new JSONObject();
        JSONArray bottonArray=new JSONArray();
        for (MenuCommon menuCommon : menuList) {
            if(menuCommon.getSub_button()==null){  //无子菜单（在这里不考虑三级菜单的情况）
                bottonArray.add(menuCommon);
            }else{
                JSONObject childMenu=new JSONObject();
                childMenu.accumulate("name", menuCommon.getName());
                childMenu.accumulate("sub_button", menuCommon.getSub_button());
                bottonArray.add(childMenu);
            }
        }
        bottonMenu.accumulate("button", bottonArray);
        if(conditionalField!=null){
            bottonMenu.accumulate("matchrule", conditionalField);  //个性化菜单多加了这个
        }
        return bottonMenu;
    }
    
    
    
    
    public List<MenuCommon>  menu_get() {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+BaseSvc.get_access_token();
        JSONObject returnObj = JSONObject.fromObject(HttpTools.exceteGet(url));
        return getMenuList(returnObj);
    }
    /**解析json返回List<MenuCommon>
     */
    @SuppressWarnings("unchecked")
    private  List<MenuCommon> getMenuList(JSONObject menuJosn){
        Integer errcode=(Integer) menuJosn.get("errcode");
        if(errcode!=null&&errcode==46003){  //菜单不存在
            return null;
        }
        try {
            JSONObject menuObj=menuJosn.getJSONObject("menu");
            JSONArray buttonArray=menuObj.getJSONArray("button");
            JsonConfig jsonConfig=new JsonConfig();
            Map<String,Object> classMap=new HashMap<String, Object>();
            classMap.put("sub_button", MenuCommon.class);
            jsonConfig.setClassMap(classMap);
            jsonConfig.setCollectionType(List.class);
            jsonConfig.setRootClass(MenuCommon.class);
            List<MenuCommon> list=(List<MenuCommon>) JSONArray.toCollection(buttonArray, jsonConfig);
            return list;
        } catch (Exception e) {
            System.out.println("===== 获取微信菜单 ====      json转换为list时报错");
            return null;
        }
    }
    
    public boolean menu_delete() {
        String url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+BaseSvc.get_access_token();
        JSONObject returnObj = JSONObject.fromObject(HttpTools.exceteGet(url));
        Integer errcode=returnObj.getInt("errcode");
        if(errcode==0){
            return true;
        }else{
            return false;
        }
    }
    
    
    //-----------------------   未在后台展示时的操作     -------------------//
    
    
    @Test
    public void createMenu() {
        List<MenuCommon> allMenuList=new ArrayList<MenuCommon>();
        
        MenuCommon menu1=null;
        MenuCommon menu2=null;
        MenuCommon menu3=null;
        List<MenuCommon> menuList1=new ArrayList<MenuCommon>();
        
//        menuList1.add(new MenuCommon("在线客服", MenuCommon.TYPE_CLICK, ConstantFor12348.ONLINE_COUNSELING,null)); 
        
//        menuList1.add(new MenuCommon("留言咨询", MenuCommon.TYPE_VIEW ,null, "http://www.wyjej.top/WXCase/consult.jhtml"));  //TODO url
//        menuList1.add(new MenuCommon("我的咨询", MenuCommon.TYPE_VIEW,null, "http://www.wyjej.top/WXCase/my_consult.jhtml"));  //TODO url
//        
//        menuList2.add(new MenuCommon("咨询案例", MenuCommon.TYPE_VIEW,null, "http://www.wyjej.top/WXConsultcase/consult_case.jhtml"));  //TODO url
//        menuList2.add(new MenuCommon("司法地图", MenuCommon.TYPE_VIEW ,null, "http://www.wyjej.top/WXMap/map.jhtml"));  //TODO url
        
        menuList1.add(new MenuCommon("电话咨询", MenuCommon.TYPE_CLICK, ConstantFor12348.CONSULT_TYPE_PHONE,null)); 
        menuList1.add(new MenuCommon("留言咨询", MenuCommon.TYPE_VIEW ,null, "http://weitu.kwp8.top/12348/WXCase/consult.jhtml"));  //TODO url
        menuList1.add(new MenuCommon("我的咨询", MenuCommon.TYPE_VIEW,null, "http://weitu.kwp8.top/12348/WXCase/my_consult.jhtml"));  //TODO url
        menuList1.add(new MenuCommon("APP下载", MenuCommon.TYPE_CLICK, ConstantFor12348.CONSULT_TYPE_APP,null));  
        
        
        menu1=new MenuCommon("我要咨询",menuList1 );
//        menu2=new MenuCommon("我要查询",menuList2 );
        menu2=new MenuCommon("咨询案例", MenuCommon.TYPE_VIEW,null, "http://weitu.kwp8.top/12348/WXConsultcase/consult_case.jhtml");
//        menu3=new MenuCommon("咨询方式",menuList3 );
        menu3=new MenuCommon("司法地图", MenuCommon.TYPE_VIEW ,null, "http://weitu.kwp8.top/12348/WXMap/map.jhtml")  ;
        
        
        allMenuList.add(menu1);
        allMenuList.add(menu2);
        allMenuList.add(menu3);
        
        JSONObject returnObj=menu_create(allMenuList,null);
        System.out.println(returnObj);
        
//        //个性化设置。
//        MenuConditionalField field1=new MenuConditionalField();
//        field1.setSex("1");  //男
//        MenuConditionalField field2=new MenuConditionalField();
//        field2.setSex("2");  //女
//        
//        JSONObject returnObj=menu_create(allMenuList,field1);
//        System.out.println(returnObj.toString());
        
//        JSONObject returnObj2=menu_create(allMenuList2,field2);
//        System.out.println(returnObj2.toString());
    }
    @Test
    public void getMenu() {
        List list=menu_get();
        System.out.println(JSONArray.fromObject(list).toString());
    }
    @Test
    public void deleteMenu() {
        boolean flag=menu_delete();
        System.out.println(flag);
    }

    

    
}
