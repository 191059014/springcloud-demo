package com.hb.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";
    private static final String URL_PARAM_CONNECT_FLAG = "&";
    private static final String EMPTY = "";
    protected static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static MultiThreadedHttpConnectionManager connectionManager = null;

    private static int connectionTimeOut = 25000;

    private static int socketTimeOut = 25000;

    private static int maxConnectionPerHost = 20;

    private static int maxTotalConnections = 20;

    private static HttpClient client = new HttpClient(connectionManager);

    public static String post(String url, Map<String, String> params, String encoding) {
        String response = "";
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);

            if (params != null) {
                Set<String> keySet = params.keySet();
                for (String key : keySet) {
                    String value = (String) params.get(key);
                    postMethod.addParameter(key, value);
                }
            }

            int statusCode = client.executeMethod(postMethod);
            if (statusCode == 200) {
                response = postMethod.getResponseBodyAsString();
            } else if (logger.isErrorEnabled())
                logger.error("Response Status Code = " + postMethod.getStatusCode());
        } catch (HttpException e) {
            logger.error("HttpException", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException", e);
            e.printStackTrace();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
                postMethod = null;
            }
        }

        return response;
    }

    public static String get(String url, Map<String, String> params, String encoding) {
        String response = "";
        GetMethod getMethod = null;
        StringBuffer strtTotalURL = new StringBuffer("");
        if (params != null) {
            if (strtTotalURL.indexOf("?") == -1)
                strtTotalURL.append(url).append("?").append(getUrl(params, encoding));
            else {
                strtTotalURL.append(url).append("&").append(getUrl(params, encoding));
            }
        }
        if (logger.isDebugEnabled())
            logger.debug("GET请求URL = /n" + strtTotalURL.toString());
        try {
            getMethod = new GetMethod(strtTotalURL.toString());
            getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);

            int statusCode = client.executeMethod(getMethod);
            if (statusCode == 200) {
                response = getMethod.getResponseBodyAsString();
            } else if (logger.isDebugEnabled())
                logger.debug("Response Status Code = " + getMethod.getStatusCode());
        } catch (HttpException e) {
            logger.error("HttpException", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException", e);
            e.printStackTrace();
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
                getMethod = null;
            }
        }

        return response;
    }

    private static String getUrl(Map<String, String> map, String valueEnc) {
        if ((null == map) || (map.keySet().size() == 0)) {
            return "";
        }
        StringBuffer url = new StringBuffer();
        Set keys = map.keySet();
        for (Iterator it = keys.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            if (map.containsKey(key)) {
                String val = (String) map.get(key);
                String str = val != null ? val : "";
                try {
                    str = URLEncoder.encode(str, valueEnc);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).append("&");
            }
        }
        String strURL = "";
        strURL = url.toString();
        if ("&".equals("" + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }

        return strURL;
    }

    public static String getRemoteAddress(HttpServletRequest request) {
        if (request == null) {
            if (logger.isErrorEnabled()) {
                logger.error("HttpServletRequest is null,can't get remote ip!");
            }

            return null;
        }
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if ((StringUtils.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip)))
                ip = request.getRemoteAddr();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    public static void configCache(HttpServletResponse response, Integer expires) {
        if ((expires != null) && (expires.intValue() > 0)) {
            Date date = new Date();
            response.setDateHeader("Last-Modified", date.getTime());
            response.setDateHeader("Expires", date.getTime() + expires.intValue());
            response.setHeader("Cache-Control", "public");
            response.setHeader("Pragma", "Pragma");
        } else {
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0L);
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "must-revalidate");
        }
    }

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
        connectionManager.getParams().setSoTimeout(socketTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
    }
}