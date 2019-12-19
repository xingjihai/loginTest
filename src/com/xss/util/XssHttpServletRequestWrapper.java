package com.xss.util;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getQueryString() {
		String value = super.getQueryString();
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	
	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[]parameters=super.getParameterValues(name);
		if (parameters==null||parameters.length == 0) {
			return null;
		}
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = xssEncode(parameters[i]);
		}
		return parameters;
	}
	
	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> getHeaderNames 也可能需要覆盖
	 */
	@Override
	public String getHeader(String name) {
		//TODO Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	
	private String xssEncode(String s){
	    if (s == null || s.equals("")) {
            return s;
        }
//        try {
//            if (s.equals(new String(s.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
//                s = new String(s.getBytes("ISO-8859-1"), "UTF-8");
//            } else {
//                s = new String(s.getBytes("UTF-8"), "UTF-8");
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        // < > ' " \ / # &
        if (!s.startsWith("<P") && !s.contains("</p>")) {   //后台编辑器（富文本框）
            // 避免p标签被过滤
            s = s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        }
        s = s.replaceAll("#", "＃");
        s = s.replaceAll("%", "％");
        s = s.replaceAll("eval\\((.*)\\)", "");
        s = s.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        s = s.replaceAll("script", "");
        return s;
	}
	
}
