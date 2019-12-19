package com.thirdparty.weixin.special.Impl;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdparty.weixin.bean.InputMessage;
import com.thirdparty.weixin.service.CustomserviceSvc;
import com.thirdparty.weixin.service.EventPushHandleSvc;
import com.thirdparty.weixin.service.KFSessionSvc;
import com.thirdparty.weixin.special.CustomerConsultMng;
import com.thirdparty.weixin.special.config.ConstantFor12348;

@Service
public class EventPushHandleSvcImpl implements EventPushHandleSvc{
//    @Autowired
//    private CustomserviceSvc customserviceSvc;
//    @Autowired
//    private CustomerConsultMng customerConsultMng;
//    @Autowired
//    private KFSessionSvc kFSessionSvc;
    
    
    @Override
    public void event_entrance(InputMessage inputMessage) {
        //TODO 消息排重处理
        
        switch (inputMessage.getEvent()) {
        case "subscribe":     //订阅
            if(StringUtils.isNotBlank(inputMessage.getTicket())){  //不为空为扫描二维码事件
                
            }else{
                //订阅事件处理
                subscribe_dispose(inputMessage);
            }
            break;
        case "unsubscribe":     //取消订阅
            unsubscribe_dispose(inputMessage);
            break;
        case "SCAN":     //订阅后的扫描二维码事件
            break;
        case "LOCATION":     //上报地理位置事件
            break;
        case "CLICK":     //点击菜单拉取消息时的事件推送
            click_dispose(inputMessage);
            break;
        case "VIEW":     //点击菜单跳转链接时的事件推送
            view_dispose(inputMessage);
            break;
        }
        
    }

    @Override
    public void subscribe_dispose(InputMessage inputMessage) {
        customserviceSvc.send_text(inputMessage.getFromUserName(), ConstantFor12348.SUBSCRIBE_TEXT);
        customserviceSvc.send_text(inputMessage.getFromUserName(), ConstantFor12348.SUBSCRIBE_TEXT2);
    }

    @Override
    public void unsubscribe_dispose(InputMessage inputMessage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void click_dispose(InputMessage inputMessage) {
        if(inputMessage.getEventKey()==null){
            return ;
        }
        switch (inputMessage.getEventKey()) {
        case ConstantFor12348.ONLINE_COUNSELING:   //"在线客服"按钮被点击
            online_counseling_click(inputMessage);
            break;
        case ConstantFor12348.CONSULT_TYPE_PHONE:   //"电话咨询"按钮被点击
            consult_type_phone_click(inputMessage);
            break;
        case ConstantFor12348.CONSULT_TYPE_APP:   //"APP下载"按钮被点击
            consult_type_app_click(inputMessage);
            break;
        case ConstantFor12348.CONSULT_TYPE_OFFICE:   //"官方网址"按钮被点击
            consult_type_office_click(inputMessage);
            break;
        }
        
    }
    //"在线客服"按钮-拉取消息
    private void online_counseling_click(InputMessage inputMessage){
        JSONObject returnObject=kFSessionSvc.getsession(inputMessage.getFromUserName());
        String kf_account=(String) returnObject.get("kf_account");
        if(StringUtils.isNotBlank(kf_account)){  //已在会话中
            customserviceSvc.send_text(inputMessage.getFromUserName(),"您已接入客服，请耐心等待客服人员回应");
            return;
        }
        
        //判断是否为待接入，待接入则提示，不为待接入，则进入流程（将wx_com_record状态修改，并发送案件类型消息）
        String open_id=inputMessage.getFromUserName();
        if( customerConsultMng.is_in_waitcase(open_id) ){  //处于待接入状态
            customserviceSvc.send_text(inputMessage.getFromUserName(),"您已创建案件，请等待客服人员接入");
            return;
        }else{
            customserviceSvc.send_text(inputMessage.getFromUserName(), customerConsultMng.get_cases_list(inputMessage.getFromUserName()));
            return;
        }
    }
    //"电话咨询"按钮-拉取消息
    private void consult_type_phone_click(InputMessage inputMessage){ 
        customserviceSvc.send_text(inputMessage.getFromUserName(), ConstantFor12348.PHONE_CLICK);
    }
    //"APP下载"按钮-拉取消息
    private void consult_type_app_click(InputMessage inputMessage){ 
    }
    //"官方网址"按钮-拉取消息
    private void consult_type_office_click(InputMessage inputMessage){
        customserviceSvc.send_text(inputMessage.getFromUserName(), ConstantFor12348.OFFICE_CLICK);
    }

    @Override
    public void view_dispose(InputMessage inputMessage) {
        // TODO Auto-generated method stub
    }

}
