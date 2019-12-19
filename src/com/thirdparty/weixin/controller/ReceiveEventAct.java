package com.thirdparty.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.junit.rules.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirdparty.weixin.base.WeixinConfig;
import com.thirdparty.weixin.bean.InputMessage;
import com.thirdparty.weixin.bean.MsgType;
import com.thirdparty.weixin.demo.aes.AesException;
import com.thirdparty.weixin.demo.aes.WXBizMsgCrypt;
import com.thirdparty.weixin.service.EventPushHandleSvc;
import com.thirdparty.weixin.service.MessageHandleSvc;

import tools.http.XmlHandle;
/**
 * @author wyj
 */
@Controller
@RequestMapping("weixin")
public class ReceiveEventAct {
    @Autowired
    private EventPushHandleSvc eventPushHandleSvc;
    @Autowired
    private MessageHandleSvc messageHandleSvc;
    
    private InputMessage message;
    
    /**
     * 验证消息的确来自微信服务器<br>
     * 文档路径：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(value="/checkSignature.jspx",method = RequestMethod.GET)
    @ResponseBody
    public String checkSignature(HttpServletRequest request,String signature,String timestamp,String nonce,String echostr) throws AesException {
        //验证消息的确来自微信服务器
        WXBizMsgCrypt pc = new WXBizMsgCrypt(WeixinConfig.Token, WeixinConfig.EncodingAESKey, WeixinConfig.APPID);
        return pc.verifyUrl(signature, timestamp, nonce, echostr);
    }
    @RequestMapping(value="/checkSignature.jspx",method = RequestMethod.POST)
    @ResponseBody
    public String disposeMsg(HttpServletRequest request) throws AesException {
        message=XmlHandle.getInputMessage(request);
        
        
        //TODO 根据消息是否加密处理数据（暂时只做明文处理）
        
        //========   针对消息的处理 start    ===========//
        if(MsgType.Event.toString().equals(message.getMsgType())){    //事件
            eventPushHandleSvc.event_entrance(message);
        }else if(MsgType.Text.toString().equals(message.getMsgType())){   //文本消息处理
            return messageHandleSvc.text_dispose2(message);
        }
        //========   针对消息的处理 end    ===========//
        
        
        //5秒内回复success，可避免微信端发起重试
        return "success";
    }
}
