package com.thirdparty.weixin.special.Impl;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import tools.http.XmlHandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.legal.common.util.StringUtil;
//import com.legal.common.web.context.ThreadContextHolder;
import com.thirdparty.weixin.base.WeixinConfig;
import com.thirdparty.weixin.bean.InputMessage;
import com.thirdparty.weixin.service.CustomserviceSvc;
import com.thirdparty.weixin.service.MessageHandleSvc;
import com.thirdparty.weixin.service.WeChatKFSvc;
import com.thirdparty.weixin.special.CustomerConsultMng;
import com.thirdparty.weixin.special.config.ConstantFor12348;

@Service
public class MessageHandleSvcImpl implements MessageHandleSvc{
//    @Autowired
//    private CustomserviceSvc customserviceSvc;
//    @Autowired
//    private CustomerConsultMng customerConsultMng;
//    @Autowired
//    private WeChatKFSvc weChatKFSvc;

    @Override
    @Deprecated
    public String text_dispose(InputMessage inputMessage) {
        HttpServletRequest request=ThreadContextHolder.getHttpRequest();
        String open_id=inputMessage.getFromUserName();
        
//        if(ConstantFor12348.IS_AUTO_RESPONSE){  //自动回复
//            customserviceSvc.send_text(inputMessage.getFromUserName(), ConstantFor12348.AUTO_RESPONSE_TEXT);
//            return;
//        }else 
        if(!customerConsultMng.cases_list_is_past(open_id)){ //正在选择案件类型
            if(StringUtil.isNumeric(inputMessage.getContent())){
                try {
                    Integer id=StringUtil.toInt(inputMessage.getContent(),true);
                    if(  customerConsultMng.save_cat_id(open_id,id)  ){   //保存类型id
                        //拉取地区列表
                        customserviceSvc.send_text(open_id, customerConsultMng.get_area_list(open_id));
                    }else{
                        customserviceSvc.send_text(open_id, "序号错误,请重新输入");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    customserviceSvc.send_text(open_id, "请输入上述序号");
                }
            }else{
                customserviceSvc.send_text(open_id, "请输入上述序号");
            }
        }else if(!customerConsultMng.area_list_is_past(open_id)){ //正在选择案件地区
            if(StringUtil.isNumeric(inputMessage.getContent())){
                try {
                    Integer id=StringUtil.toInt(inputMessage.getContent(),true);
                    if(  customerConsultMng.save_area_id(open_id,id)  ){   //保存地区id
                        //创建案件
                        customerConsultMng.createCases(open_id);
                        //获取在线客服数
                        Integer count=weChatKFSvc.get_online_kflist().size();
                        
                        //进入客服系统
                        customerConsultMng.intoService(open_id);
                        customserviceSvc.send_text(open_id,"创建案件成功，您已进入客服服务，当前在线客服:"+count+"人" );
                        
                        
                    }else{
                        customserviceSvc.send_text(open_id, "序号错误,请重新输入");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    customserviceSvc.send_text(open_id, "请输入上述序号");
                }
            }else{
                customserviceSvc.send_text(open_id, "请输入上述序号");
            }
        }else if(customerConsultMng.isService(open_id) ){        //客服状态
            //直接交给客服
            InputMessage im=new InputMessage();
            im.setToUserName(inputMessage.getFromUserName());
            im.setFromUserName(WeixinConfig.WEIXIN_NO);
            im.setCreateTime(inputMessage.getCreateTime());
            im.setMsgType("transfer_customer_service");
            String msg=XmlHandle.getXmlString(im);
            return msg;
        }else{
            customserviceSvc.send_text(open_id, ConstantFor12348.SUBSCRIBE_TEXT2);
        }
        return "success";
    }
    public String text_dispose2(InputMessage inputMessage){
        String open_id=inputMessage.getFromUserName();
        Integer sort=StringUtil.toInt(inputMessage.getContent(), -1);
        if(sort.equals(-1)){
            customserviceSvc.send_text(open_id, customerConsultMng.get_user_guide(open_id));
        }else{
            JSONObject send_return_obj=customerConsultMng.send_user_guide_message(open_id, sort);
            if(send_return_obj==null){
                customserviceSvc.send_text(open_id, customerConsultMng.get_user_guide(open_id));
            }
        }
        return "success";
    }

    @Override
    public void image_dispose(InputMessage inputMessage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void voice_dispose(InputMessage inputMessage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void video_dispose(InputMessage inputMessage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void shortvideo_dispose(InputMessage inputMessage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void location_dispose(InputMessage inputMessage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void link_dispose(InputMessage inputMessage) {
        // TODO Auto-generated method stub
        
    }
    
}
