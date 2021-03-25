package com.cn.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HttpClient
 * @Description: 调用第三方接口
 * @Author: zhanghongjun
 * @Date: 2021/3/22 16:27
 */
public class HttpClient {
    /**
     * 带参数的get请求
     * @param url
     * @param param
     * @return String
     */
    public static String doGet(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的get请求
     * @param url
     * @return String
     */
    public static String doGet(String url) {

        return doGet(url, null);
    }

    /**
     * 带参数的post请求
     * @param url
     * @param param
     * @return String
     */
    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type", "application/json;charset=utf-8");
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的post请求
     * @param url
     * @return String
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * 传送json类型的post请求
     * @param url
     * @param json
     * @return String
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum","1");
        map.put("pageSize","4");
        String remoteUrl = "http://127.0.0.1:8200/api/normElement/list";
        String result =doGet(remoteUrl,map);
//        GenericResult genericResult = JSON.parseObject(result, GenericResult.class);
//                if(genericResult.getCode().equals("200")){
//                    System.out.println("调用成功");
//                   log.info("调用成功");
//        }
//        System.out.println(genericResult.getData());
//        PageInfo pageInfo =  JSON.parseObject(genericResult.getData().toString(),PageInfo.class);
//        List<NormElementListVo> list= pageInfo.getList();
//        log.info(list.toString());

        //输入的Json参数
        JSONObject jsonObject = new JSONObject();
//添加访问参数
        jsonObject.put("assessmentIds","assessmentIds");
        jsonObject.put("assessmentNames","assessmentNames");
        jsonObject.put("description","描述");
        jsonObject.put("normBasis","normBasis");
        jsonObject.put("normName","指标名称http");
        jsonObject.put("normTypeId",200);
        jsonObject.put("normTypeName","指标类型");
        jsonObject.put("parentId",200);
        jsonObject.put("parentNormName","父指标名称");
        jsonObject.put("targetId",200);
        jsonObject.put("targetName","目标名称");
        jsonObject.put("weight",40);

        //传JSONObject 对象  ，获取端可以解析或者解决获取对应的对象实体类
        String re=  doPostJson("http://localhost:8200/api/norm",jsonObject.toJSONString());
        //todo:将返回的字符串转化成对应的Java对象
//        GenericResult genericResultAdd = JSON.parseObject(re, GenericResult.class);
//        if(genericResultAdd.getCode().equals("200")){
//            System.out.println("新增调用成功");
//            log.info("新增调用成功");
//        }
    }
}
