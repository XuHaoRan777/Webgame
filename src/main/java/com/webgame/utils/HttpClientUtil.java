package com.webgame.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpClient 工具类
 */
public class HttpClientUtil {

    /**
     * 发送POST 请求
     * @param url
     * @param data
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String doPost(String url, String data){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000).setConnectTimeout(20000)
                .setConnectionRequestTimeout(10000).build();
        httpPost.setConfig(requestConfig);
        String context = StringUtils.EMPTY;
        if (!StringUtils.isEmpty(data)) {
            StringEntity body = new StringEntity(data, "utf-8");
            httpPost.setEntity(body);
        }
        // 设置回调接口接收的消息头
        httpPost.addHeader("Content-Type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            context = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                response.close();
                httpPost.abort();
                httpClient.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return context;
    }

    /**
     *  发送Get 请求
     * @param url
     * @param paraMap
     * @return
     */
    //@SuppressWarnings("deprecation")
    public static String doGet(String url, Map<String,Object> paraMap){

        if(paraMap != null && !paraMap.isEmpty()){
            // 为 get url 添加参数
            StringBuffer urlData = new StringBuffer("?");
            int paramSize = paraMap.keySet().size();
            int i = 1;
            for (String currentKey : paraMap.keySet()) {
                urlData.append(currentKey + ":" + paraMap.get(currentKey));
                if(i < paramSize){
                    urlData.append("&");
                    i++;
                }
            }
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpGet.
        HttpGet httpget = new HttpGet(url);
        //System.out.println("executing request " + httpget.getURI());
        String context = StringUtils.EMPTY;
        CloseableHttpResponse response = null;
        try {
            // 执行get请求.
            response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
                // 响应内容长度
                //System.out.println("Response content length: " + entity.getContentLength());
                // 响应内容
                //System.out.println("Response content: " + EntityUtils.toString(entity));
                context = EntityUtils.toString(entity, "UTF-8");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                response.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return context;
    }


    /**
     * 解析出url参数中的键值对
     * @param url url参数
     * @return 键值对
     */
    public static Map<String, String> getRequestParam(String url) {
        Map<String, String> map = new HashMap<String, String>();
        String[] arrSplit = null;
        // 每个键值为一组
        arrSplit = url.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                map.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    map.put(arrSplitEqual[0], "");
                }
            }
        }
        return map;
    }


}
