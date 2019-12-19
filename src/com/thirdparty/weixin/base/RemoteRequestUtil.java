package com.thirdparty.weixin.base;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RemoteRequestUtil{

	public HttpClient httpClient;
	private Map<String, String> params;

	public void setExecuteParams(Map<String, String> params) {
		this.params = params;
	}

	private HttpClient getHttpClient(HttpServletRequest httpRequest) {
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute("httpClient") == null) {
			HttpClient httpclient = new DefaultHttpClient();
			session.setAttribute("httpClient", httpclient);
		}

		return (HttpClient) session.getAttribute("httpClient");
	}

	/**
     * @param uri
     * @param httpResponse
     * @param httpRequest
     */
    public String executePost(String uri, HttpServletRequest httpRequest) {
//        String method = httpRequest.getMethod();
//
//        method = method.toUpperCase();
//
//        HttpClient httpclient = getHttpClient(httpRequest);
//        HttpUriRequest httpUriRequest = null;
//
//
//        HttpEntity entity = HttpEntityFactory.buildEntity(httpRequest, this.params);
//        httppost.setEntity(entity);
//        httpUriRequest = httppost;
//        
//        HttpPost httppost = new HttpPost(uri);
//        HttpResponse response;
//        try {
//            response = httpclient.execute(httppost);
//            HttpEntity rentity = response.getEntity();
//            String content = EntityUtils.toString(entity, "utf-8");
//            return content;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

	/**
	 * 
	 * @param uri
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String execute(String uri) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(uri);
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "utf-8");
			return content;
		} catch (ClientProtocolException e) {
			 e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		}
		return null;
	}

}