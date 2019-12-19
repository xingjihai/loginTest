package com.thirdparty.weixin.bean;

/** 
 * 消息类型 
 */  
public enum MsgType {  
  
    Text("text"),  
    Image("image"),  
    Music("music"),  
    Video("video"),  
    Voice("voice"),  
    Location("location"),  
    Link("link"),
    Event("event");
    
    private String msgType = "";  
  
    MsgType(String msgType) {  
        this.msgType = msgType;  
    }  
  
    /** 
     * @return the msgType 
     */  
    @Override  
    public String toString() {  
        return msgType;  
    }  
}  
