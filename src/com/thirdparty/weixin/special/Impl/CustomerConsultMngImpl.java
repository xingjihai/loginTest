package com.thirdparty.weixin.special.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdparty.weixin.bean.kfmessage.NewsMessage;
import com.thirdparty.weixin.service.CustomserviceSvc;
import com.thirdparty.weixin.service.KFSessionSvc;
import com.thirdparty.weixin.service.WeChatUserSvc;
import com.thirdparty.weixin.special.CustomerConsultMng;

@Service
public class CustomerConsultMngImpl implements CustomerConsultMng{
//    @Autowired
//    private WxComRecordMng wxComRecordMng;
//    @Autowired
//    private CategoryMng categoryMng;
//    @Autowired
//    private RegionsMng regionsMng;
//    @Autowired
//    private WeChatUserSvc weChatUserSvc;
//    @Autowired
//    private KFSessionSvc kFSessionSvc;
//    @Autowired
//    private CaseMng caseMng;
//    @Autowired
//    private UserGuideMng userGuideMng;
//    @Autowired
//    private CustomserviceSvc customserviceSvc;
    
    
    //咨询类型
    public static final long CASES_LIST_TIMEOUT=2*60*60;  //过期时间2小时
    //地区列表
    public static final long AREA_LIST_TIMEOUT=2*60*60;  //过期时间2小时
    
    public static final long TIMEOUT=2*60*60;  //过期时间2小时
    
    //咨询案件字段
    public static final String CAT_ID="cat_id";  //类型id
    public static final String AREA_ID="area_id";  //地区id
    
    
    /**
     * 拉取咨询类型
     */
    public String get_cases_list(String open_id){
        String str="请输入你要咨询的问题所属类型：\r\n";
//        List<Map> list=categoryMng.getlistByParentid(null, Category.TYPE_CASES, 0);
//        int i=1;
//        for (Map map : list) {
//            str+=i+"\t"+map.get("text")+"\n";
//            i++;
//        }
//        WxComRecord wxComRecord=wxComRecordMng.findByOpenId(open_id);
//        if(wxComRecord==null){
//            wxComRecord=new WxComRecord();
//            wxComRecord.setOpen_id(open_id);
//            wxComRecord.setCat_time(DateUtils.getCurrentTime());
//            wxComRecord.setArea_time(null);
//            wxComRecord.setTime(DateUtils.getCurrentTime());
//            wxComRecord.setStatus(WxComRecord.STATUS_SELECT_CAT);
//            wxComRecordMng.save(wxComRecord);
//        }else{
//            wxComRecord.setCat_time(DateUtils.getCurrentTime());
//            wxComRecord.setArea_time(null);
//            wxComRecord.setTime(DateUtils.getCurrentTime());
//            wxComRecord.setStatus(WxComRecord.STATUS_SELECT_CAT);
//            wxComRecordMng.updateByUpdater(wxComRecord);
//        }
        return str;
    }
    
    /**
     * 咨询类型过期判断
     */
    public boolean cases_list_is_past(String open_id) {
//        WxComRecord wxComRecord=wxComRecordMng.findByOpenId(open_id);
//        Long time=wxComRecord.getTime();
//        Integer status=wxComRecord.getStatus();
//        if(status.equals( WxComRecord.STATUS_SELECT_CAT )&&time!=null&&DateUtils.getCurrentTime()-time<TIMEOUT){   //未过期
//            return false;
//        }
        return true;
    }
    
    public String get_area_list(String open_id) {
        String str="请输入案件地区类型：\r\n";
//        List<Map> list=regionsMng.getStatisticsArea();
//        int i=1;
//        for (Map map : list) {
//            str+=i+"\t"+map.get("text")+"\n";
//            i++;
//        }
//        WxComRecord wxComRecord=wxComRecordMng.findByOpenId(open_id);
//        if(wxComRecord==null){
//            wxComRecord=new WxComRecord();
//            wxComRecord.setOpen_id(open_id);
//            wxComRecord.setCat_time(null);
//            wxComRecord.setArea_time(DateUtils.getCurrentTime());
//            wxComRecord.setTime(DateUtils.getCurrentTime());
//            wxComRecord.setStatus(WxComRecord.STATUS_SELECT_AREA);
//            wxComRecordMng.save(wxComRecord);
//        }else{
//            wxComRecord.setCat_time(null);
//            wxComRecord.setArea_time(DateUtils.getCurrentTime());
//            wxComRecord.setTime(DateUtils.getCurrentTime());
//            wxComRecord.setStatus(WxComRecord.STATUS_SELECT_AREA);
//            wxComRecordMng.updateByUpdater(wxComRecord);
//        }
        return str;
    }
    public boolean area_list_is_past(String open_id) {
//        WxComRecord wxComRecord=wxComRecordMng.findByOpenId(open_id);
//        Long time=wxComRecord.getTime();
//        Integer status=wxComRecord.getStatus();
//        if(status.equals( WxComRecord.STATUS_SELECT_AREA )&&time!=null&&DateUtils.getCurrentTime()-time<TIMEOUT){   //未过期
//            return false;
//        }
        return true;
    }
    
    
    
    private List<Map> get_cat_list(){
//        List<Map> list=categoryMng.getlistByParentid(null, Category.TYPE_CASES, 0);
        List<Map> weixin_cases_list=new ArrayList<Map>();
//        int i=1;
//        for (Map map : list) {
//            Map case_map=new HashMap();
//            case_map.put("id", map.get("id"));
//            case_map.put("text", map.get("text"));
//            case_map.put("sort", i);
//            i++;
//            weixin_cases_list.add(case_map);
//        }
        return weixin_cases_list;
    }
    
    public boolean save_cat_id(String open_id,Integer sort) {
        WxComRecord wxComRecord=wxComRecordMng.findByOpenId(open_id);
        List<Map> list=get_cat_list();
        for (Map map : list) {
            if(  sort.equals(  (Integer)map.get("sort") ) ){
                wxComRecord.setCat_id(  (Integer)map.get("id")  );
                wxComRecordMng.updateByUpdater(wxComRecord);
                return true;
            }
        }
        return false;
    }
    
    private List<Map> get_area_list(){
        List<Map> list=regionsMng.getStatisticsArea();
        List area_list=new ArrayList<>();
        int i=1;
        for (Map map : list) {
            Map case_map=new HashMap();
            case_map.put("id", map.get("id"));
            case_map.put("text", map.get("text"));
            case_map.put("sort", i);
            i++;
            area_list.add(case_map);
        }
        return area_list;
    }
    
    @Override
    public boolean save_area_id(String open_id,Integer sort) {
        WxComRecord wxComRecord=wxComRecordMng.findByOpenId(open_id);
        List<Map> list=get_area_list();
        for (Map map : list) {
            if(  sort.equals(  (Integer)map.get("sort") ) ){
                wxComRecord.setArea_id(  (Integer)map.get("id")  );
                wxComRecordMng.updateByUpdater(wxComRecord);
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public String get_user_guide(String open_id) {
        String str="您可以回复以下内容获取用户指南相关文章：\r\n";
        Page<UserGuide> page=userGuideMng.getAll(1, 20);
        List<UserGuide> list=(List<UserGuide>) page.getResult();
        int i=101;
        for (UserGuide userGuide : list) {
            str+=i+"\t"+userGuide.getTitle()+"\n";
            i++;
        }
        return str;
    }
    private List<Map> get_user_guide_list(){
        Page<UserGuide> page=userGuideMng.getAll(1, 20);
        List<UserGuide> list=(List<UserGuide>) page.getResult();
        List ug_list=new ArrayList<>();
        int i=101;
        for (UserGuide userGuide : list) {
            Map ug_map=new HashMap();
            ug_map.put("id", userGuide.getId());
            ug_map.put("text", userGuide.getTitle());
            ug_map.put("sort", i);
            i++;
            ug_list.add(ug_map);
        }
        return ug_list;
    }
    
    @Override
    public JSONObject send_user_guide_message(String open_id,Integer sort) {
        List<Map> list=get_user_guide_list();
        JSONObject send_return_obj = null;
        for (Map map : list) {
            if(  sort.equals(  (Integer)map.get("sort") ) ){
                Integer id=(Integer)map.get("id");
                UserGuide userGuide=userGuideMng.findById(id);
                //获取用户指南路径
                String url=userGuideMng.getUserGuide(id);
                String description=StringUtil.addRedByKeyword(userGuide.getContent(), null, true, 40);
                NewsMessage newsMessage=new NewsMessage( userGuide.getTitle(),description,url,"");
                List newsList=new ArrayList<NewsMessage>();
                newsList.add(newsMessage);
                send_return_obj=customserviceSvc.send_news(open_id, newsList);
                break;
            }
        }
//        if(send_return_obj==null){
//            System.out.println("微信公众号发送用户指南消息："+send_return_obj.toString());
//        }else{
//            System.out.println("微信公众号发送用户指南消息：数字不正确");
//        }
        return send_return_obj;
    }
    
    

    @Override
    public void createCases(String open_id) {
        WxComRecord wxComRecord=wxComRecordMng.findByOpenId(open_id);
        caseMng.saveForWeChat(open_id, wxComRecord.getArea_id(), wxComRecord.getCat_id());
        
    }

    @Override
    public boolean is_in_waitcase(String open_id) {
        JSONObject resultJson=kFSessionSvc.getwaitcase(open_id);
        List<Map> list=(List<Map>) resultJson.get("waitcaselist");
        for (Map map : list) {
            String openid=(String) map.get("openid");
            if(open_id.equals(openid)){
                return true;
            }
        }
        return false;
    }
    

    @Override
    public void intoService(String open_id) {
        WxComRecord wxComRecord=wxComRecordMng.findByOpenId(open_id);
        if(wxComRecord==null){
            wxComRecord=new WxComRecord();
            wxComRecord.setOpen_id(open_id);
            wxComRecord.setTime(DateTool.getCurrentTime());
            wxComRecord.setStatus(WxComRecord.STATUS_SERVICE_NO_SESSION);
            wxComRecordMng.save(wxComRecord);
        }else{
            wxComRecord.setTime(DateTool.getCurrentTime());
            wxComRecord.setStatus(WxComRecord.STATUS_SERVICE_NO_SESSION);
            wxComRecordMng.updateByUpdater(wxComRecord);
        }
    }

    @Override
    public boolean isService(String open_id) {
        WxComRecord wxComRecord = wxComRecordMng.findByOpenId(open_id);
        Long time = wxComRecord.getCat_time();
        Integer status = wxComRecord.getStatus();
//        if (status.equals(WxComRecord.STATUS_SERVICE) && time != null && DateUtils.getCurrentTime() - time < TIMEOUT) {
//            return false;
//        }
        if ( status.equals(WxComRecord.STATUS_SERVICE_NO_SESSION)  ) {  //不判断过期时间
            wxComRecord.setStatus(WxComRecord.STATUS_SERVICE_HAS_SESSION);
            wxComRecord.setTime(DateTool.getCurrentTime());
            wxComRecordMng.updateByUpdater(wxComRecord);
            return true;
        }else if(  status.equals(WxComRecord.STATUS_SERVICE_HAS_SESSION) ){
            //判断是否拥有待接入会话或者已经在会话中，否则为客服结束会话
            
            JSONObject returnObject=kFSessionSvc.getsession(open_id);
            String kf_account=(String) returnObject.get("kf_account");
            if(   StringUtils.isNotBlank(kf_account) ||this.is_in_waitcase(open_id)    ){  //已在会话中或拥有待接入会话
                return true;
            }else{
                wxComRecord.setStatus(WxComRecord.STATUS_INIT);
                wxComRecord.setTime(null);
                wxComRecordMng.updateByUpdater(wxComRecord);
            }
            
        }
        return false;
    }

    
}
