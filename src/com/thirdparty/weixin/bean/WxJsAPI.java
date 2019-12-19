package com.thirdparty.weixin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * Module: core
 * <p>Function: 微信JS-SDK配置
 * <p>Description:       		
 * <p>Copyright © 2008-2028, Sinlff, All Rights Reserved
 * @since 1.0   
 * @version 1.0  
 * @author 181675284@qq.com 王盛武 Jan 12, 2015 
 * @see #log
 */
public class WxJsAPI implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 公众号的唯一标识 */
	private String appId;
	
	/** 生成签名的时间戳 */
	private String timestamp;
	
	/** 生成签名的随机串 */
	private String nonceStr;
	
	/** 签名 */
	private String signature;
	
	/** 需要使用的JS接口列表，所有JS接口列表见附录2 */
	private static List<String> jsApiList;
	private static String jsApiListStr;
	
	/** js访问票据 */
	private String jsapiTicket;
	
	/** 是否静态URL */
	private Boolean fixedUrl;
	
	/** 生成的url */
	private String url;
	
	/** 生成的url */
	private String domainUrl;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public static List<String> getJsApiList() {
		if(jsApiList==null){
			jsApiList=new ArrayList<String>();
			jsApiList.add("onMenuShareTimeline");
			jsApiList.add("onMenuShareAppMessage");
			jsApiList.add("onMenuShareQQ");
			jsApiList.add("onMenuShareWeibo");
			jsApiList.add("startRecord");
			jsApiList.add("stopRecord");
			jsApiList.add("onVoiceRecordEnd");
			jsApiList.add("playVoice");
			jsApiList.add("pauseVoice");
			jsApiList.add("stopVoice");
			jsApiList.add("onVoicePlayEnd");
			jsApiList.add("uploadVoice");
			jsApiList.add("downloadVoice");
			jsApiList.add("chooseImage");
			jsApiList.add("previewImage");
			jsApiList.add("uploadImage");
			jsApiList.add("downloadImage");
			jsApiList.add("translateVoice");
			jsApiList.add("getNetworkType");
			jsApiList.add("openLocation");
			jsApiList.add("getLocation");
			jsApiList.add("hideOptionMenu");
			jsApiList.add("showOptionMenu");
			jsApiList.add("hideMenuItems");
			jsApiList.add("showMenuItems");
			jsApiList.add("hideAllNonBaseMenuItem");
			jsApiList.add("showAllNonBaseMenuItem");
			jsApiList.add("closeWindow");
			jsApiList.add("scanQRCode");
			jsApiList.add("chooseWXPay");
			jsApiList.add("openProductSpecificView");
			jsApiList.add("addCard");
			jsApiList.add("chooseCard");
			jsApiList.add("openCard");
		}
		return jsApiList;
	}
	
	public static String getJsApiListStr() {
		if(jsApiList==null)
			getJsApiList();
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		for(int i=0,j=jsApiList.size()-1;i<jsApiList.size();i++){
			sb.append("'").append(jsApiList.get(i)).append("'");
			if(i!=j)
				sb.append(",");
		}
		sb.append("]");
		jsApiListStr=sb.toString();
		return jsApiListStr;
	}

	public static void setJsApiListStr(String jsApiListStr) {
		WxJsAPI.jsApiListStr = jsApiListStr;
	}

	public static void setJsApiList(List<String> jsApiList) {
		WxJsAPI.jsApiList = jsApiList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public Boolean getFixedUrl() {
		return fixedUrl;
	}

	public void setFixedUrl(Boolean fixedUrl) {
		this.fixedUrl = fixedUrl;
	}

	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}

}