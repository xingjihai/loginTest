package com.thirdparty.weixin.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.http.HttpTools;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.thirdparty.weixin.base.BaseSvc;
import com.thirdparty.weixin.bean.kfmessage.NewsMessage;
import com.thirdparty.weixin.service.CustomserviceSvc;
/**
 * 客服接口
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140547&token=&lang=zh_CN
 * @author wyj
 */
@Service
public class CustomserviceSvcImpl implements CustomserviceSvc{

    private JSONObject message_send(String jsonString) {
        String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+BaseSvc.get_access_token();
        JSONObject returnObj = JSONObject.fromObject(HttpTools.excetePost(url.toString(),jsonString));
        return returnObj;
    }
    
    /**
     * 回复文本消息
     */
    public JSONObject send_text(String openId,String content) {
        JSONObject obj=new JSONObject();
        obj.accumulate("touser", openId);
        obj.accumulate("msgtype", "text");
        JSONObject text_obj=new JSONObject();
        text_obj.accumulate("content", content);
        obj.accumulate("text",text_obj );
        return message_send(obj.toString());
    }
    /**
     * 回复图文消息
     */
    public JSONObject send_news(String openId,List<NewsMessage> newsList) {
        JSONObject obj=new JSONObject();
        obj.accumulate("touser", openId);
        obj.accumulate("msgtype", "news");
        JSONObject news_obj=new JSONObject();
        JSONArray articlesArray=new JSONArray();
        JSONObject articles_obj=new JSONObject();
        for (NewsMessage newsMessage : newsList) {
            articles_obj.accumulate("title", newsMessage.getTitle());
            articles_obj.accumulate("description", newsMessage.getDescription());
            articles_obj.accumulate("url", newsMessage.getUrl());
            articles_obj.accumulate("picurl", newsMessage.getPicurl());
            articlesArray.add(articles_obj);
        }
        news_obj.accumulate("articles", articlesArray);
        obj.accumulate("news", news_obj);
        System.out.println(obj.toString());
        return message_send(obj.toString());
    }

    @Test
    public void test(){
        send_text("ornyc1IlCg1N3kKTi-tTtpaBGQ70", "success");
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void test2(){
        NewsMessage newsMessage=new NewsMessage("标题","描述描述","https://www.baidu.com","");
        List newsList=new ArrayList<NewsMessage>();
        newsList.add(newsMessage);
        System.out.println(  send_news("ornyc1IlCg1N3kKTi-tTtpaBGQ70", newsList).toString()  );
    }
    
}
