package com.thirdparty.weixin.service.impl;

import java.io.File;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.http.HttpTools;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.thirdparty.weixin.base.BaseSvc;
import com.thirdparty.weixin.base.util.FileUpload;
import com.thirdparty.weixin.bean.WeChatKF;
import com.thirdparty.weixin.bean.WeChatOnlineKF;
import com.thirdparty.weixin.service.WeChatKFSvc;

@Service
public class WeChatKFSvcImpl implements WeChatKFSvc{
    
    public JSONObject add_kfaccount(String kf_account,String nickname) {
    	String access_token=BaseSvc.get_access_token();
        String url="https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+access_token;
        JSONObject kf=new JSONObject();
        kf.accumulate("kf_account", kf_account);
        kf.accumulate("nickname", nickname);
        JSONObject object=JSONObject.fromObject(HttpTools.excetePost(url, kf.toString()));
        return object;
    }
    
    public JSONObject invite_worker(String kf_account, String invite_wx) {
        String url="https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token="+BaseSvc.get_access_token();
        JSONObject kf=new JSONObject();
        kf.accumulate("kf_account", kf_account);
        kf.accumulate("invite_wx", invite_wx);
        JSONObject object=JSONObject.fromObject(HttpTools.excetePost(url, kf.toString()));
        return object;
    }

    @SuppressWarnings("unchecked")
    public List<WeChatKF> get_kflist() {
        String url="https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token="+BaseSvc.get_access_token();
        JSONObject object=JSONObject.fromObject(HttpTools.exceteGet(url));
        List<WeChatKF> list=(List<WeChatKF>) JSONArray.toCollection(   JSONArray.fromObject( object.get("kf_list")),WeChatKF.class   );
        return list;
    }

    
    @SuppressWarnings("unchecked")
    public List<WeChatOnlineKF> get_online_kflist() {
        String url="https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token="+BaseSvc.get_access_token();
        JSONObject object=JSONObject.fromObject(HttpTools.exceteGet(url));
        JSONArray array=JSONArray.fromObject( object.get("kf_online_list"));
        List<WeChatOnlineKF> list=(List<WeChatOnlineKF>) JSONArray.toCollection(  array,WeChatOnlineKF.class   );
        return list;
    }

    public JSONObject update_kfnc(String kf_account,String nickname) {
        String url="https://api.weixin.qq.com/customservice/kfaccount/update?access_token="+BaseSvc.get_access_token();
        JSONObject kf=new JSONObject();
        kf.accumulate("kf_account", kf_account);
        kf.accumulate("nickname", nickname);
        JSONObject object=JSONObject.fromObject(HttpTools.excetePost(url, kf.toString()));
        return object;
    }

    public JSONObject upload_headimg(String kf_account, File file) {
        /**
         * 上传图片
         */
        String url="https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token="+BaseSvc.get_access_token()+"&kf_account="+kf_account;
        
        try {
            String str=FileUpload.uploadFile(url, file, "image");
            return JSONObject.fromObject(str);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONObject.fromObject("error");
        }
    }
    

    public JSONObject del_kfaccount(String kf_account) {
        String url="https://api.weixin.qq.com/customservice/kfaccount/del?access_token="+BaseSvc.get_access_token()+"&kf_account="+kf_account;
        JSONObject object=JSONObject.fromObject(HttpTools.exceteGet(url));
        return object;
    }
    
    
    public boolean add_invite_kf(String invite_wx, String nickname, File head_img) {
        String kf_account=invite_wx+"@xmsf12348";
        JSONObject result1=add_kfaccount(kf_account, nickname);
        JSONObject result2=upload_headimg(kf_account, head_img);
        JSONObject result3=invite_worker( kf_account ,invite_wx );
        
        System.out.println("创建客服 ---------- " + result1.toString()  );
        System.out.println("修改客服头像 ---------- " + result2.toString()   );
        System.out.println( "绑定客服账号 ---------- "+  result3.toString()        );
        Integer errcode1=(Integer) result1.get("errcode");
        
        boolean flag=true;
        if(errcode1==null||errcode1!=0){
        	flag=false;
        }
        Integer errcode2=(Integer) result2.get("errcode");
        if(errcode2==null||errcode2!=0){
        	flag=false;
        }
        Integer errcode3=(Integer) result3.get("errcode");
        if(errcode3==null||errcode3!=0){
        	flag=false;
        }
        if(!flag){
        	del_kfaccount(kf_account);
        }
        return flag;
    }

    //---------- 测试
    
    @Test
    public void test(){
//        System.out.println(add_kfaccount("test","客服1号"));
        System.out.println(JSONArray.fromObject(get_kflist()).toString());
    }
    @Test
    public void test1(){
//        System.out.println(add_kfaccount("test","客服1号"));
        get_online_kflist();
        System.out.println(JSONArray.fromObject(get_kflist()).toString());
    }
    @Test
    public void test2(){
        File file=new File("C:\\Users\\Administrator\\Desktop\\司法文档\\logo\\logo.jpg");
        System.out.println(    upload_headimg("test@xmsf12348",file ).toString()       );
    }
    @Test
    public void test3(){
        System.out.println(  add_kfaccount("XMD1805008@xmsf12348","客服6号").toString()          );
        File file=new File("C:\\Users\\Administrator\\Desktop\\司法文档\\logo\\logo.jpg");
        
        System.out.println(   upload_headimg("test6@xmsf12348",file ).toString()         );
        System.out.println(   invite_worker("test6@xmsf12348","XMD1805008").toString()         );
        
    }
    public void test4(){
//    	System.out.println( JSONArray.fromObject(get_kflist()).toString() );
    	System.out.println(  del_kfaccount("1994kwp@xmsf12348").toString() );
    }
    public void test5(){
    	System.out.println( JSONArray.fromObject(get_kflist()).toString() );
//    	System.out.println(  del_kfaccount("test4@xmsf12348").toString() );
    }

    
    
}
