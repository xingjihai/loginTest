package com.thirdparty.weixin.bean;

/**
 * 被动回复（自动回复）
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543&token=&lang=zh_CN
 * @author wyj
 */
public class ReplyMessage {
    private String ToUserName;  
    private String FromUserName;  
    private Long   CreateTime;  
    private String MsgType = "text";  
    
    // 文本消息  
    private String Content;
    
    // 图片消息、语音消息 
    private String MediaId;
    
    // 位置消息  
    private String LocationX;  
    private String LocationY;  
    private Long   Scale;  
    private String Label;
    
    // 视频/音乐消息
    
    
    /**
     * 发送文本消息
     * @param ToUserName  接收方帐号（收到的OpenID）
     * @param FromUserName  开发者微信号
     * @param CreateTime  消息创建时间
     * @param MsgType  text
     * @param Content  回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示
     * @return
     */
    public static String text_message(String toUserName, String fromUserName,Long createTime,String msgType,String content){
        String text_xml="<xml>\r\n" + 
                "    <ToUserName><![CDATA[%1$s]]></ToUserName>\r\n" + 
                "    <FromUserName><![CDATA[%2$s]]></FromUserName>\r\n" + 
                "    <CreateTime>%3$s</CreateTime>\r\n" + 
                "    <MsgType><![CDATA[%4$s]]></MsgType>\r\n" + 
                "    <Content><![CDATA[%5$s]]></Content>\r\n" + 
                "    </xml>";
        return String.format(text_xml, toUserName, fromUserName, createTime, msgType,content);
    }
}
