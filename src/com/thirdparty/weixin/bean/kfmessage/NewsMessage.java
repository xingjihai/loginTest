package com.thirdparty.weixin.bean.kfmessage;

public class NewsMessage {
    private String title;
    private String description;
    private String url;
    private String picurl;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPicurl() {
        return picurl;
    }
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
    
    public NewsMessage() {
    }
    public NewsMessage(String title,String description,String url,String picurl) {
        this.title=title;
        this.description=description;
        this.url=url;
        this.picurl=picurl;
    }
    
    
}
